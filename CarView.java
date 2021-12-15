package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CarView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_view);
    }

    /**
     * call the ViewMyGarage.java to display the list of all cars in my car's database
     * @param view
     */
    public void viewMyGarage (View view) {
        Intent i = new Intent(CarView.this, ViewMyGarage.class);
        startActivity(i);
    }

    /**
     * call the AddNewCar.java to add a new car into my car's database
     * @param view
     */
    public void addNewCar (View view) {
        Intent i = new Intent(CarView.this, AddNewCar.class);
        startActivity(i);
    }

    public void onBackPressed (View v) {
        Intent i = new Intent(CarView.this, MainActivity.class);
        startActivity(i);
    }
}
