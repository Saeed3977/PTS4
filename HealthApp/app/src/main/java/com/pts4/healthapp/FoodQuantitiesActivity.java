package com.pts4.healthapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class FoodQuantitiesActivity extends Activity {

    private ListView lv;
    private ArrayList<String> mealIngredients;
    private long mealId;
    private String mealName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_quantities);
        getValuesFromIntent();
        final Button submitQuantitiesButton = (Button)findViewById(R.id.quantityAddEntry);
        submitQuantitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredient();
            }
        });

        lv = (ListView)findViewById(R.id.quantityFoodList);

        if (!populateFoodList())
        {
            finish();
        }
    }

    private boolean populateFoodList()
    {
        QuantityPickerArrayAdapter adapter = new QuantityPickerArrayAdapter(this, DiaryAddEntryActivity.mealFoodsNames);
        lv.setAdapter(adapter);
        return true;
    }

    private void getValuesFromIntent()
    {
        mealIngredients = this.getIntent().getStringArrayListExtra("ingredientsMeal");
        mealId = this.getIntent().getLongExtra("mealId", 0);
        mealName = this.getIntent().getStringExtra("mealName");
    }

    private boolean addIngredient()
    {
        for (int i = 0; i < DiaryAddEntryActivity.mealFoods.size(); i++) {
            EditText e = (EditText) (lv.getChildAt(i).findViewById(R.id.quantityValue));
            TextView nameFood = (TextView)(lv.getChildAt(i).findViewById(R.id.quantityFoodName));
            String namefood = nameFood.getText().toString();
            int amount = Integer.parseInt(e.getText().toString());
            Long foodId = DiaryAddEntryActivity.mealFoods.get(namefood);
            Ingredient ingredient = new Ingredient(mealId, foodId, amount);
            ingredient.save();
        }
        return true;
    }


}
