package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.LoginActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText firstNameEditText, lastNameEditText, usernameEditText, passwordEditText, departmentEditText;
    private RadioButton maleRadioButton, femaleRadioButton;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        dbHelper = new MyDatabaseHelper(this);

        firstNameEditText = findViewById(R.id.fName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        usernameEditText = findViewById(R.id.editTextusername);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        departmentEditText = findViewById(R.id.department);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);

        Button signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    public void signUp() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();
        String department = departmentEditText.getText().toString().trim();
        String gender = maleRadioButton.isChecked() ? "Male" : "Female";

        // Check if any fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || department.isEmpty()) {
            // Show an error message indicating that all fields are required
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the password meets certain criteria (e.g., minimum length)
        if (password.length() < 6) {
            // Show an error message indicating that the password is too short
            Toast.makeText(this, "Password should be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user data into the database
        long newRowId = dbHelper.addUser(firstName, lastName, username, password, gender, department);

        // Check if registration was successful
        if (newRowId != -1L) {
            // Registration successful, display a success message
            Toast.makeText(this, "User registered successfully. You can now log in.", Toast.LENGTH_SHORT).show();

            // Optionally, navigate to another activity after sign-up
            // Intent intent = new Intent(this, AnotherActivity.class);
            // startActivity(intent);
        } else {
            // Registration failed, handle appropriately
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
