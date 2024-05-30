package com.example.lr1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etHeight;
    private RadioGroup rgGender;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.et_height);
        rgGender = findViewById(R.id.rg_gender);
        Button btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIdealWeight();
            }
        });
    }

    private void calculateIdealWeight() {
        String heightStr = etHeight.getText().toString();
        if (heightStr.isEmpty()) {
            tvResult.setText("Введите рост");
            return;
        }

        int height = Integer.parseInt(heightStr);
        int selectedId = rgGender.getCheckedRadioButtonId();

        if (selectedId == -1) {
            tvResult.setText("Выберите пол");
            return;
        }

        double idealWeight;
        if (selectedId == R.id.rb_male) {
            idealWeight = height - 100 - (height - 150) / 4;
        } else {
            idealWeight = height - 100 - (height - 150) / 2;
        }

        tvResult.setText(String.format("Ваш идеальный вес: %.1f кг", idealWeight));
    }
}