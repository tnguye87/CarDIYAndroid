package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends ArrayAdapter {

    List list = new ArrayList();

    // Constructor for this class
    public CarAdapter(Context context, int resource) {
        super(context, resource);
    }

    /**
     * will be use in onProgressUpdate in the DoInBackGround.java
     * @param car
     */
    public void add(Cars car) {
        list.add(car);
        super.add(car);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CarHolder carHolder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.line, parent, false);
            carHolder = new CarHolder();
            carHolder.car_name = (TextView) row.findViewById(R.id.todo);
            row.setTag(carHolder);
        }

        else carHolder = (CarHolder) row.getTag();

        Cars car = (Cars) getItem(position);
        carHolder.car_name.setText(car.getName().toString());

        return row;
    }

    static class CarHolder { TextView car_name;}
}
