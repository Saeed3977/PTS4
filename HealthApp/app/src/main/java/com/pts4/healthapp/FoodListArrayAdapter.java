package com.pts4.healthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joep on 13-4-2015.
 */
public class FoodListArrayAdapter extends ArrayAdapter<Food> {
    private final Context context;
    private final ArrayList<Food> entries;

    public FoodListArrayAdapter(Context c, ArrayList<Food> input)
    {
        super(c, R.layout.foodlistrow, input);

        this.context = c;
        this.entries = input;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.foodlistrow, parent, false);

        TextView nameView = (TextView)rowView.findViewById(R.id.foodlistItemName);
        TextView caloriesView = (TextView)rowView.findViewById(R.id.foodlistItemCalories);
        TextView proteinView = (TextView)rowView.findViewById(R.id.foodlistItemProtein);
        TextView fatView = (TextView)rowView.findViewById(R.id.foodlistItemFat);

        nameView.setText(entries.get(position).getName());
        caloriesView.setText("Calories: " + String.valueOf(entries.get(position).getCalories()) + "g");
        proteinView.setText("Protein: " + String.valueOf(entries.get(position).getProteins()) + "g");
        fatView.setText("Fat: " + String.valueOf(entries.get(position).getFat()) + "g");

        return rowView;
    }
}
