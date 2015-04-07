package com.pts4.healthapp;

import com.orm.SugarRecord;

/**
 * Created by Joep on 7-4-2015.
 */
public class Profile extends SugarRecord<Profile> {

    private String name;
    private int weight;
    private int height;
    private int age;
    private Sex sex;

    public Profile(){}

    public Profile(String name, int weight, int height, int age, Sex sex)
    {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
    }

    public String getName()
    {
        return name;
    }

    public int getWeight()
    {
        return weight;
    }

    public int getHeight()
    {
        return height;
    }

    public int getAge()
    {
        return age;
    }

    public Sex getSex()
    {
        return sex;
    }
}
