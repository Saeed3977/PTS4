package com.pts4.healthapp;

import com.orm.SugarRecord;

/**
 * Created by Bart on 24/03/15.
 */

public class Food extends SugarRecord<Food>{

    private String name;

    public Food(){}

    public Food(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
