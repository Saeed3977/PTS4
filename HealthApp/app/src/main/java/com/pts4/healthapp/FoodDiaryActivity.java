package com.pts4.healthapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        this.setTitle("Food Diary - " + sdf.format(new Date()));

        //Navigation to Add Entry
        Button addEntryButton = (Button)findViewById(R.id.addEntryButton);
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newEntryIntent = new Intent(v.getContext(), DiaryAddEntryActivity.class);
                v.getContext().startActivity(newEntryIntent);
            }
        });

        //Nav to details activity
        final ListView lv = (ListView)findViewById(R.id.diaryListView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(lv.getContext(), MealDetailsActivity.class);
                lv.getContext().startActivity(detailsIntent);
            }
        });
    }

    private void populateDiaryListView()
    {
        DiaryArrayAdapter adapter = new DiaryArrayAdapter(this, getTodaysFood());
        ListView lv = (ListView)findViewById(R.id.diaryListView);
        lv.setAdapter(adapter);
    }

    private ArrayList<Meal> getTodaysFood(){
        ArrayList<Meal> todaysDiaryEntries = new ArrayList<Meal>();
        todaysDiaryEntries = (ArrayList) Meal.listAll(Meal.class);
        return todaysDiaryEntries;
    }
}
