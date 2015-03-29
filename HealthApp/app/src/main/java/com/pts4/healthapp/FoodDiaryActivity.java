package com.pts4.healthapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FoodDiaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);

        populateDiaryListView();

        //Set today's date in title
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        TextView dateView = (TextView)findViewById(R.id.dateTextView);
        dateView.setText(sdf.format(new Date()));

        //Navigation to Add Entry
        Button addEntryButton = (Button)findViewById(R.id.addEntryButton);
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newEntryIntent = new Intent(v.getContext(), DiaryAddEntryActivity.class);
                v.getContext().startActivity(newEntryIntent);
            }
        });
    }

    private void populateDiaryListView()
    {
        DiaryArrayAdapter adapter = new DiaryArrayAdapter(this, generateData());
        ListView lv = (ListView)findViewById(R.id.diaryListView);
        lv.setAdapter(adapter);
    }

    private ArrayList<Meal> generateData()
    {
        Meal d = new Meal("Bacon Cheese Burger", "12:48", "248g", "478kcal");
        Meal d1 = new Meal("Appel", "13:28", "66g", "22kcal");
        Meal d2 = new Meal("Banaan", "15:11", "70g", "41kcal");
        ArrayList<Meal> entries = new ArrayList<>();
        entries.add(d);
        entries.add(d1);
        entries.add(d2);
        for(int i = 0; i < 10; i++)
        {
            entries.add(d2);
        }

        return entries;
    }

    private ArrayList<Meal> getTodaysFood(){
        ArrayList<Meal> todaysDiaryEntries = new ArrayList<Meal>();
        todaysDiaryEntries = (ArrayList) Meal.listAll(Meal.class);
        return todaysDiaryEntries;
    }
}
