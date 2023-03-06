package org.tsinghua.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private final LayoutInflater inflater;
    private TodoViewModel todoViewModel;

    public TodoListAdapter(Context context, TodoViewModel viewModel) {
        inflater = LayoutInflater.from(context);
        this.todoViewModel = viewModel;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = inflater.inflate(
                R.layout.todolist_item, parent, false);
        return new TodoViewHolder(mItemView, this, todoViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        // Retrieve the data for that position.
        String current = getTodoList().get(position).getContent();
        // Add the data to the view holder.
        holder.todoItemView.setText(current);
    }

    @Override
    public int getItemCount() {
        if (getTodoList() == null) {
            return 0;
        }
        return getTodoList().size();
    }

    public List<Todo> getTodoList() {
        return todoViewModel.getAllTodos().getValue();
    }

}

class TodoViewHolder extends RecyclerView.ViewHolder{
    public final TextView todoItemView;
    private TodoViewModel todoViewModel;

    public TodoViewHolder(@NonNull View itemView, TodoListAdapter adapter, TodoViewModel todoViewModel) {
        super(itemView);
        todoItemView = itemView.findViewById(R.id.todo);
        ImageButton imageButton = itemView.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(view -> {
            // Get the position of the item that was clicked.
            int position = getLayoutPosition();
            Todo todo = adapter.getTodoList().get(position);
            todoViewModel.delete(todo.getNumber());
        });
    }
}