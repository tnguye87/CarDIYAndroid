package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

public class DoInBackGround extends AsyncTask<String, Cars, String>  {

    Context ctx;
    Activity activity;
    ListView lv;
    CarAdapter carAdapter;

    //Constructor for this class
    DoInBackGround (Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        String toReturn = "";

        // used for car's info
        String name, type, make, model, note;
        int year = 0;

        // used for service's info
        String s_name, s_cur_date, s_next_date, s_note;
        int s_cur_mile = 0, s_next_mile = 0;

        CarDBHelper CarDBHelper = new CarDBHelper(ctx);
        ServiceDBHelper SerDBHelper = new ServiceDBHelper(ctx);

        /**
         * call when want to add new car to the car's database
         * which is used in the AddNewCar.java
         */

        if (method.equalsIgnoreCase("add_car")) {
            name = params[1]; // don't need to check for empty name because it is done in AddNewCar.java
            SQLiteDatabase db = CarDBHelper.getWritableDatabase();
            Cursor cr = CarDBHelper.getCar(db);
            boolean hasDup = false; //use for determine duplicate car's name

            /**
             * Check for duplicate car's name
             * Because it is the Primary key, it must be distinct
             */
            while (cr.moveToNext()) {
                String name_temp = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_NAME));
                if(name_temp.equalsIgnoreCase(name)) {
                    hasDup = true;
                    break;
                }
            }

            if (hasDup) toReturn = "Name *" + name + "* already exists in car's database\nPlease enter different car's name";
            else {
                if (!params[2].equals("")) year = Integer.parseInt(params[2]);
                name = convert(params[1]);
                type = convert(params[3]);
                make = convert(params[4]);
                model = convert(params[5]);
                note = convert(params[6]);
                CarDBHelper.addNewCar(db, name, year, type, make, model, note);
                toReturn = "Car *" + name + "* added to car's database";
            }
        }

        /**
         * Call when want to display all cars data from the cars_database
         * allCars_listView from the view_my_garage.xml
         * Will be used in the ViewMyGarage.java
         */
        else if (method.equals("get_info")) {

            lv = (ListView) activity.findViewById(R.id.allCars_listView);
            SQLiteDatabase db = CarDBHelper.getReadableDatabase();
            Cursor cr = CarDBHelper.getCar(db);
            carAdapter = new CarAdapter(ctx, R.layout.view_my_garage);

            // get data from Cursor cr and display it on ListView
            while (cr.moveToNext()) {
                name = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_NAME));
                year = cr.getInt(cr.getColumnIndex(DBParam.CarsParam.C_YEAR));
                type = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_TYPE));
                make = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_MAKE));
                model = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_MODEL));
                note = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_NOTE));

                Cars car = new Cars(name, type, make, model, note, year);
                publishProgress(car);
            }
            toReturn = "get_info";
        }

        /**
         * call when want to edit one or more element of a car from the cars_database
         * will be used in the ViewOneCarInfo.java
         */
        else if (method.equals("update_car")) {
            SQLiteDatabase db = CarDBHelper.getWritableDatabase();

            String old_name = params [1];
            name = convert(params[2]);
            if (!params[3].equals("")) year = Integer.parseInt(params[3]);
            type = params[4];
            make = params[5];
            model = params [6];
            note = params[7];

            CarDBHelper.updateCar(db, old_name, name, year, type, make, model, note);
            toReturn = "Update " + name + "'s Car Information";
        }

        /**
         * Call when want to add new service to the service's database
         * which is used in the AddNewService.java
         */
        else if (method.equals("add_service")) {
            s_name = params [1];
            SQLiteDatabase db = SerDBHelper.getWritableDatabase();
            Cursor cr = SerDBHelper.getService(db);
            boolean hasDup = false; //used to check duplicate service's name

            /**
             * Check for duplicate service's name
             * Because the service's name is made up by combine car's name and service's name
             * And a Car can't have 2 same services (ex: one car can't have 2 oil change)
             */
            while (cr.moveToNext()) {
                String name_temp = cr.getString(cr.getColumnIndex(DBParam.ServiceParam.S_NAME));
                if (name_temp.equalsIgnoreCase(s_name)) {
                    hasDup = true;
                    break;
                }
            }
            if(hasDup) {
                String [] temp = s_name.split("'s ");
                String ser = "";
                ser = temp[1];
                /*
                for (int i = 1; i < temp.length; i++) {
                    if (i == temp.length-1) ser = ser + temp[i];
                    else ser = ser + temp[i] + " ";
                }
                 */
                toReturn = temp[0] + "'s car already has " + ser + " done!!!!";
            }
            else {
                s_name = convert(params[1]);
                s_cur_date = convert(params[2]);
                if (!params[3].equals("")) s_cur_mile = Integer.parseInt(params[3]);
                s_next_date = convert(params[4]);
                if (!params[5].equals("")) s_next_mile = Integer.parseInt(params[5]);
                s_note = convert(params[6]);
                SerDBHelper.addNewService(db, s_name, s_cur_date,s_cur_mile,s_next_date,s_next_mile, s_note);
                toReturn = "*" + s_name + "* added to service's database";
            }
        }

        else if (method.equals("get_service")) {

        }

        return toReturn;
    }

    /**
     * Call 'add' method from CarAdapter.java
     * @param values
     */
    @Override
    protected void onProgressUpdate(Cars... values) {
        carAdapter.add(values[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("get_info")) lv.setAdapter(carAdapter);
        else if (!result.equals(""))Toast.makeText(ctx, result,Toast.LENGTH_LONG).show();
    }

    /**
     * To make the String input from user to look nicer and consist when display in database
     * @param str
     * @return
     */
    static String convert(String str) {

        // Create a char array of given String
        char ch[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {

            // If first character of a word is found
            if (i == 0 && ch[i] != ' ' ||
                    ch[i] != ' ' && ch[i - 1] == ' ') {

                // If it is in lower-case
                if (ch[i] >= 'a' && ch[i] <= 'z') {

                    // Convert into Upper-case
                    ch[i] = (char)(ch[i] - 'a' + 'A');
                }
            }

            // If apart from first character
            // Any one is in Upper-case
            else if (ch[i] >= 'A' && ch[i] <= 'Z')

                // Convert into Lower-Case
                ch[i] = (char)(ch[i] + 'a' - 'A');
        }

        // Convert the char array to equivalent String
        String st = new String(ch);
        return st;
    }

}
