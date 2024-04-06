package msu.edu.rathodha.cse476project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Nail extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nail);

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
                DatabaseHelper.getServices() + " LIKE ?",
                new String[]{"Nail Art, Manicure"},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.getName()));
            String serviceOffered = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.getServices()));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.getAddress()));
            double pricePerHour = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.getPrice()));

            TextView nameTextView = findViewById(R.id.NailNameTextView);
            TextView serviceOfferedTextView = findViewById(R.id.NailServiceOfferedTextView);
            TextView addressTextView = findViewById(R.id.NailAddressTextView);
            TextView pricePerHourTextView = findViewById(R.id.NailPricePerHourTextView);

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
