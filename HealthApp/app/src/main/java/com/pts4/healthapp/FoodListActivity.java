package com.pts4.healthapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class FoodListActivity extends Activity {

    private List<Food> foodlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
    }

    /**
     * Loads the food list from the local database
     */
    private boolean loadFoodlist() {
        List<Food> foods = Food.listAll(Food.class);

        if (foods.size() > 0) {
            foodlist = foods;
            return true;
        }

        return false;
    }

    /**
     * Adds a food to the
     *
     * @param name
     */
    private void addFood(String name) {
        Food food = new Food(name);
        food.save();
    }
}
