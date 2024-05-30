package com.example.lr2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonNext = findViewById(R.id.button);
        buttonNext.setOnClickListener(view -> {
            String title = ((EditText) findViewById(R.id.editTextTitle)).getText().toString();
            String author = ((EditText) findViewById(R.id.editTextAuthor)).getText().toString();
            String year = ((EditText) findViewById(R.id.editTextYear)).getText().toString();

            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("author", author);
            intent.putExtra("year", year);
            startActivity(intent);
        });
    }
}


