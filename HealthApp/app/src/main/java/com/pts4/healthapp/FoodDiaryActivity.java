package com.pts4.healthapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FoodDiaryActivity extends Activity {

    DiaryArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);

        populateDiaryListView();
        refreshDayValues();

        //Set today's date in title
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.setTitle("Food Diary - " + sdf.format(new Date()));

        //Navigation to Add Entry
        Button addEntryButton = (Button)findViewById(R.id.addEntryButton);
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Food.listAll(Food.class).size() == 0)
                {
                    Toast.makeText(v.getContext(), "Please add some foods before adding a new meal entry to your diary.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent newEntryIntent = new Intent(v.getContext(), DiaryAddEntryActivity.class);
                    v.getContext().startActivity(newEntryIntent);
                }
            }
        });

        //Nav to details activity
        final ListView lv = (ListView)findViewById(R.id.diaryListView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(lv.getContext(), MealDetailsActivity.class);
                detailsIntent.putExtra("id", id);
                lv.getContext().startActivity(detailsIntent);
            }
        });
    }

    private void refreshDayValues() {
        // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // String today = sdf.format(new Date());

        int protein     = 0;
        int calories    = 0;
        int fat         = 0;

        for (Ingredient i : Ingredient.listAll(Ingredient.class)) {

           // if (i.getMeal().getEntryDate() == null) continue;

           // if (i.getMeal().getEntryDate().equals(today)) {
                calories    += i.getFood().getCalories()    * i.getAmount() / 100;
                protein     += i.getFood().getProteins()    * i.getAmount() / 100;
                fat         += i.getFood().getFat()         * i.getAmount() / 100;
            //}
        }

        TextView caloriesBox = (TextView)findViewById(R.id.totalCalValue);
        TextView proteinBox = (TextView)findViewById(R.id.totalProteinValue);
        TextView fatBox = (TextView)findViewById(R.id.totalFatValue);

        caloriesBox.setText (calories   + "g");
        proteinBox.setText  (protein    + "g");
        fatBox.setText      (fat        + "g");
    }

    private void populateDiaryListView()
    {
        adapter = new DiaryArrayAdapter(this, getTodaysFood());
        ListView lv = (ListView)findViewById(R.id.diaryListView);
        lv.setAdapter(adapter);
    }

    private ArrayList<Meal> getTodaysFood(){
        ArrayList<Meal> todaysDiaryEntries = new ArrayList<Meal>();
        todaysDiaryEntries = (ArrayList) Meal.listAll(Meal.class);
        return todaysDiaryEntries;
    }
}
