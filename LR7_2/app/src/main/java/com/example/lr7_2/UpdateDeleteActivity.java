package com.example.lr7_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDeleteActivity extends AppCompatActivity {
    private EditText editText;
    private Button updateButton, deleteButton;
    private String recordKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        editText = findViewById(R.id.editText);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        String data = intent.getStringExtra("record");
        recordKey = intent.getStringExtra("recordKey");
        editText.setText(data);

        updateButton.setOnClickListener(v -> {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data").child(recordKey);
            databaseReference.setValue(editText.getText().toString());
            finish();
        });

        deleteButton.setOnClickListener(v -> {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data").child(recordKey);
            databaseReference.removeValue();
            finish();
        });
    }
}
