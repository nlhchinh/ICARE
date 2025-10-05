package com.example.icare.logic.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.icare.R;
import com.example.icare.logic.database.AppDatabase;
import com.example.icare.logic.database.AppDatabaseClient;
import com.example.icare.logic.model.User;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin;
    private AppDatabase appDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout); // First, set the layout

        // Initialize the database instance
        appDatabase = AppDatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        // Initialize views from the layout
        editEmail = findViewById(R.id.log_email);
        editPassword = findViewById(R.id.log_pass);
        btnLogin = findViewById(R.id.login_button);

        // Set the click listener for the login button
        btnLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the user exists in the database
            User user = appDatabase.userDao().login(email, password);

            if (user != null) {

                // Save the userId to SharedPreferences
                String userId = user.getUserId();
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("userId", userId);
                editor.apply();

                // Login successful
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                // FIX: Navigate to the OptionActivity
                Intent intent = new Intent(LoginActivity.this, OptionActivity.class);
                startActivity(intent);
                finish(); // Finish LoginActivity so the user can't go back to it
            } else {
                // Login failed
                Toast.makeText(this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the click listener for the "Register" link
        TextView registerLink = findViewById(R.id.register_link);
        registerLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
