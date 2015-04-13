package com.pts4.healthapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddFoodActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Button confirmButton = (Button)findViewById(R.id.addFoodConfirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description;
                int calPer;
                int proteinPer;
                int fatPer;

                //Check user input
                try
                {
                    description = ((EditText)findViewById(R.id.addFoodNameValue)).getText().toString();
                    if (description.length() == 0)
                    {
                        throw new RuntimeException("Please enter a valid description.");
                    }

                    calPer = checkIfNumeric(((EditText)findViewById(R.id.addFoodCaloriesValue)).getText().toString());
                    if (calPer == -1)
                    {
                        throw new RuntimeException("Please enter a valid amount of calories.");
                    }

                    proteinPer = checkIfNumeric(((EditText)findViewById(R.id.addFoodProteinValue)).getText().toString());
                    if (proteinPer == -1)
                    {
                        throw new RuntimeException("Please enter a valid amount of protein.");
                    }

                    fatPer = checkIfNumeric(((EditText)findViewById(R.id.addFoodFatValue)).getText().toString());
                    if (fatPer == -1)
                    {
                        throw new RuntimeException("Please enter a valid amount of fat.");
                    }

                    try
                    {
                        Food newFood = new Food(description, proteinPer, calPer, fatPer);
                        newFood.save();
                        finish();
                    }
                    catch (Exception ex)
                    {
                        throw new RuntimeException("Something went wrong during the saving of your entry. \nPlease try again or contact a developer.");
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(v.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int checkIfNumeric(String input)
    {
        int result;

        if (input.length() < 1)
        {
            return -1;
        }

        try
        {
            result = Integer.parseInt(input);
        }
        catch (Exception ex)
        {
            return -1;
        }

        return result;
    }
}
