package com.pts4.healthapp;

import com.orm.SugarRecord;

/**
 * Created by Bart on 24/03/15.
 */

public class Food extends SugarRecord<Food> {

    private String name;

    private int proteins;
    private int calories;
    private int fat;

    public Food() {
    }

    public Food(String name) {
        this.name = name;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public String getName() {
        return this.name;
    }

    public int getProteins() {
        return proteins;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

}
