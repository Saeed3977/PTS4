package com.pts4.healthapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DiaryAddEntryActivity extends Activity {

    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_add_entry);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        TextView timeField = (TextView)findViewById(R.id.timeValue);
        timeField.setText(sdf.format(new Date()));

        populateFoodPicker();
    }

    private void populateFoodPicker()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.foodpickerrow, items);
        ListView foodPicker = (ListView)findViewById(R.id.foodPicker);
        foodPicker.setItemsCanFocus(false);
        foodPicker.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        foodPicker.setAdapter(adapter);
        items.add("Banana");
        items.add("Butter");
        items.add("White Bread");
        items.add("Wholegrain Bread");
        items.add("Tomato Sauce");
        items.add("Milk");
        items.add("Egg");
        items.add("Cheese");
        items.add("Pepper");
        items.add("Salt");
    }
}
