package org.tsinghua.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private List<Todo> todoList;

    private RecyclerView recyclerView;
    private TodoListAdapter adapter;
    private Button addButton;
    private TextInputLayout textInputLayout;
    private TodoRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.add_button);
        textInputLayout = findViewById(R.id.textInputLayout);

        // TODO: 2023/2/10 .get() will block the UI thread
        try {
            repository = new asyncCreateRepository().execute(this.getApplication()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Todo> allTodos = repository.getAllTodos();

        // Create recycler view.
        recyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        adapter = new TodoListAdapter(this, todoList, repository);
        // Connect the adapter with the recycler view.
        recyclerView.setAdapter(adapter);
        // Give the recycler view a default layout manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTodoList(allTodos);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = textInputLayout.getEditText().getText().toString();
                List<Todo> todoList = adapter.getTodoList();
                int number;
                if (todoList.isEmpty()) {
                    number = 1;
                }else {
                    number = todoList.get(todoList.size() - 1).getNumber() + 1;
                }
                Todo todo = new Todo(number, content);
                todoList.add(todo);
                repository.insert(todo);
                textInputLayout.getEditText().setText("");
                adapter.notifyDataSetChanged();
            }
        });
    }

    class asyncCreateRepository extends AsyncTask<Application, Void, TodoRepository> {
        @Override
        protected TodoRepository doInBackground(Application... applications) {
            return new TodoRepository(applications[0]);
        }
    }

}
