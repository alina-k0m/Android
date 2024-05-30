package com.example.lr2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String author = extras.getString("author");
            String year = extras.getString("year");

            ((TextView) findViewById(R.id.textViewTitle)).setText(title);
            ((TextView) findViewById(R.id.textViewAuthor)).setText(author);
            ((TextView) findViewById(R.id.textViewYear)).setText(year);
        }
    }
}
