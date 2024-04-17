package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayUser extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayuser);

        dbHelper = new MyDatabaseHelper(this);

        // Get all users from the database
        Cursor cursor = dbHelper.getAllUsers();

        // Find the table layout
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Check if the cursor is valid and contains data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve user data from the cursor
                @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_FIRST_NAME));
                @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_LAST_NAME));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_USERNAME));

                // Create a new table row
                TableRow row = new TableRow(this);

                // Create and add TextViews for each user attribute
                TextView firstNameTextView = new TextView(this);
                firstNameTextView.setText(firstName);
                row.addView(firstNameTextView);

                TextView lastNameTextView = new TextView(this);
                lastNameTextView.setText(lastName);
                row.addView(lastNameTextView);

                TextView usernameTextView = new TextView(this);
                usernameTextView.setText(username);
                row.addView(usernameTextView);

                // Add the row to the table layout
                tableLayout.addView(row);
            } while (cursor.moveToNext());

            cursor.close(); // Close the cursor to avoid memory leaks
        }
    }
}
