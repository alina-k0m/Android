package com.example.lr2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    CheckBox readCheckBox;
    RadioButton hardcoverRadioButton, paperbackRadioButton;
    Spinner genreSpinner;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String title = getIntent().getStringExtra("TITLE");
        String author = getIntent().getStringExtra("AUTHOR");
        int year = getIntent().getIntExtra("YEAR", 0);

        TextView displayTextView = findViewById(R.id.displayTextView);
        displayTextView.setText("Title: " + title + "\nAuthor: " + author + "\nYear: " + year);

        readCheckBox = findViewById(R.id.readCheckBox);
        hardcoverRadioButton = findViewById(R.id.hardcoverRadioButton);
        paperbackRadioButton = findViewById(R.id.paperbackRadioButton);
        genreSpinner = findViewById(R.id.genreSpinner);
        submitButton = findViewById(R.id.submitButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genres_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        submitButton.setOnClickListener(v -> {
            // Implement saving the information or submission logic
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Read", readCheckBox.toString());
        outState.putString("Hardcover", hardcoverRadioButton.toString());
        outState.putString("Paperback", paperbackRadioButton.toString());
        outState.putInt("Genre", genreSpinner.getBaseline());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Read", readCheckBox.toString());
        savedInstanceState.putString("Hardcover", hardcoverRadioButton.toString());
        savedInstanceState.putString("Paperback", paperbackRadioButton.toString());
        savedInstanceState.putInt("Genre", genreSpinner.getBaseline());
    }
}
