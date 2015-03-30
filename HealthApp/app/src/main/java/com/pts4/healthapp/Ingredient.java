package com.pts4.healthapp;

import com.orm.SugarRecord;

/**
 * Created by Joep on 30-3-2015.
 */
public class Ingredient extends SugarRecord<Meal>
{
    private long mealID;
    private long foodID;

    public Ingredient(){};

    public Ingredient(long mealID, long foodID)
    {
        this.mealID = mealID;
        this.foodID = foodID;
    }

    public long getMealID()
    {
        return mealID;
    }

    public long getFoodID()
    {
        return foodID;
    }
}
