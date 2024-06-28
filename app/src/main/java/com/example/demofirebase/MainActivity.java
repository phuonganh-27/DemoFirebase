package com.example.demofirebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText inputemail, inputpassword;
    Button btnLogin;
    TextView forgotPassword;
    Database dbHelper;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new Database(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputemail = findViewById(R.id.inputemail);
        inputpassword = findViewById(R.id.inputpassword);
        btnLogin = findViewById(R.id.btnLogin);
        dbHelper = new Database(this);


        forgotPassword=findViewById(R.id.forgotPassword);
        forgotPassword.setBackgroundColor(Color.TRANSPARENT);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Forgotpass.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbHelper.checkLogin(email, password)) {
            // Lưu trữ email trong SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("SessionPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email);
            editor.apply();

            // Đăng nhập thành công, chuyển hướng sang màn hình chính hoặc màn hình khác
            Intent intent = new Intent(MainActivity.this, Forgotpass.class);
            startActivity(intent);
            finish(); // Kết thúc Activity hiện tại sau khi chuyển hướng
        } else {
            // Thông báo lỗi khi đăng nhập không thành công
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
        }
    }
}