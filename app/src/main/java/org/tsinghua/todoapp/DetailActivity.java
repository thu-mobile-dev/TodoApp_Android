package org.tsinghua.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class DetailActivity extends AppCompatActivity {
    TextInputEditText contentText;
    TextInputEditText detailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        contentText = findViewById(R.id.content);
        detailText = findViewById(R.id.detail);

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String detail = intent.getStringExtra("detail");
        int position = intent.getIntExtra("position", 0);
        contentText.setText(content);
        detailText.setText(detail);
        Intent replyIntent = new Intent();
        // default values, to avoid return empty replyIntent
        replyIntent.putExtra("position", position);
        replyIntent.putExtra("content", content);
        replyIntent.putExtra("detail", detail);
        contentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                replyIntent.putExtra("content", content);
                setResult(RESULT_OK, replyIntent);
            }
        });
        detailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String detail = editable.toString();
                replyIntent.putExtra("detail", detail);
                setResult(RESULT_OK, replyIntent);
            }
        });
    }
}
