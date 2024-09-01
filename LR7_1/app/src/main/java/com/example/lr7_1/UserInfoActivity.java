package com.example.lr7_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {
    private TextView textUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        textUserInfo = findViewById(R.id.textUserInfo);
        Button buttonLogout = findViewById(R.id.buttonLogout);

        String username = getIntent().getStringExtra("USERNAME");
        textUserInfo.setText("Пользователь: " + username);

        buttonLogout.setOnClickListener(v -> {
            finish(); // Возвращение на экран логина
        });
    }
}
