package com.pts4.healthapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class FoodQuantitiesActivity extends Activity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_quantities);

        lv = (ListView)findViewById(R.id.quantityFoodList);

        if (!populateFoodList())
        {
            finish();
        }
    }

    private boolean populateFoodList()
    {
        ArrayList<String> testItems = new ArrayList<>();
        testItems.add("Brood");
        testItems.add("Kaas");
        testItems.add("Boter");

        QuantityPickerArrayAdapter adapter = new QuantityPickerArrayAdapter(this, testItems);
        lv.setAdapter(adapter);

        return true;
    }
}
