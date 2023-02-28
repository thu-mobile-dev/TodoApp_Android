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
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private List<Todo> todoList;
    private final LayoutInflater inflater;
    private TodoRepository repository;

    public TodoListAdapter(Context context, List<Todo> todoList, TodoRepository repository) {
        inflater = LayoutInflater.from(context);
        this.todoList = todoList;
        this.repository = repository;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = inflater.inflate(
                R.layout.todolist_item, parent, false);
        return new TodoViewHolder(mItemView, this, repository);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        // Retrieve the data for that position.
        String current = todoList.get(position).getContent();
        // Add the data to the view holder.
        holder.todoItemView.setText(current);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }
}

class TodoViewHolder extends RecyclerView.ViewHolder{
    public final TextView todoItemView;
    private final TodoListAdapter adapter;

    public TodoViewHolder(@NonNull View itemView, TodoListAdapter adapter, TodoRepository repository) {
        super(itemView);
        todoItemView = itemView.findViewById(R.id.todo);
        this.adapter = adapter;
        for(int index = 0; index < ((ViewGroup) itemView).getChildCount(); index++) {
            View nextChild = ((ViewGroup) itemView).getChildAt(index);
            if (nextChild instanceof ImageButton) {
                nextChild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get the position of the item that was clicked.
                        int position = getLayoutPosition();
                        Todo todo = adapter.getTodoList().get(position);
                        repository.delete(todo.getNumber());
                        adapter.getTodoList().remove(position);
                        // Notify the adapter, that the data has changed so it can
                        // update the RecyclerView to display the data.
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }


}