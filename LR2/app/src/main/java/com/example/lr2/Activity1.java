package com.example.lr2;

/*
Разработайте UI для регистрации и создания «ITEM» книга. Количество характеристик должно быть не менее 6.
        На одном экране должен быть ввод нескольких (но не всех) характеристик. Например, для приложения: название, автор, год. На первой активности разместите кнопку навигации для перемещения на следующий экран для продолжения ввода характеристик (например, с надписью «NEXT»). Значения, введенные пользователем, необходимо передавать через Intent в новую Activity и выводить в виде текста сверху следующей активности. Предусмотреть возможность возврата на предыдущие экраны и изменение введенных пользователем значений.
        Предусмотрите возможность сохранения состояния (при вызове onPause, onStop, закрытии приложения и изменении ориентации экрана).
        При построении UI используйте: разные типы Layout, Text, Spinner, DataPicker, Button, CheckBox, RadioButton и изображения «ITEM».
        UI должен отображаться корректно в обоих ориентациях экрана.
        Поменяйте стандартную иконку приложения.
*/


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