package com.pts4.healthapp;

/**
 * Created by Joep on 24-3-2015.
 */
public class diaryItem {

    private String label;
    private String value;

    public diaryItem(String t1, String t2)
    {
        super();
        this.label = t1;
        this.value = t2;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String value)
    {
        this.label = value;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
