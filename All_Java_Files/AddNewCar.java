package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewCar extends AppCompatActivity {
    private EditText c_name, c_year, c_type, c_make, c_model, c_note;
    private String name, year, type, make, model, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_car);

        c_name = (EditText) findViewById(R.id.c_name);
        c_year = (EditText) findViewById(R.id.c_year);
        c_type = (EditText) findViewById(R.id.c_type);
        c_make = (EditText) findViewById(R.id.c_make);
        c_model = (EditText) findViewById(R.id.c_model);
        c_note = (EditText) findViewById(R.id.c_notes);
    }

    public void onBackPressed(View v) {
        Intent i = new Intent(AddNewCar.this, MainActivity.class);
        startActivity(i);
    }

    public void addNewCar (View v) {
        name = c_name.getText().toString();
        year = c_year.getText().toString();
        type = c_type.getText().toString();
        make = c_make.getText().toString();
        model = c_model.getText().toString();
        note = c_note.getText().toString();
        boolean hasDup = false;

        if (name.equals("")) {
            Toast.makeText(getApplicationContext(), "Must give a name for your new Car", Toast.LENGTH_SHORT).show();
        }
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

        if (!hasDup) {
            DoInBackGround backGround = new DoInBackGround(this);
            backGround.execute("add_car", name, year, type, make, model, note);
            finish();
        }
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
