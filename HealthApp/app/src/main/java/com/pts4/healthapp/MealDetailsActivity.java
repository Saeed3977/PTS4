package com.pts4.healthapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MealDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        Bundle bundle = getIntent().getExtras();

        Meal thisMeal = null;

        for(Meal m: Meal.listAll(Meal.class))
        {
            if(m.getId().equals(bundle.getLong("id") + 1l))
            {
                thisMeal = m;
            }
        }

        if(thisMeal == null)
        {
            finish();
        }
        else
        {
            ArrayList<String> ingredientNames = new ArrayList<String>();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredientNames);
            TextView mealTitle = (TextView)findViewById(R.id.mealTitle);
            TextView mealDate = (TextView)findViewById(R.id.mealDate);
            ListView ingredientsList = (ListView)findViewById(R.id.ingredientsList);
            ingredientsList.setAdapter(adapter);

            for(Ingredient i: Ingredient.listAll(Ingredient.class))
            {
                if (i.getMealID() == thisMeal.getId())
                {
                    for(Food f: Food.listAll(Food.class))
                    {
                        if (f.getId() == i.getFoodID())
                        {
                            ingredientNames.add(f.getName());
                        }
                    }
                }
            }

            mealTitle.setText(thisMeal.getName());
            mealDate.setText(thisMeal.getTime());
        }
    }
}
