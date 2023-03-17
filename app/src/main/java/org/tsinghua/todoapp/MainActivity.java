package org.tsinghua.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private final TodoList todoList = new TodoList();
    private RecyclerView recyclerView;
    private TodoListAdapter adapter;
    private Button addButton;
    private TextInputLayout textInputLayout;

    public static final int EDIT_DETAIL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add_button);
        textInputLayout = findViewById(R.id.textInputLayout);

        // Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            todoList.insert("Todo " + i);
        }
        // Create recycler view.
        recyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        adapter = new TodoListAdapter(this, todoList);
        // Connect the adapter with the recycler view.
        recyclerView.setAdapter(adapter);
        // Give the recycler view a default layout manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = textInputLayout.getEditText().getText().toString();
                adapter.getTodoList().insert(content);
                textInputLayout.getEditText().setText("");
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == EDIT_DETAIL) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String content = data.getStringExtra("content");
                String detail = data.getStringExtra("detail");
                int position = data.getIntExtra("position", 0);
                Todo todo = adapter.getTodoList().get(position);
                todo.setContent(content);
                todo.setDetail(detail);
                adapter.notifyDataSetChanged();
            }
        }
    }
}

