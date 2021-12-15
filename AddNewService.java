package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddNewService extends AppCompatActivity {

    private String serviceName, car;
    private TextView curDateTV, nextDateTV;
    private DatePickerDialog.OnDateSetListener curDateListener, nextDateListener;

    private String name, curDate, curMiles, nextDate, nextMiles, note;
    private EditText s_cur_miles, s_next_miles, s_note;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_service);

        s_cur_miles = (EditText) findViewById(R.id.s_currentMiles);
        s_next_miles = (EditText) findViewById(R.id.s_nextMiles);
        s_note = (EditText) findViewById(R.id.s_notes);

        /* Retrieve the service's name from ViewMyGarage.java and update the service's name TextView
         * The Primary key for service's database could the the combination of car's name and service's name
         * since the car's name is unique and service's name is also unique
         */
        Intent i = getIntent();
        TextView service_name = (TextView) findViewById(R.id.s_name);
        serviceName = i.getStringExtra(ViewMyGarage.SERVICE_NAME);
        car = i.getStringExtra(ViewMyGarage.VIEW_ONE_CAR_MESSAGE);
        name = car + "'s " + serviceName;
        service_name.setText(name);

        /*
         * Setup the DatePickerDialog for user to select date for Current Date and Next ServiceDate
         */
        /** setup for curDate */
        curDateTV = (TextView) findViewById(R.id.s_currentDate);
        nextDateTV = (TextView) findViewById(R.id.s_nextDate);
        curDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddNewService.this,
                        //android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        curDateListener,
                        year,month,day);
                dialog.getWindow();
                dialog.show();
            }
        });
        curDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" +  day + "/" + year;
                curDateTV.setText(date);
            }
        };

        /** Set up for nextDate */
        nextDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddNewService.this,
                        //android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        nextDateListener,
                        year,month,day);
                dialog.getWindow();
                dialog.show();
            }
        });
        nextDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" +  day + "/" + year;
                nextDateTV.setText(date);
            }
        };
    }

    public void addNewService (View v) {
        name = car + "'s " + serviceName;
        curDate = curDateTV.getText().toString();
        curMiles = s_cur_miles.getText().toString();
        nextDate = nextDateTV.getText().toString();
        nextMiles = s_next_miles.getText().toString();
        note = s_note.getText().toString();

        if (curDate.equals("") || curMiles.equals("") || nextDate.equals("") || nextMiles.equals("")) {
            Toast.makeText(getApplicationContext(), "All Fields (Except Note) must be filled", Toast.LENGTH_SHORT).show();
        }
        else {
            DoInBackGround backGround = new DoInBackGround(this);
            backGround.execute("add_service", name, curDate, curMiles, nextDate, nextMiles, note);
            finish();
        }

    }


    public void onBackPressed (View v) {
        Intent i = new Intent(AddNewService.this, MainActivity.class);
        startActivity(i);
    }

}
