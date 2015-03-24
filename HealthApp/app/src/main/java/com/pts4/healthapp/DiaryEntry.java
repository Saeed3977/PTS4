package com.pts4.healthapp;

/**
 * Created by Joep on 24-3-2015.
 */
public class DiaryEntry
{
    private String name;
    private String time;
    private String weight;
    private String calories;

    public DiaryEntry(String name, String time, String weight, String calories)
    {
        super();
        this.name = name;
        this.time = time;
        this.weight = weight;
        this.calories = calories;
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
}
