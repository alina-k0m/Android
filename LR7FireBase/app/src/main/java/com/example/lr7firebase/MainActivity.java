package com.example.lr7firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList;
    private DatabaseReference databaseReference;
    private FirebaseFirestore firestore;
    private boolean useFirestore = false; //по умолчанию используем Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Button addButton = findViewById(R.id.addButton);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        CheckBox dataSourceCheckbox = findViewById(R.id.dataSourceCheckbox);

        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        //инициализация Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        firestore = FirebaseFirestore.getInstance();

        //загрузка данных
        loadData();

        //обработка нажатия кнопки
        addButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddItemActivity.class)));

        //обработка клика по элементам списка
        listView.setOnItemClickListener((parent, view, position, id) -> {
            //передать выбранный элемент в добавление/редактирование
            String selectedItem = dataList.get(position);
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            intent.putExtra("item", selectedItem);
            startActivity(intent);
        });

        //переключение между Realtime Database и Firestore
        dataSourceCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useFirestore = isChecked;
            loadData();
        });
    }

    private void loadData() {
        dataList.clear();
        if (useFirestore) {
            //загрузка данных из Firestore
            firestore.collection("items").get().addOnSuccessListener(queryDocumentSnapshots -> {
                for (DocumentSnapshot document : queryDocumentSnapshots) {
                    dataList.add(document.getString("name"));
                }
                adapter.notifyDataSetChanged();
            });
        } else {
            //загрузка данных из Realtime Database
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String item = dataSnapshot.getValue(String.class);
                        dataList.add(item);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
}