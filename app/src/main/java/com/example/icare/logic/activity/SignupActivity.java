package com.example.icare.logic.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.util.UUID;

public class SignupActivity extends AppCompatActivity {

    EditText editUsername, editEmail, editPassword, editConfirmPassword;

    Button btnSignup;
    private AppDatabase appDatabase;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        appDatabase = AppDatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        editUsername = findViewById(R.id.edit_text_username);
        editEmail = findViewById(R.id.edit_text_email);
        editPassword = findViewById(R.id.edit_text_pass);
        editConfirmPassword = findViewById(R.id.edit_text_confirm_pass);
        btnSignup = findViewById(R.id.register_button);

        btnSignup.setOnClickListener(v -> {
            String username = editUsername.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            String confirmPassword = editConfirmPassword.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            User existingUserByEmail = appDatabase.userDao().getUserByEmail(email);
            if (existingUserByEmail != null) {
                Toast.makeText(this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.contains(" ")) {
                Toast.makeText(this, "Mật khẩu không được chứa khoảng trắng", Toast.LENGTH_SHORT).show();
                return;
            }

            User newUser = new User();

            // Generate a unique ID and GUARANTEE it's not in the database
            String uniqueID;
            do {
                uniqueID = UUID.randomUUID().toString();
            } while (appDatabase.userDao().getUserById(uniqueID) != null);
            // This loop will run until a truly unique ID is generated.
            // In reality, it will only ever run once.

            newUser.setUserId(uniqueID);
            newUser.setUserName(username);
            newUser.setEmail(email);
            newUser.setPassword(password); // Remember to hash passwords in a real app

            try {
                appDatabase.userDao().insert(newUser);
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        TextView loginLink = findViewById(R.id.login_link);
        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
