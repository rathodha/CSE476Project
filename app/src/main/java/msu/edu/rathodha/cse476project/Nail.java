package msu.edu.rathodha.cse476project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Nail extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView nameTextView, serviceOfferedTextView, addressTextView, pricePerHourTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nail);

        // Instantiate the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize TextViews
        nameTextView = findViewById(R.id.NailNameTextView);
        serviceOfferedTextView = findViewById(R.id.NailServiceOfferedTextView);
        addressTextView = findViewById(R.id.NailAddressTextView);
        pricePerHourTextView = findViewById(R.id.NailPricePerHourTextView);

        // Insert nail service data into the database
        insertNailServiceData();

        // Display nail service data from the database
        displayNailServiceData();
    }

    private void insertNailServiceData() {
        // Insert nail service data into the database
        databaseHelper.addService("John Doe", "Manicure", "456 Elm St", 30.0);
    }

    @SuppressLint("SetTextI18n")
    private void displayNailServiceData() {
        // Retrieve nail service data from the database
        Cursor cursor = databaseHelper.getServiceByName("John Doe");

        // Check if cursor is not null and move to the first row
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve data from the cursor for each column
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            @SuppressLint("Range") String serviceOffered = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVICE_OFFERED));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS));
            @SuppressLint("Range") double pricePerHour = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRICE_PER_HOUR));

            // Display data in TextViews
            nameTextView.setText(name);
            serviceOfferedTextView.setText(serviceOffered);
            addressTextView.setText(address);
            pricePerHourTextView.setText(String.valueOf(pricePerHour));

            // Close the cursor
            cursor.close();
        } else {
            // Handle the case where the cursor is empty (no matching data found)
            // For example, you can display a message or handle it in another way
            // Here, we're just setting default values or leaving the TextViews empty
            nameTextView.setText("No data found");
            serviceOfferedTextView.setText("");
            addressTextView.setText("");
            pricePerHourTextView.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        // Close the database connection when the activity is destroyed
        databaseHelper.close();
        super.onDestroy();
    }

    public void onBackClick(View view) {
        Intent intent = new Intent(this, InitialPage.class);
        startActivity(intent);
        finish();
    }
}
