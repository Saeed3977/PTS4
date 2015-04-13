package com.pts4.healthapp;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Joep on 24-3-2015.
 */
public class Meal extends SugarRecord<Meal> {
    private String name;
    private String time;
    private String weight;
    private String calories;
    private String entryDate;

    public Meal() {
    }

    public Meal(String name, String time) {
        super();
        this.name = name;
        this.time = time;
        this.entryDate = new Date().toString();
    }

    public void addIngredient(Food food, int amount) {
        Ingredient ing = new Ingredient(this, food, amount);
        ing.save();
        //ingredients.add(ing);
    }

    /**
     * Looks up all ingredients of the meal
     *
     * @return
     */
    public List<Ingredient> getIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        for (Ingredient i : Ingredient.listAll(Ingredient.class)) {
            if (i.getMealID() == this.getId()) {
                ingredients.add(i);
            }
        }

        return Collections.unmodifiableList(ingredients);
    }


    /**
     * Calculates the total weight of all ingredients
     *
     * @return The total weight of this meal.
     */
    public String getWeight() {
        int result = 0;

        for (Ingredient i : this.getIngredients()) {
            result += i.getAmount();
        }

        return String.valueOf(result);
    }

    /**
     * Calculates the total of all calories in this meal
     *
     * @return The total of all calories in this meal.
     */
    public String getCalories() {
        int result = 0;

        for (Ingredient i : this.getIngredients()) {
            result += i.getAmount() * i.getFood().getCalories();
        }

        return String.valueOf(result);
    }

    /**
     * Calculates the total of all proteins in this meal
     *
     * @return The total of all proteins in this meal.
     */
    public int getProteins() {
        int result = 0;

        for (Ingredient i : this.getIngredients()) {
            result += i.getAmount() * i.getFood().getProteins();
        }

        return result;
    }

    /**
     * Calculates the total of all fat in this meal
     *
     * @return The total of all fat in this meal.
     */
    public int getFat() {
        int result = 0;

        for (Ingredient i : this.getIngredients()) {
            result += i.getAmount() * i.getFood().getFat();
        }

        return result;
    }

    public String getEntryDate() {
        return this.entryDate;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
}
