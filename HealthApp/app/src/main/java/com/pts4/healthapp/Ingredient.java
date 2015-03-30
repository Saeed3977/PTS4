package com.pts4.healthapp;

import com.orm.SugarRecord;

/**
 * Created by Joep on 30-3-2015.
 */
public class Ingredient extends SugarRecord<Meal>
{
    private int mealID;
    private int foodID;

    public Ingredient(){};

    public Ingredient(int mealID, int foodID)
    {
        this.mealID = mealID;
        this.foodID = foodID;
    }

    public int getMealID()
    {
        return mealID;
    }

    public int getFoodID()
    {
        return foodID;
    }
}
