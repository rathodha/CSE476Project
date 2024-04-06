package msu.edu.rathodha.cse476project;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "service_providers";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SERVICE_OFFERED = "service_offered";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PRICE_PER_HOUR = "price_per_hour";

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

        insertInitialValues(db);
    }

    private void insertInitialValues(SQLiteDatabase db) {
        ContentValues Hairvalues = new ContentValues();
        Hairvalues.put(COLUMN_NAME, "John Doe");
        Hairvalues.put(COLUMN_SERVICE_OFFERED, "Haircuts, Coloring");
        Hairvalues.put(COLUMN_ADDRESS, "Wilson Hall, MSU");
        Hairvalues.put(COLUMN_PRICE_PER_HOUR, 25.0);
        db.insert(TABLE_NAME, null, Hairvalues);

        ContentValues nailsValues = new ContentValues();
        nailsValues.put(COLUMN_NAME, "Jane Smith");
        nailsValues.put(COLUMN_SERVICE_OFFERED, "Nail Art, Manicure");
        nailsValues.put(COLUMN_ADDRESS, "Main Street, MSU");
        nailsValues.put(COLUMN_PRICE_PER_HOUR, 30.0);
        db.insert(TABLE_NAME, null, nailsValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getName() {
        return COLUMN_NAME;
    }

    public static String getServices() {
        return COLUMN_SERVICE_OFFERED;
    }

    public static String getAddress() {
        return COLUMN_ADDRESS;
    }

    public static String getPrice() {
        return COLUMN_PRICE_PER_HOUR;
    }
}
