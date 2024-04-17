package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        dbHelper = new MyDatabaseHelper(this);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        TextView createAccountTextView = findViewById(R.id.textViewLogin);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> login());

        // Set OnClickListener for the TextView to navigate to SignUpActivity
        createAccountTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void login() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();

        // Check if any fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            // Show an error message indicating that all fields are required
            Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verify if the user exists in the database
        if (isValidUser(username, password)) {
            // Login successful, navigate to the main activity or another activity
            Intent intent = new Intent(LoginActivity.this, DisplayUser.class);
            startActivity(intent);
            finish(); // Finish the login activity to prevent returning to it by pressing the back button
        } else {
            // Login failed, show an error message
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidUser(String username, String password) {
        Cursor cursor = dbHelper.getUserByUsernameAndPassword(username, password);
        boolean isValid = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return isValid;
    }
}
