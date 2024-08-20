package com.example.lr4;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity {
    private EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        registerForContextMenu(editText1);
        editText2.setOnClickListener(v -> showPopup(v));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.editText1) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fillField:
                editText1.setText("Test");
                return true;
            case R.id.clearField:
                editText1.setText("");
                return true;
            case R.id.checkField:
                checkField();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            // Обработка PopUp меню
            return true;
        });
        popup.show();
    }

    private void checkField() {
        Snackbar.make(editText1, "Field Valid", Snackbar.LENGTH_LONG).show();
    }

    public void onNavigateToThird(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Proceed to the next page?")
                .setPositiveButton("Yes", (dialog, which) -> startActivity(new Intent(this, ThirdActivity.class)))
                .setNegativeButton("No", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}