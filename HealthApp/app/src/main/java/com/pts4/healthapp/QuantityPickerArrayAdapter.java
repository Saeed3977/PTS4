package com.pts4.healthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joep on 7-4-2015.
 */
public class QuantityPickerArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> entries;

    public QuantityPickerArrayAdapter(Context context, ArrayList<String> entries)
    {
        super(context, R.layout.quantitypickerrow, entries);

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
        View rowView = inflater.inflate(R.layout.quantitypickerrow, parent, false);

        // 3. Get the two text view from the rowView
        TextView nameView = (TextView)rowView.findViewById(R.id.quantityFoodName);

        // 4. Set the text for textView
        nameView.setText(entries.get(position));

        // 5. return rowView
        return rowView;
    }
}
