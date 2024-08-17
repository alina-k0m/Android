package com.example.lr2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Locale;


public class Activity3 extends AppCompatActivity {
    private CheckBox readCheckBox;
    private Button saveButton;
    private static final int PICK_IMAGE = 100;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        TextView textView = findViewById(R.id.textViewPrevious2);
        String bookTitle = getIntent().getStringExtra("bookTitle");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        String bookYear = getIntent().getStringExtra("bookYear");
        String genre = getIntent().getStringExtra("genre");
        String language = getIntent().getStringExtra("language");
        Integer readDateDay = getIntent().getIntExtra("readDateDay", 0);
        Integer readDateMonth = getIntent().getIntExtra("readDateMonth", 0);
        Integer readDateYear = getIntent().getIntExtra("readDateYear", 0);
        textView.setText("Book: " + bookTitle + "\nAuthor: " + bookAuthor + "\nYear: " + bookYear +
                "\ngenre: " + genre + "\nlanguage: " + language +
                "\nreadDateDay: " + readDateDay + "." + readDateMonth + "." + readDateYear);
        readCheckBox = findViewById(R.id.readCheckBox);
        saveButton = findViewById(R.id.saveButton);


        Button previousButton = findViewById(R.id.previousButton);

        previousButton.setOnClickListener(v -> finish());

        imageView = findViewById(R.id.imageView);
        Button btnUpload = findViewById(R.id.btnUpload);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri); //Отображаем загруженное изображение
        }


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBookData();
            }
        });
    }

    private void saveBookData() {
        boolean isRead = readCheckBox.isChecked();
        // Здесь вы можете сохранять данные в базу данных или передавать их другим способом
        Toast.makeText(this, "Сохранено: " + (isRead ? "Прочитана" : "Не прочитана"), Toast.LENGTH_SHORT).show();
    }
}
