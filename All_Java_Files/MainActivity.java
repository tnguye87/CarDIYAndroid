package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * call the StartTutorial.java to view all available tutorial in the repair database
     * @param view
     */
    public void startTutorial (View view) {
        Intent i = new Intent(MainActivity.this, ViewAllTutorial.class);
        startActivity(i);
    }

    /**
     * call the ViewAllCar.java to open a new view with the options to:
     *      *** display all of the cars in my car database
     *      *** add a new car
     * @param view
     */
    public void displayCarViews (View view) {
        Intent i = new Intent(MainActivity.this, CarView.class);
        startActivity(i);
    }
}