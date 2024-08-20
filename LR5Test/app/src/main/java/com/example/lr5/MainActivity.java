package com.example.lr5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements ListFragment.OnItemSelectedListener {
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.list_fragment_container, new ListFragment())
                    .add(R.id.detail_fragment_container, new DetailFragment())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(String item) {
        if (detailFragment == null) {
            detailFragment = new DetailFragment();
        }
        Bundle args = new Bundle();
        args.putString("selected_item", item);
        detailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_container, detailFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (detailFragment != null && detailFragment.getArguments() != null) {
            outState.putString("selected_item", detailFragment.getArguments().getString("selected_item"));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String selectedItem = savedInstanceState.getString("selected_item");
        if (selectedItem != null) {
            DetailFragment detailFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putString("selected_item", selectedItem);
            detailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, detailFragment)
                    .commit();
        }
    }
}
