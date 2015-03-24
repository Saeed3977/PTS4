package com.pts4.healthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joep on 24-3-2015.
 */
public class DiaryArrayAdapter extends ArrayAdapter<Meal> {

    private final Context context;
    private final ArrayList<Meal> entries;

    public DiaryArrayAdapter(Context context, ArrayList<Meal> entries)
    {
        super(context, R.layout.diaryrow, entries);

        this.context = context;
        this.entries = entries;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent)
    {
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.diaryrow, parent, false);

        // 3. Get the two text view from the rowView
        TextView nameView = (TextView)rowView.findViewById(R.id.name);
        TextView timeView = (TextView)rowView.findViewById(R.id.time);
        TextView weightView = (TextView)rowView.findViewById(R.id.weight);
        TextView caloriesView = (TextView)rowView.findViewById(R.id.calories);

        // 4. Set the text for textView
        nameView.setText(entries.get(position).getName());
        timeView.setText(entries.get(position).getTime());
        weightView.setText(entries.get(position).getWeight());
        caloriesView.setText(entries.get(position).getCalories());

        // 5. return rowView
        return rowView;
    }
}
