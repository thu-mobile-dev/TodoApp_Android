package org.tsinghua.todoapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoRepository {
    private TodoDao todoDao;
    private LiveData<List<Todo>> allTodos;

    public TodoRepository(Application application) {
        TodoRoomDatabase db = TodoRoomDatabase.getDatabase(application);
        todoDao = db.todoDao();
        allTodos = todoDao.getAllTodos();
    }

    public LiveData<List<Todo>> getAllTodos() {
        return allTodos;
    }

    public void insert(Todo todo) {
        new insertAsyncTask(todoDao).execute(todo);
    }

    public void delete(int number) {
        new deleteAsyncTask(todoDao).execute(number);
    }

    private static class insertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao insertDao;

        public insertAsyncTask(TodoDao dao) {
            insertDao = dao;
        }

        @Override
        protected Void doInBackground(Todo... params) {
            insertDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private TodoDao deleteDao;

        public deleteAsyncTask(TodoDao dao) {
            deleteDao = dao;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            deleteDao.delete(new Todo(params[0], null));
            return null;
        }
    }
}
