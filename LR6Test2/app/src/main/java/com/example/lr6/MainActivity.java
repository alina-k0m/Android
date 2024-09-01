package com.example.lr6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private TextView textViewOutput;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        textViewOutput = findViewById(R.id.textViewOutput);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        //Загрузка последнего значения из Shared Preferences
        loadLastValue();

        findViewById(R.id.buttonSaveInternal).setOnClickListener(v -> saveInternal());
        findViewById(R.id.buttonSaveExternal).setOnClickListener(v -> saveExternal());
    }

    private void saveInternal() {
        String input = editTextInput.getText().toString();
        try (FileOutputStream fos = openFileOutput("internalData.txt", MODE_PRIVATE)) {
            fos.write(input.getBytes());
            saveToPreferences(input);
            updateOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveExternal() {
        String input = editTextInput.getText().toString();
        //сохранение во внешнее хранилище
        String filename = "externalData.txt";
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        try (FileOutputStream fos = new FileOutputStream(new File(getExternalFilesDir(null), filename))) {
            fos.write(input.getBytes());
            saveToPreferences(input);
            updateOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveToPreferences(String input) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastValue", input);
        editor.apply();
    }

    private void loadLastValue() {
        String lastValue = sharedPreferences.getString("lastValue", "");
        textViewOutput.setText(lastValue);
    }

    private void updateOutput() {
        String lastValue = sharedPreferences.getString("lastValue", "");
        textViewOutput.setText(lastValue);
    }
}