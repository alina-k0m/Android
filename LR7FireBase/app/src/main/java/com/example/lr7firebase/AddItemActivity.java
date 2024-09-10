package com.example.lr7firebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AddItemActivity extends AppCompatActivity {
    private EditText editText;
    private DatabaseReference databaseReference; //для Realtime
    private FirebaseFirestore firestore; //для Firestore
    private boolean useFirestore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editText = findViewById(R.id.editText);
        Button saveButton = findViewById(R.id.saveButton);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        CheckBox dataSourceCheckbox = findViewById(R.id.dataSourceCheckbox);

        dataSourceCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useFirestore = isChecked;
        });

        //инициализация Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        firestore = FirebaseFirestore.getInstance();

        //получение переданного элемента для редактирования
        String item = getIntent().getStringExtra("item");
        if (item != null) {
            editText.setText(item);
        }

        //метод для сохранения
        saveButton.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (useFirestore) {
                //сохранение в Firestore
                Map<String, Object> data = new HashMap<>();
                data.put("name", text);
                firestore.collection("items").add(data).addOnSuccessListener(documentReference -> finish());
            } else {
                //сохранение в Realtime Database
                databaseReference.push().setValue(text).addOnSuccessListener(aVoid -> finish());
            }
        });

        //метод для обновления
        buttonUpdate.setOnClickListener(v -> {
            String newText = editText.getText().toString();
            if (useFirestore) {
                //сохранение в Firestore
                Map<String, Object> data = new HashMap<>();
                data.put("name", newText);
                firestore.collection("items").add(data).addOnSuccessListener(documentReference -> finish());
            } else {
                //сохранение в Realtime Database
                databaseReference.push().setValue(newText).addOnSuccessListener(aVoid -> finish());
            }
        });

        //метод для удаления
        buttonDelete.setOnClickListener(v -> {
            databaseReference.child(item).removeValue();
            finish(); //закрыть Activity
        });
    }
}
