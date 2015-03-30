package com.pts4.healthapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
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
                if(saveEntry())
                {
                    finish();
                }
                else
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(confirmEntryButton.getContext()).create();
                    alertDialog.setMessage("Meal could not be added to diary.");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {}});
                    alertDialog.show();
                }
            }
        });
    }

    private void populateFoodPicker()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.foodpickerrow, items);
        ListView foodPicker = (ListView)findViewById(R.id.foodPicker);
        foodPicker.setItemsCanFocus(false);
        foodPicker.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        foodPicker.setAdapter(adapter);
        items.add("Banana");
        items.add("Butter");
        items.add("White Bread");
        items.add("Wholegrain Bread");
        items.add("Tomato Sauce");
        items.add("Milk");
        items.add("Egg");
        items.add("Cheese");
        items.add("Pepper");
        items.add("Salt");
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
        newEntry.setDate(new Date());
        newEntry.setIngredients(foods);
        String entryTime = (new Date()).toGMTString();
        newEntry.setTime(entryTime);

        return isSuccess;
    }
}
