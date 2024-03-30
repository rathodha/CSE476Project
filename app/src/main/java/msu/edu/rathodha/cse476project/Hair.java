package msu.edu.rathodha.cse476project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Hair extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.getName(),
                DatabaseHelper.getServices(),
                DatabaseHelper.getAddress(),
                DatabaseHelper.getPrice()
        };

        Cursor cursor = db.query(
                DatabaseHelper.getTableName(),
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.getName()));
            String serviceOffered = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.getServices()));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.getAddress()));
            double pricePerHour = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.getPrice()));

            TextView nameTextView = findViewById(R.id.nameTextView);
            TextView serviceOfferedTextView = findViewById(R.id.serviceOfferedTextView);
            TextView addressTextView = findViewById(R.id.addressTextView);
            TextView pricePerHourTextView = findViewById(R.id.pricePerHourTextView);

            nameTextView.setText(name);
            serviceOfferedTextView.setText(serviceOffered);
            addressTextView.setText(address);
            pricePerHourTextView.setText(String.valueOf(pricePerHour));

        } else {
            Toast.makeText(this, "No data found in the database", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
    }
}