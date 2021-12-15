package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarDBHelper extends SQLiteOpenHelper {

    final private static Integer VERSION = 1;
    final private Context context;

    /**
     * SQL syntax to create a table
     * will be used in the onCreate method
     */
    final private static String CREATE_QUERY = "CREATE TABLE " +
            DBParam.CarsParam.CAR_TABLE_NAME + "(" +
            DBParam.CarsParam.C_NAME    + " TEXT PRIMARY KEY," +
            DBParam.CarsParam.C_YEAR    + " INTEGER," +
            DBParam.CarsParam.C_TYPE    + " TEXT," +
            DBParam.CarsParam.C_MAKE    + " TEXT," +
            DBParam.CarsParam.C_MODEL   + " TEXT," +
            DBParam.CarsParam.C_NOTE    + " TEXT);";

    // Constructor for this class
    public CarDBHelper(Context context) {
        super(context, DBParam.CarsParam.CAR_TABLE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        ContentValues values = new ContentValues();
    }

    /**
     * Function to add a new car to the car's database
     * Will be used in DoInBackground.java
     * @param db
     * @param name
     * @param year
     * @param type
     * @param make
     * @param model
     * @param note
     */
    public void addNewCar (SQLiteDatabase db, String name, int year, String type, String make, String model, String note) {

        // get the value from the arguments and add to the appropriate place
        ContentValues ct = new ContentValues();
        ct.put(DBParam.CarsParam.C_NAME, name);
        ct.put(DBParam.CarsParam.C_YEAR, year);
        ct.put(DBParam.CarsParam.C_TYPE, type);
        ct.put(DBParam.CarsParam.C_MAKE, make);
        ct.put(DBParam.CarsParam.C_MODEL, model);
        ct.put(DBParam.CarsParam.C_NOTE, note);

        db.insert(DBParam.CarsParam.CAR_TABLE_NAME, null, ct);
    }

    /**
     * Function for retrieving each car information in car's database
     * Will be used in DoInBackground.java
     * @param db
     * @return cr
     */
    public Cursor getCar (SQLiteDatabase db) {

        // All columns of the database
        String [] allColumn = {
            DBParam.CarsParam.C_NAME,
            DBParam.CarsParam.C_YEAR,
            DBParam.CarsParam.C_TYPE,
            DBParam.CarsParam.C_MAKE,
            DBParam.CarsParam.C_MODEL,
            DBParam.CarsParam.C_NOTE
        };

        // Cursor object to get information from Database
        Cursor cr = db.query(DBParam.CarsParam.CAR_TABLE_NAME, allColumn, null, null, null,null, null );
        return cr;
    }

    /**
     * Function to update component of each individual car
     * Will be used in DoInBackground.java
     * @param db
     * @param old_name
     * @param new_name
     * @param new_year
     * @param new_type
     * @param new_make
     * @param new_model
     * @param new_note
     */
    public void updateCar (SQLiteDatabase db, String old_name, String new_name, int new_year, String new_type, String new_make, String new_model, String new_note) {

        ContentValues ct = new ContentValues();
        ct.put(DBParam.CarsParam.C_NAME, new_name);
        ct.put(DBParam.CarsParam.C_YEAR, new_year);
        ct.put(DBParam.CarsParam.C_TYPE, new_type);
        ct.put(DBParam.CarsParam.C_MAKE, new_make);
        ct.put(DBParam.CarsParam.C_MODEL, new_model);
        ct.put(DBParam.CarsParam.C_NOTE, new_note);

        String selection = DBParam.CarsParam.C_NAME + "=?";
        String [] selection_args = {old_name};
        db.update(DBParam.CarsParam.CAR_TABLE_NAME, ct, selection, selection_args);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
