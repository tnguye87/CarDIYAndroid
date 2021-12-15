package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewOneServiceOfACar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_one_service_of_a_car);
        Intent i = getIntent();

        String car_name = i.getStringExtra(ViewAllServiceOfACar.CAR_NAME_MESSAGE);
        String service_name = i.getStringExtra(ViewAllServiceOfACar.SERVICE_NAME_MESSAGE);

        TextView title = (TextView) findViewById(R.id.textView);
        TextView s_cur_date = (TextView) findViewById(R.id.s_currentDate);
        TextView s_cur_miles = (TextView) findViewById(R.id.s_currentMiles);
        TextView s_next_date = (TextView) findViewById(R.id.s_nextDate);
        TextView s_next_miles = (TextView) findViewById(R.id.s_nextMiles);
        TextView s_note = (TextView) findViewById(R.id.s_notes);

        /**
         * Loop through all service's database and find the matching
         * car and service to the car_name and service_name
         * and display all information of that service to the screen
         */

        ServiceDBHelper serviceDBHelper = new ServiceDBHelper(this);
        SQLiteDatabase db = serviceDBHelper.getReadableDatabase();
        Cursor cr = serviceDBHelper.getService(db);

        while (cr.moveToNext()) {
            String name = cr.getString(cr.getColumnIndex(DBParam.ServiceParam.S_NAME));
            String[] temp = name.split("'s ");
            if (temp[0].equalsIgnoreCase(car_name) && temp[1].equalsIgnoreCase(service_name)) {
                String cur_date = cr.getString(cr.getColumnIndex(DBParam.ServiceParam.S_CUR_DATE));
                String cur_miles = cr.getString(cr.getColumnIndex(DBParam.ServiceParam.S_CUR_MILES));
                String next_date = cr.getString(cr.getColumnIndex(DBParam.ServiceParam.S_NEXT_DATE));
                String next_miles = cr.getString(cr.getColumnIndex(DBParam.ServiceParam.S_NEXT_MILES));
                String note = cr.getString(cr.getColumnIndex(DBParam.ServiceParam.S_NOTE));
                //if (note.equalsIgnoreCase("new notes")) note = "Note Not Available";

                title.setText(temp[0].toUpperCase() + "'s " + temp[1].toUpperCase());
                s_cur_date.setText(cur_date);
                s_cur_miles.setText(cur_miles);
                s_next_date.setText(next_date);
                s_next_miles.setText(next_miles);
                s_note.setText(note);
                break;
            }
        }
    }

    public void onBackPressed (View v) {
        Intent i = new Intent(ViewOneServiceOfACar.this, MainActivity.class);
        startActivity(i);
    }
}
