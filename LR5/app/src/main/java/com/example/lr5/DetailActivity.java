package com.example.lr5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("item", getIntent().getStringExtra("item"));
        detailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .commit();
    }
}
