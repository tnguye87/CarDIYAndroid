package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAllServiceOfACar extends AppCompatActivity {

    private TextView tv;
    private ArrayAdapter myAdapter;
    private ListView mList;
    public final static String CAR_NAME_MESSAGE = "edu.gmu.cs477.fall2020.final_projecttnguye87.CAR_NAME_MESSAGE";
    public final static String SERVICE_NAME_MESSAGE = "edu.gmu.cs477.fall2020.final_projecttnguye87.SERVICE_NAME_MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_service_of_a_car);

        mList = (ListView) findViewById(R.id.allServices_listView);
        myAdapter = new ArrayAdapter<String>(this, R.layout.line);
        mList.setAdapter(myAdapter);

        /*
         * Get the car's name that sends from ViewOneCarInfo.java
         * And uses that name to compare with the 1st part of service's name
         * which is the combination of car's name and service's name
         */
        Intent i = getIntent();
        final String car_name = i.getStringExtra(ViewOneCarInfo.A_CAR_SERVICES_MESSAGE);
        addServices(myAdapter, car_name);

        tv = (TextView) findViewById(R.id.textView);
        tv.setText(car_name.toUpperCase() + "'s SERVICES");

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * Send the car_name and service that select from the service list
                 * and send it to the ViewOneServiceOfACar.java
                 */
                String service_name = (String) myAdapter.getItem(position);
                Intent i = new Intent(ViewAllServiceOfACar.this, ViewOneServiceOfACar.class);
                i.putExtra(CAR_NAME_MESSAGE, car_name);
                i.putExtra(SERVICE_NAME_MESSAGE, service_name);
                startActivity(i);
            }
        });
    }

    /**
     * Private function to add all service of a car into the allService_listView
     * @param myAdapter
     */
    private void addServices (ArrayAdapter<String> myAdapter, String car_name) {
        ServiceDBHelper serviceDBHelper = new ServiceDBHelper(this);
        SQLiteDatabase db = serviceDBHelper.getReadableDatabase();
        Cursor cr = serviceDBHelper.getService(db);

        /**
         * Loop through all services in the service's database
         *  * Get the name of a service
         *  * Split that name by "'s " to separate the car's name and service's name
         *  * Compare car's name part (at index 0, aka [0]) with car_name
         *  * if match, add the service's part (at index 1, aka [1] to the myAdapter
         */
        while (cr.moveToNext()) {
            String service_name = cr.getString(cr.getColumnIndex(DBParam.ServiceParam.S_NAME));
            String [] temp = service_name.split("'s ");
            if (temp[0].equalsIgnoreCase(car_name)) myAdapter.add(temp[1]);
        }
    }

}
