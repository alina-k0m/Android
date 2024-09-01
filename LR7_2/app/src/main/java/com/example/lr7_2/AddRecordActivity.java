package com.example.lr7_2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecordActivity extends AppCompatActivity {
    private EditText editText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        editText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            String data = editText.getText().toString();
            if (!data.isEmpty()) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data");
                databaseReference.push().setValue(data);
                finish();
            }
        });
    }
}

