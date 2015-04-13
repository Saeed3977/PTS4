package com.pts4.healthapp;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by Joep on 30-3-2015.
 */
public class Ingredient extends SugarRecord<Meal> {
    private long mealID;
    private long foodID;
    private int amount;

    public Ingredient() {
    }

    public Ingredient(long mealID, long foodID, int amount) {
        this.mealID = mealID;
        this.foodID = foodID;
        this.amount = amount;
    }


    public long getMealID() {
        return mealID;
    }

    public long getFoodID() {
        return foodID;
    }

    public long getAmount() {
        return amount;
    }

    /***
     * Looks up the actual Meal-object in the database according to the mealID
     *
     * @return The meal of the ingredient. null if the meal is not found
     */
    public Meal getMeal() {

        for (Meal m : Meal.listAll(Meal.class)) {
            if (m.getId() == this.getMealID()) {
                return m;
            }
        }

        return null;

    }

    /***
     * Looks up the actual Food-object in the database according to the foodID
     *
     * @return The food of the ingredient. null if the food is not found
     */
    public Food getFood() {
        for (Food f : Food.listAll(Food.class)) {
            if (f.getId() == this.getFoodID()) {
                return f;
            }
        }

        return null;
    }
}
