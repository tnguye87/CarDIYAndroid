package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ServiceDBHelper  extends SQLiteOpenHelper {

    final private static Integer VERSION = 1;
    final private Context context;

    /**
     * SQL syntax to create a table
     * will be used in the onCreate method
     */
    final private static String CREATE_QUERY = "CREATE TABLE " +
            DBParam.ServiceParam.SERVICE_TABLE_NAME + "(" +
            DBParam.ServiceParam.S_NAME + " TEXT PRIMARY KEY," +
            DBParam.ServiceParam.S_CUR_DATE + " TEXT," +
            DBParam.ServiceParam.S_CUR_MILES + " INTEGER," +
            DBParam.ServiceParam.S_NEXT_DATE + " TEXT," +
            DBParam.ServiceParam.S_NEXT_MILES + " INTEGER," +
            DBParam.ServiceParam.S_NOTE + " TEXT);";

    // Constructor for this class
    public ServiceDBHelper(Context context) {
        super(context, DBParam.ServiceParam.SERVICE_TABLE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        ContentValues values = new ContentValues();
    }

    /**
     * Function to add a new service to the service's database
     * Will be used in DoInBackground.java
     *
     * @param db
     * @param name
     * @param curDate
     * @param curMile
     * @param nextDate
     * @param nextMile
     * @param note
     */
    public void addNewService(SQLiteDatabase db, String name, String curDate, int curMile, String nextDate, int nextMile, String note) {

        // get the value from the arguments and add to the appropriate place
        ContentValues ct = new ContentValues();
        ct.put(DBParam.ServiceParam.S_NAME, name);
        ct.put(DBParam.ServiceParam.S_CUR_DATE, curDate);
        ct.put(DBParam.ServiceParam.S_CUR_MILES, curMile);
        ct.put(DBParam.ServiceParam.S_NEXT_DATE, nextDate);
        ct.put(DBParam.ServiceParam.S_NEXT_MILES, nextMile);
        ct.put(DBParam.ServiceParam.S_NOTE, note);

        db.insert(DBParam.ServiceParam.SERVICE_TABLE_NAME, null, ct);
    }

    /**
     * Function for retrieving each service information in service's database
     * Will be used in DoInBackground.java
     *
     * @param db
     * @return cr
     */
    public Cursor getService(SQLiteDatabase db) {

        // All columns of the database
        String[] allColumn = {
                DBParam.ServiceParam.S_NAME,
                DBParam.ServiceParam.S_CUR_DATE,
                DBParam.ServiceParam.S_CUR_MILES,
                DBParam.ServiceParam.S_NEXT_DATE,
                DBParam.ServiceParam.S_NEXT_MILES,
                DBParam.ServiceParam.S_NOTE
        };

        // Cursor object to get information from Database
        Cursor cr = db.query(DBParam.ServiceParam.SERVICE_TABLE_NAME, allColumn, null, null, null, null, null);
        return cr;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

