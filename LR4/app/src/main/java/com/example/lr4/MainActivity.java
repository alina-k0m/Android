package com.example.lr4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText emailField, secondField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.emailField);
        secondField = findViewById(R.id.secondField);

        findViewById(R.id.buttonFillEmail).setOnClickListener(v ->
                emailField.setText("test@iba.by"));

        findViewById(R.id.buttonClearFields).setOnClickListener(v -> {
            emailField.setText("");
            secondField.setText("");
        });

        findViewById(R.id.buttonCheckData).setOnClickListener(v ->
                Toast.makeText(this, "Данные проверены", Toast.LENGTH_SHORT).show());

        findViewById(R.id.buttonGoToSecond).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}
