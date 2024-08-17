package com.example.lr2;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    private Spinner genreSpinner, languageSpinner;
    private DatePicker dateReadingInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        TextView textView = findViewById(R.id.textViewPrevious);
        String bookTitle = getIntent().getStringExtra("bookTitle");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        String bookYear = getIntent().getStringExtra("bookYear");
        textView.setText("Book: " + bookTitle + "\nAuthor: " + bookAuthor + "\nYear: " + bookYear);

        genreSpinner = findViewById(R.id.genreSpinner);
        languageSpinner = findViewById(R.id.languageSpinner);
        dateReadingInput = findViewById(R.id.dateReadingInput);

        Button previousButton = findViewById(R.id.previousButton);
        Button nextButton = findViewById(R.id.nextButton);

        previousButton.setOnClickListener(v -> finish());

        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(Activity2.this, Activity3.class);
            intent.putExtra("bookTitle", bookTitle);
            intent.putExtra("bookAuthor", bookAuthor);
            intent.putExtra("bookYear", bookYear);
            intent.putExtra("genre", genreSpinner.getSelectedItem().toString());
            intent.putExtra("language", languageSpinner.getSelectedItem().toString());
            intent.putExtra("readDate", dateReadingInput.getDayOfMonth()
                    + "/" + dateReadingInput.getMonth()
                    + "/" + dateReadingInput.getYear());
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("genre", genreSpinner.getSelectedItem().toString());
        outState.putString("language", languageSpinner.getSelectedItem().toString());
        outState.putInt("readDateDay", dateReadingInput.getDayOfMonth());
        outState.putInt("readDateMonth", dateReadingInput.getMonth());
        outState.putInt("readDateYear", dateReadingInput.getYear());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //восстанавливаем состояние Spinner и DatePicker
    }
}