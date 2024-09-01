package com.example.lr6;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    private EditText editTextData;
    private TextView textViewOutput;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextData = findViewById(R.id.editTextData);
        textViewOutput = findViewById(R.id.textViewOutput);
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Button buttonSaveInternal = findViewById(R.id.buttonSaveInternal);
        Button buttonSaveExternal = findViewById(R.id.buttonSaveExternal);
        Button buttonSaveSQLite = findViewById(R.id.buttonSaveSQLite);
        Button buttonLoad = findViewById(R.id.buttonLoad);

        buttonSaveInternal.setOnClickListener(v -> saveInternal());
        buttonSaveExternal.setOnClickListener(v -> saveExternal());
        buttonSaveSQLite.setOnClickListener(v -> saveSQLite());
        buttonLoad.setOnClickListener(v -> load());
    }

    private void saveInternal() {
        String data = editTextData.getText().toString();
        try (FileOutputStream fos = openFileOutput("internal_storage.txt", MODE_PRIVATE)) {
            fos.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveToPreferences(data);
    }

    private void saveExternal() {
        String data = editTextData.getText().toString();
        File file = new File(Environment.getExternalStorageDirectory(), "external_storage.txt");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveToPreferences(data);
    }

    private void saveToPreferences(String data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("last_input", data);
        editor.apply();
    }

    private void saveSQLite() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String data = editTextData.getText().toString();

        ContentValues values = new ContentValues();
        values.put("data", data);
        db.insert("my_table", null, values);

        db.close();
        saveToPreferences(data);
    }

    private void load() {
        String lastInput = sharedPreferences.getString("last_input", "Нет данных");
        textViewOutput.setText(lastInput);
    }


    private void loadSQLite() {
        // Получение данных из SQLite и отображение в textViewOutput
    }

    private void updateSQLite(int id, String newData) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("data", newData);
        db.update("my_table", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    private void deleteSQLite(int id) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("my_table", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}