package com.example.lr4;

import android.os.Bundle;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        DatePicker datePicker = findViewById(R.id.datePicker);

        findViewById(R.id.buttonSelectDate).setOnClickListener(v -> {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();
            String selectedDate = day + "/" + month + "/" + year;

            Snackbar.make(findViewById(android.R.id.content), "Выбрана дата: " + selectedDate, Snackbar.LENGTH_LONG).show();
        });
    }
}
