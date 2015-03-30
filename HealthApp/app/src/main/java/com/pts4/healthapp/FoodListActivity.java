package com.pts4.healthapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(addEntryButton.getContext());
                builder.setTitle("Food description:");

// Set up the input
                final EditText input = new EditText(addEntryButton.getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("Add to Foodlist", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String value = input.getText().toString();
                        addFood(value);
                        populateFoodListView();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
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

    /**
     * Adds a food to the database
     *
     * @param name
     */
    private void addFood(String name) {
        Food food = new Food(name);
        food.save();

    }
}
