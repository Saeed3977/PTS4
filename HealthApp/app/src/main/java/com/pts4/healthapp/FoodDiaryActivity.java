package com.pts4.healthapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class FoodDiaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);

        populateDiaryListView();
    }

    private void populateDiaryListView()
    {
        DiaryArrayAdapter adapter = new DiaryArrayAdapter(this, generateData());
        ListView lv = (ListView)findViewById(R.id.diaryListView);
        lv.setAdapter(adapter);
    }

    private ArrayList<DiaryEntry> generateData()
    {
        DiaryEntry d = new DiaryEntry("Bacon Cheese Burger", "12:48", "248g", "478kcal");
        DiaryEntry d1 = new DiaryEntry("Appel", "13:28", "66g", "22kcal");
        DiaryEntry d2 = new DiaryEntry("Banaan", "15:11", "70g", "41kcal");
        ArrayList<DiaryEntry> entries = new ArrayList<>();
        entries.add(d);
        entries.add(d1);
        entries.add(d2);
        for(int i = 0; i < 10; i++)
        {
            entries.add(d2);
        }

        return entries;
    }
}
