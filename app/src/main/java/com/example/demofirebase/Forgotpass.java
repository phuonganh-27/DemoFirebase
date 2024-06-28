package com.example.demofirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton btn_quaylai;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgotpass);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_quaylai = findViewById(R.id.btn_quaylai);

        btn_quaylai.setOnClickListener(v -> {
            Intent myIntent = new Intent(Forgotpass.this, MainActivity.class);
            startActivity(myIntent);
        });

        String email = "user@example.com"; // Thay email của người dùng cần đặt lại mật khẩu
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Thực hiện các hành động khi gửi email thành công, ví dụ như hiển thị thông báo
                            Toast.makeText(Forgotpass.this, "Đã gửi email đặt lại mật khẩu!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Xử lý lỗi khi gửi email đặt lại mật khẩu không thành công
                            Toast.makeText(Forgotpass.this, "Gửi email đặt lại mật khẩu thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}