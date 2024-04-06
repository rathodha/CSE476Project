package msu.edu.rathodha.cse476project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "services.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "services";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SERVICE_OFFERED = "service_offered";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PRICE_PER_HOUR = "price_per_hour";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SERVICE_OFFERED + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PRICE_PER_HOUR + " REAL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addService(String name, String serviceOffered, String address, double pricePerHour) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_SERVICE_OFFERED + ", " +
                COLUMN_ADDRESS + ", " +
                COLUMN_PRICE_PER_HOUR + ") VALUES ('" +
                name + "', '" +
                serviceOffered + "', '" +
                address + "', " +
                pricePerHour + ")";
        db.execSQL(insertQuery);
        db.close();
    }

    public Cursor getAllServices() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getServiceByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=?", new String[]{name});
    }
}
