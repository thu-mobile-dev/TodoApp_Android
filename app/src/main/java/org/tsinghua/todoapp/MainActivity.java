package org.tsinghua.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.tsinghua.todoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements HomeFragment.HomeFragmentListener {
    private ActivityMainBinding binding;
    private BottomNavigationView navView;

    public static final int EDIT_DETAIL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        this.navView = navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
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
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
                RecyclerView view = (RecyclerView) fragment.getView().findViewById(R.id.recyclerview);
                TodoListAdapter adapter = (TodoListAdapter) view.getAdapter();
                Todo todo = adapter.getTodoList().get(position);
                todo.setContent(content);
                todo.setDetail(detail);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void hideBottomNavView() {
        navView.setVisibility(View.GONE);
    }

    @Override
    public void showBottomNavView() {
        navView.setVisibility(View.VISIBLE);
    }
}