package com.example.demofirebase;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
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

public class Send extends AppCompatActivity {
    private Database dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn_xacnhan;
        ImageButton btn_quaylai;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new Database(this);
//        db = dbHelper.initializeDatabaseFromAssets(this, "user.db");

        btn_xacnhan = findViewById(R.id.btn_xacnhan);

        btn_xacnhan.setOnClickListener(v -> {
            Intent myIntent = new Intent(Send.this, Newpass.class);
            startActivity(myIntent);
        });

        btn_quaylai = findViewById(R.id.btn_quaylai);
        btn_quaylai.setOnClickListener(v -> {
            Intent myIntent = new Intent(Send.this, Forgotpass.class);
            startActivity(myIntent);
        });
    }
}