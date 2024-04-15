package msu.edu.rathodha.cse476project;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Hair extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView nameTextView, serviceOfferedTextView, addressTextView, pricePerHourTextView;

    private LocationManager locationManager = null;
    private double toLatitude = 0;
    private double toLongitude = 0;
    private SharedPreferences settings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair);

        // Instantiate the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize TextViews
        nameTextView = findViewById(R.id.HairNameTextView);
        serviceOfferedTextView = findViewById(R.id.HairServiceOfferedTextView);
        addressTextView = findViewById(R.id.HairAddressTextView);
        pricePerHourTextView = findViewById(R.id.HairPricePerHourTextView);

        // Insert nail service data into the database
        insertHairServiceData();

        // Display nail service data from the database
        displayHairServiceData();

        settings = getSharedPreferences("my.app.packagename_preferences", Context.MODE_PRIVATE);
        toLatitude = Double.parseDouble("42.722835");
        toLongitude = Double.parseDouble("-84.489046");

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                // Also, dont forget to add overriding
                // public void onRequestPermissionsResult(int requestCode, String[] permissions,
                // int[] grantResults)
                // to handle the case where the user grants the permission.
                ActivityCompat.requestPermissions(this, new
                        String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        Button bookButton = findViewById(R.id.bookButton);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hair.this, Request.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void startGoogleMaps(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + toLatitude + "," + toLongitude + "&mode=" + "d");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void insertHairServiceData() {
        // Insert nail service data into the database
        databaseHelper.addService("Sam Smith", "Haircut", "East Wilson Hall, MSU", 20.0);
    }

    @SuppressLint("SetTextI18n")
    private void displayHairServiceData() {
        // Retrieve nail service data from the database
        Cursor cursor = databaseHelper.getServiceByName("Sam Smith");

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
