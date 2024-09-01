package com.example.lr4;

import android.widget.EditText;
import android.widget.PopupMenu;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity {
    private EditText fieldOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fieldOne = findViewById(R.id.fieldOne);

        fieldOne.setOnLongClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            popup.getMenuInflater().inflate(R.menu.context_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.fillField:
                        fieldOne.setText("Заполнено");
                        return true;
                    case R.id.clearField:
                        fieldOne.setText("Очищено");
                        return true;
                    case R.id.checkData:
                        Snackbar.make(v, "Данные проверены", Snackbar.LENGTH_LONG).show();
                        return true;
                    default:
                        return false;
                }
            });
            popup.show();
            return true;
        });
    }
}