package com.pts4.healthapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import java.util.List;


public class FoodListActivity extends Activity {

    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        populateFoodListView();

        final Button addEntryButton = (Button)findViewById(R.id.addFoodButton);
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFoodIntent = new Intent(v.getContext(), AddFoodActivity.class);
                v.getContext().startActivity(addFoodIntent);
            }
        });
    }

    private void populateFoodListView()
    {
        if (!loadFoodlist())
        {
            items.add("No food in list!");
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListView foodList = (ListView)findViewById(R.id.foodListView);
        foodList.setAdapter(adapter);
    }

    /**
     * Loads the food list from the local database
     */
    private boolean loadFoodlist() {
        List<Food> foods = Food.listAll(Food.class);

        if (foods.size() > 0) {

            items.clear();

            for (Food f : foods) {
                items.add(f.getName());
            }

            return true;
        }

        return false;
    }
}
