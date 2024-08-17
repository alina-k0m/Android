package com.example.lr2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Activity1 extends AppCompatActivity {
    private EditText bookTitleEdit, bookAuthorEdit, bookYearEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        bookTitleEdit = findViewById(R.id.bookTitleEdit);
        bookAuthorEdit = findViewById(R.id.bookAuthorEdit);
        bookYearEdit = findViewById(R.id.bookYearEdit);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(Activity1.this, Activity2.class);
            intent.putExtra("bookTitle", bookTitleEdit.getText().toString());
            intent.putExtra("bookAuthor", bookAuthorEdit.getText().toString());
            intent.putExtra("bookYear", bookYearEdit.getText().toString());
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("bookTitle", bookTitleEdit.getText().toString());
        outState.putString("bookAuthor", bookAuthorEdit.getText().toString());
        outState.putString("bookYear", bookYearEdit.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bookTitleEdit.setText(savedInstanceState.getString("bookTitle"));
        bookAuthorEdit.setText(savedInstanceState.getString("bookAuthor"));
        bookYearEdit.setText(savedInstanceState.getString("bookYear"));
    }
}