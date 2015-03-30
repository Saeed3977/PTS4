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
    private Date   date;

    private ArrayList<Food> ingredients = new ArrayList<>();

    public Meal(){}

    public Meal(String name, String time, String weight, String calories)
    {
        super();
        this.name = name;
        this.time = time;
        this.weight = weight;
        this.calories = calories;
        this.date = new Date();
    }

    public void addFood(Food food)
    {
        ingredients.add(food);
    }

    public List<Food> getFood()
    {
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

    public String getWeight()
    {
        return weight;
    }

    public String getCalories()
    {
        return calories;
    }

    public Date getDate(){
        return this.date;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setIngredients(ArrayList<Food> ingredients)
    {
        this.ingredients = ingredients;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
