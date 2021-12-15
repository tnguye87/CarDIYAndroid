package edu.gmu.cs477.fall2020.finalproject_tnguye87;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAllTutorial extends AppCompatActivity {

    private ListView mList;
    private ArrayAdapter myAdapter;
    public final static String SINGLE_TUTORIAL_MESSAGE = "edu.gmu.cs477.fall2020.final_projecttnguye87.single_tutorial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_tutorial);

        mList = (ListView) findViewById(R.id.allTutorial);
        myAdapter = new ArrayAdapter<String>(this, R.layout.line);
        mList.setAdapter(myAdapter);

        addTutorial(myAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 /* Get the tutorial that the user select and send that string to SingleTutorialView.java */
                String tutorial = (String) myAdapter.getItem(position);
                //Toast.makeText(getApplicationContext(), "Click: " + tutorial, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ViewAllTutorial.this, SingleTutorialView.class);
                i.putExtra(SINGLE_TUTORIAL_MESSAGE, tutorial);
                startActivity(i);
            }
        });

    }

    /**
     * Private function to add tutorial into the allTutorial list view
     * @param myAdapter
     */
    private void addTutorial (ArrayAdapter<String> myAdapter) {

        /* Set up the list of all available tutorial for the app */
        myAdapter.add("Oil Change");
        myAdapter.add("Engine Air Filter Replacement");
        myAdapter.add("Cabin Air Filter");
        myAdapter.add("Tire Rotation");
        myAdapter.add("Windshield Fluid Top Up");
    }
}


