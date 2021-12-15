package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewOneCarInfo extends AppCompatActivity {
    EditText c_name, c_year, c_type, c_make, c_model, c_note;
    String name, year, type, make, model, note;
    String user_select_car; //used to get the user selected car from ViewMyGarage.java
    TextView tv;
    public final static String A_CAR_SERVICES_MESSAGE = "edu.gmu.cs477.fall2020.final_projecttnguye87.A_CAR_SERVICES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_one_car_info);

        c_name = (EditText) findViewById(R.id.c_name);
        c_year = (EditText) findViewById(R.id.c_year);
        c_type = (EditText) findViewById(R.id.c_type);
        c_make = (EditText) findViewById(R.id.c_make);
        c_model = (EditText) findViewById(R.id.c_model);
        c_note = (EditText) findViewById(R.id.c_notes);
        tv = (TextView) findViewById(R.id.textView);

        // retrieve car's name selected by user from the ViewMyGarage.java
        Intent i = getIntent();
        user_select_car = i.getStringExtra(ViewMyGarage.VIEW_ONE_CAR_MESSAGE);

        CarDBHelper carDbHelper = new CarDBHelper(this);
        SQLiteDatabase db = carDbHelper.getReadableDatabase();
        Cursor cr = carDbHelper.getCar(db);

        /**
         * loop through all car in the car's database and get the right car data
         * update all of the "set text" of the "edit texts"
         * */
        while (cr.moveToNext()) {
            String name_temp = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_NAME));
            if (name_temp.equalsIgnoreCase(user_select_car)) {

                year = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_YEAR));
                type = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_TYPE));
                make = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_MAKE));
                model = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_MODEL));
                note = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_NOTE));

                tv.setText(user_select_car.toUpperCase() + "'s CAR INFO");
                c_name.setText(user_select_car);
                if (!year.equals("0"))c_year.setText(year);
                c_type.setText(type);
                c_make.setText(make);
                c_model.setText(model);
                c_note.setText(note);

                Button btnUp = (Button) findViewById(R.id.btnUpdate);
                Button btnSer = (Button) findViewById(R.id.btnServices);
                btnUp.setText("UPDATE " + user_select_car + "'s car");
                btnSer.setText("View " + user_select_car+ "'s Services");
                break;
            }
        }
    }


    public void onBackPressed (View v) {
        Intent i = new Intent(ViewOneCarInfo.this, MainActivity.class);
        startActivity(i);
    }

    /**
     * Function to enable the update button
     * @param view
     */
    public void updateCar (View view) {
        name = c_name.getText().toString(); //user input a new name for the car
        year = c_year.getText().toString();
        type = c_type.getText().toString();
        make = c_make.getText().toString();
        model = c_model.getText().toString();
        note = c_note.getText().toString();
        boolean hasDup = false;

        //if (!name.equals("") || !year.equals("") || !type.equals("") || !make.equals("") || !model.equals("") || !note.equals("") ||) {

        /*
         * If the current name field is NOT equal to user_select_car >>>> user change car's name
         * Then Check for duplicate name in the car's database
         *      * if the name is already in the car's database >>>> update hasDup to true
         */
        if (!name.equalsIgnoreCase(user_select_car)) {
            //check if user leave name's field blank
            if (name.equals("")) Toast.makeText(getApplicationContext(),"Please give a name for your car!!!", Toast.LENGTH_SHORT).show();
            else {
                CarDBHelper carDbHelper = new CarDBHelper(this);
                SQLiteDatabase db = carDbHelper.getWritableDatabase();
                Cursor cr = carDbHelper.getCar(db);
                while(cr.moveToNext()) {
                    String name_temp = cr.getString(cr.getColumnIndex(DBParam.CarsParam.C_NAME));
                    if (name_temp.equalsIgnoreCase(name)) {
                        String mes = "Name *" + convert(name) +"* already used\nPlease enter a different name";
                        Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_SHORT).show();
                        hasDup = true;
                        break;
                    }
                }
            }
        }
        /* If user does NOT enter duplicate name or leave the name's field as it */
        if (!hasDup) {
            DoInBackGround background = new DoInBackGround(this);
            background.execute("update_car", user_select_car, name, year, type, make, model, note);
            finish();
        }
    }

    /**
     * Function to enable the view services a car
     * which will call ViewAllServiceOfACar.java
     * @param v
     */
    public void btnServices (View v) {
        Intent i = new Intent(ViewOneCarInfo.this, ViewAllServiceOfACar.class);
        i.putExtra(A_CAR_SERVICES_MESSAGE, user_select_car);
        startActivity(i);
    }
    /**
     * To make the name look nicer and consist when display in database
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
