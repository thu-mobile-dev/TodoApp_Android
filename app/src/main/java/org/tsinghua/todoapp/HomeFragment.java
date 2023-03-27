package org.tsinghua.todoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class HomeFragment extends Fragment {
    private final TodoList todoList = new TodoList();
    private RecyclerView recyclerView;
    private TodoListAdapter adapter;
    private FloatingActionButton addButton;
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;
    private View view;

    private HomeFragmentListener listener;

    public interface HomeFragmentListener {
        void hideBottomNavView();
        void showBottomNavView();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.listener = (HomeFragmentListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            todoList.insert("Todo " + i);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        addButton = view.findViewById(R.id.add_button);
        textInputLayout = view.findViewById(R.id.textInputLayout);
        textInputEditText = view.findViewById(R.id.textInputEditText);
        // Create recycler view.
        recyclerView = view.findViewById(R.id.recyclerview);
        textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = textInputLayout.getEditText().getText().toString();
                adapter.getTodoList().insert(content);
                textInputLayout.getEditText().setText("");
                adapter.notifyDataSetChanged();
                Utils.HideSoftKey(getContext(), textInputEditText);
                listener.showBottomNavView();
                textInputLayout.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.VISIBLE);
                recyclerView.scrollToPosition(adapter.getItemCount()-1);
            }
        });

        // Create an adapter and supply the data to be displayed.
        adapter = new TodoListAdapter(getContext(), todoList);
        // Connect the adapter with the recycler view.
        recyclerView.setAdapter(adapter);
        // Give the recycler view a default layout manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.hideBottomNavView();
                textInputLayout.setVisibility(View.VISIBLE);
                textInputLayout.requestFocus();
                addButton.setVisibility(View.GONE);
                Utils.ShowSoftKey(getContext(), textInputEditText);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        textInputLayout.setVisibility(View.INVISIBLE);
        addButton.setVisibility(View.VISIBLE);
        listener.showBottomNavView();
        Utils.HideSoftKey(getContext(), textInputEditText);
    }
}
