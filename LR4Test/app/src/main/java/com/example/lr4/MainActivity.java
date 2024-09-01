package com.example.lr4;

/*
Меню, диалоги и нотификация в Android
    На первой странице добавить меню параметров выполняющие следующие действия:
        Заполнить первое поле (как пример значение почты меняется на test@iba.by),
        Заполнить второе поле,
        Заполнить всё,
        Очистить данные (сделать динамическим те показывать в меню, когда хотя бы одно поле заполнено),
        Проверить данные (Результаты проверки отобразить через Toast).
    На второй странице для первого поля добавить контекстное меню с функциями аналогичными первому заданию:
        Заполнить поле,
        Очистить поле,
        Проверить данные (Результаты проверки отобразить через SnackBar).
    На второй странице для второго поля добавить PopUp меню с функциями аналогичными предыдущему заданию.
    Результаты проверки дополнительно отображать в уведомления.
    Перед переходом со второй страницы на третью добавить AlertDialog
    и в случае подтверждения (setPositiveButton) производить переход. Добавить изображение в диалог.
    На второй странице добавить контекстное меню для выбора даты и времени. Результаты выбора
    отображать в виде SnackBar.
    Добавить собственный Toast при переходе с первой страницы на вторую
    Добавить обработчик событий для SnackBar. В обработчике событий вызывать уведомление
    с тестом “Notification from snackbar”
*/

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        this.menu = menu;
        updateMenu();
        return true;
    }

    private void updateMenu() {
        boolean anyFieldFilled = isAnyFieldFilled();
        menu.findItem(R.id.clear).setVisible(anyFieldFilled);
        menu.findItem(R.id.checkData).setVisible(anyFieldFilled);
    }

    private boolean isAnyFieldFilled() {
        return !editText1.getText().toString().isEmpty() || !editText2.getText().toString().isEmpty();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fillEmail:
                editText1.setText("test@iba.by");
                return true;
            case R.id.fillSecond:
                editText2.setText("Sample Text");
                return true;
            case R.id.fillAll:
                editText1.setText("test@iba.by");
                editText2.setText("Sample Text");
                return true;
            case R.id.clear:
                editText1.setText("");
                editText2.setText("");
                updateMenu();
                return true;
            case R.id.checkData:
                verifyData();
                return true;
            case R.id.navigateToSecond:
                startActivity(new Intent(this, SecondActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void verifyData() {
        Toast.makeText(this, "Data valid", Toast.LENGTH_SHORT).show(); // Ваша логика проверки
    }
}
