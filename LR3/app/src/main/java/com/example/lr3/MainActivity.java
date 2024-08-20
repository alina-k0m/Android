package com.example.lr3;

/*
На основе ListView заработайте список из электронных почт и номеров телефона. При клике на которые будет вызываться соответствующие приложение.
        Доработать дизайн RecyclerView. Изменить для хранения имени, номера телефона и электронной почты.
        По клику открыть приложения для звонков.
        Добавить вариант поделится контактом по долгому нажатию (отправка имени, номера телефона на указанную почту)
*/


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John Snow", "1234567890", "john@example.com"));
        contacts.add(new Contact("Ariana Grande", "987654", "ariana@example.com"));
        contacts.add(new Contact("Tom Ford", "654321", "tom@example.com"));
        contacts.add(new Contact("Иван Иванов", "123456", "ivan@example.com"));
        contacts.add(new Contact("Vanessa Paradi", "456789", "vanessa@example.com"));


        ContactsAdapter adapter = new ContactsAdapter(this, contacts);
        recyclerView.setAdapter(adapter);
    }
}