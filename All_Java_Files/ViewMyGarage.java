package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewMyGarage extends AppCompatActivity {

    Context ctx;
    ListView mlist;
    public final static String VIEW_ONE_CAR_MESSAGE = "edu.gmu.cs477.fall2020.finalprojecttnguye87.ONE_CAR";
    public final static String SERVICE_NAME = "edu.gmu.cs477.fall2020.finalprojecttnguye87.SERVICE_NAME";
    String serviceName, car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_my_garage);

        DoInBackGround backGround = new DoInBackGround(this);
        backGround.execute("get_info");

        mlist = (ListView) findViewById(R.id.allCars_listView);

        CarDBHelper carDbHelper = new CarDBHelper(this);
        SQLiteDatabase db = carDbHelper.getReadableDatabase();
        Cursor cr = carDbHelper.getCar(db);

        /* Count how many cars that are in your car's database */
        int count = 0;
        while (cr.moveToNext()) {count++;}
        if (count == 0) Toast.makeText(getApplicationContext(), "There is no car in your garage!!!", Toast.LENGTH_SHORT).show();
        else if (count == 1) Toast.makeText(getApplicationContext(), "There is 1 cars in your garage!!!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(), "There are "+ count + " cars in your garage", Toast.LENGTH_SHORT).show();

        /* Receive service's name (aka: signal) that this class is called from SingleTutorialView */
        Intent i = getIntent();
        serviceName = i.getStringExtra(SingleTutorialView.SINGLE_TUTORIAL_VIEW);
        //System.out.println("activateAddService receive: " + serviceName);

        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*
                 * Check if serviceName is sent from SingleTutorialView.java
                 * Then, send that name to AddNewService.java
                 * Also send that service name to AddNewService.java
                 */
                if (serviceName != null) {
                    car = ((TextView) view).getText().toString();
                    Intent intent = new Intent(ViewMyGarage.this, AddNewService.class);
                    intent.putExtra(SERVICE_NAME, serviceName);
                    intent.putExtra(VIEW_ONE_CAR_MESSAGE, car);
                    startActivity(intent);
                }
                else {
                    /* Get the name of the car
                     * Send that name to ViewOneCarInfo.java to view and update corresponding data of that car
                     */
                    car = ((TextView) view).getText().toString();
                    Intent i = new Intent(ViewMyGarage.this, ViewOneCarInfo.class);
                    i.putExtra(VIEW_ONE_CAR_MESSAGE, car);
                    startActivity(i);
                }
            }
        });

    }

    public void onResume() {
        super.onResume();
        DoInBackGround background = new DoInBackGround(this);
        background.execute("get_car");
    }

}
