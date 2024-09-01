package com.example.lr6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private TextView outputTextView;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        outputTextView = findViewById(R.id.outputTextView);

        // Инициализация базы данных
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        // Загрузка последнего сохранённого значения
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String lastValue = prefs.getString("lastValue", "");
        editTextInput.setText(lastValue);

        findViewById(R.id.saveInternalButton).setOnClickListener(v -> saveInternal());
        findViewById(R.id.saveExternalButton).setOnClickListener(v -> saveExternal());
        findViewById(R.id.saveSQLiteButton).setOnClickListener(v -> saveSQLite());
        findViewById(R.id.loadButton).setOnClickListener(v -> loadData());
        findViewById(R.id.loadButton).setOnClickListener(v -> updateSQLite(int id, String newValue));
        findViewById(R.id.loadButton).setOnClickListener(v -> deleteSQLite());
    }

    private void saveInternal() {
        String data = editTextInput.getText().toString();
        try (FileOutputStream fos = openFileOutput("data.txt", Context.MODE_PRIVATE)) {
            fos.write(data.getBytes());
            // Сохранение в Shared Preferences
            saveLastValue(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveExternal() {
        String data = editTextInput.getText().toString();
        if (isExternalStorageWritable()) {
            File file = new File(Environment.getExternalStorageDirectory(), "data.txt");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(data.getBytes());
                saveLastValue(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveSQLite() {
        String data = editTextInput.getText().toString();
        database.execSQL("INSERT INTO Data (value) VALUES ('" + data + "')");
        saveLastValue(data);
    }


    private void updateSQLite(int id, String newValue) {
        database.execSQL("UPDATE Data SET value = '" + newValue + "' WHERE id = " + id);
    }

    private void deleteSQLite(int id) {
        database.execSQL("DELETE FROM Data WHERE id = " + id);
    }

    private void loadData() {
        StringBuilder output = new StringBuilder();

        // Загрузка из SQLite
        Cursor cursor = database.rawQuery("SELECT * FROM Data", null);
        while (cursor.moveToNext()) {
            output.append(cursor.getString(1)).append("\n");
        }
        cursor.close();

        // Загрузка последних данных
        SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        output.append("Последнее значение: ").append(prefs.getString("lastValue", ""));
        outputTextView.setText(output.toString());
    }

    private void saveLastValue(String value) {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putString("lastValue", value);
        editor.apply();
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, "MyDatabase", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Data (id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Data");
            onCreate(db);
        }
    }


}