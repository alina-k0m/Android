package com.example.lr2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText titleEditText, authorEditText;
    DatePicker yearDatePicker;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        yearDatePicker = findViewById(R.id.yearDatePicker);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("TITLE", titleEditText.getText().toString());
            intent.putExtra("AUTHOR", authorEditText.getText().toString());
            intent.putExtra("YEAR", yearDatePicker.getYear());
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TITLE", titleEditText.getText().toString());
        outState.putString("AUTHOR", authorEditText.getText().toString());
        outState.putInt("YEAR", yearDatePicker.getYear());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)  {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("TITLE", titleEditText.getText().toString());
        savedInstanceState.putString("AUTHOR", authorEditText.getText().toString());
        savedInstanceState.putInt("YEAR", yearDatePicker.getYear());
    }

}