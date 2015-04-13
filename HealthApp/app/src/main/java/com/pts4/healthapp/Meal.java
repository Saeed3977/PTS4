package com.pts4.healthapp;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Joep on 24-3-2015.
 */
public class Meal extends SugarRecord<Meal>
{
    private String name;
    private String time;
    private String weight;
    private String calories;
    private String entryDate;

    public Meal(){}

    public Meal(String name, String time, String weight, String calories)
    {
        super();
        this.name = name;
        this.time = time;
        this.weight = weight;
        this.calories = calories;
        this.entryDate = new Date().toString();
    }

    public void addIngredient(Food food, int amount)
    {
        Ingredient ing = new Ingredient(this, food, amount);
        ing.save();
        //ingredients.add(ing);
    }

    public List<Ingredient> getIngredients()
    {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        for(Ingredient i: Ingredient.listAll(Ingredient.class))
        {
            if (i.getMealID() == this.getId())
            {
                ingredients.add(i);
            }
        }

        return Collections.unmodifiableList(ingredients);
    }

    public String getName()
    {
        return name;
    }

    public String getTime()
    {
        return time;
    }

    /***
     * Calculates the total weight of all ingredients
     *
     * @return The total weight of this meal.
     */
    public int getWeight()
    {
        int result = 0;

        for (Ingredient i : this.getIngredients())
        {
            result += i.getAmount();
        }

        return result;
    }

    /***
     * Calculates the total of all calories in this meal
     *
     * @return The total of all calories in this meal.
     */
    public int getCalories()
    {
        int result = 0;

        for (Ingredient i : this.getIngredients())
        {
            result += i.getAmount() * i.getFood().getCalories();
        }

        return result;
    }

    /***
     * Calculates the total of all proteins in this meal
     *
     * @return The total of all proteins in this meal.
     */
    public int getProteins()
    {
        int result = 0;

        for (Ingredient i : this.getIngredients())
        {
            result += i.getAmount() * i.getFood().getProteins();
        }

        return result;
    }

    /***
     * Calculates the total of all fat in this meal
     *
     * @return The total of all fat in this meal.
     */
    public int getFat()
    {
        int result = 0;

        for (Ingredient i : this.getIngredients())
        {
            result += i.getAmount() * i.getFood().getFat();
        }

        return result;
    }

    public String getEntryDate(){
        return this.entryDate;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public void setCalories(String calories)
    {
        this.calories = calories;
    }

//    public void setIngredients(ArrayList<Food> ingredients)
//    {
//        this.ingredients = ingredients;
//    }

    public void setEntryDate(String entryDate)
    {
        this.entryDate = entryDate;
    }
}
