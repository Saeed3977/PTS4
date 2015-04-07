package com.pts4.healthapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

        final Button confirmEntryButton = (Button)findViewById(R.id.confirmEntryButton);
        confirmEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView foodPicker = (ListView)findViewById(R.id.foodPicker);

                if (((EditText)findViewById(R.id.nameValue)).getText().toString().length() > 0
                        && foodPicker.getCheckedItemCount() > 0) {

                    if (saveEntry()) {
                        finish();
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(confirmEntryButton.getContext()).create();
                        alertDialog.setMessage("Meal could not be added to diary.");
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                    }
                }
            }
        });
    }

    private void populateFoodPicker()
    {
        if (!loadFoodlist())
        {
            items.add("No food in list!");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.foodpickerrow, items);
        ListView foodPicker = (ListView)findViewById(R.id.foodPicker);
        foodPicker.setItemsCanFocus(false);
        foodPicker.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        foodPicker.setAdapter(adapter);
    }

    private boolean saveEntry()
    {
        boolean isSuccess = true;

        ArrayList<Food> foods = new ArrayList<Food>();
        ListView foodPicker = (ListView)findViewById(R.id.foodPicker);
        SparseBooleanArray checkedFoods = foodPicker.getCheckedItemPositions();

        for(int i = 0; i < checkedFoods.size(); i++)
        {
            if(checkedFoods.valueAt(i) == true)
            {
                Food checkedFood = new Food(foodPicker.getItemAtPosition(checkedFoods.keyAt(i)).toString());
                foods.add(checkedFood);
            }
        }

        Meal newEntry = new Meal();
        newEntry.setName(((EditText)findViewById(R.id.nameValue)).getText().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        newEntry.setTime(sdf.format(new Date()));
        newEntry.save();

        List<Meal> allMeals = Meal.listAll(Meal.class);
        Meal lastMeal = allMeals.get(allMeals.size()-1);
        long mealID = lastMeal.getId();

        List<Food> allFoods = Food.listAll(Food.class);
        List<Food> ingredients = new ArrayList<Food>();
        for(Food f: allFoods)
        {
            for (Food f2: foods)
            {
                if(f.getName().equals(f2.getName()))
                {
                    ingredients.add(f);
                }
            }
        }

        for(Food x: ingredients)
        {
            Ingredient newIngredient = new Ingredient(mealID, x.getId());
            newIngredient.save();
        }

        return isSuccess;
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
