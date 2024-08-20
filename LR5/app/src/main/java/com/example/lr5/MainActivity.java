package com.example.lr5;

/*
Фрагменты. Введение в понятие фрагмент. Жизненный цикл фрагмента
        Разработать два фрагмента: один для вывода списка, а второй для вывода информации о выбранном пункте из списка. Для горизонтальной ориентации два фрагмента отображаются одновременно, в вертикальной каждый в своей активности
        Добавить сохранение выбранного элемента при переходах из альбомного в портретный и наоборот.
*/

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.OnItemSelectedListener {
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ListFragment listFragment = new ListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit();
        } else {
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        }
    }

    @Override
    public void onItemSelected(String item) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            detailFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putString("item", item);
            detailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailFragment, detailFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (detailFragment != null && detailFragment.getArguments() != null) {
            outState.putString("selectedItem", detailFragment.getArguments().getString("item"));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String selectedItem = savedInstanceState.getString("selectedItem");
        if (selectedItem != null) {
            // Обновление DetailFragment
        }
    }
}