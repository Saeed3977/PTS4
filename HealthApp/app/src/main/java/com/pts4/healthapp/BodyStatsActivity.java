package com.pts4.healthapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class BodyStatsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_stats);

        displayBMI();
        displayBMR();
    }

    private void displayBMR()
    {
        TextView bmrValue = (TextView)findViewById(R.id.bodyStatsCaloriesValue);

        int bmrResult = calculateBMR();

        if (bmrResult == -1)
        {
            Toast.makeText(bmrValue.getContext(), "BMR calculation failed. Please contact a developer.", Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            bmrValue.setText(String.valueOf(bmrResult));
        }
    }

    private void displayBMI()
    {
        TextView bmiValue = (TextView)findViewById(R.id.bodyStatsBMIValue);
        TextView bmiBodyType = (TextView)findViewById(R.id.bodyStatsBMIBodyType);

        double bmiResult = calculateBMI();

        if (bmiResult == -1)
        {
            Toast.makeText(bmiValue.getContext(), "BMI calculation failed. Please contact a developer.", Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            String bodyType = "";
            int bmiColor = 0;
            if (bmiResult < 18.5)
            {
                bodyType = "Underweight";
                bmiColor = getResources().getColor(R.color.bmiUnderweight);
            }
            else if (bmiResult < 25)
            {
                bodyType = "Healthy";
                bmiColor = getResources().getColor(R.color.bmiNormal);
            }
            else if (bmiResult < 30)
            {
                bodyType = "Overweight";
                bmiColor = getResources().getColor(R.color.bmiOverweight);
            }
            else if (bmiResult >= 30)
            {
                bodyType = "Obese";
                bmiColor = getResources().getColor(R.color.bmiObesity);
            }

            bmiValue.setText(String.valueOf(bmiResult).substring(0,4));
            bmiValue.setTextColor(bmiColor);
            bmiBodyType.setText(bodyType);
            bmiBodyType.setTextColor(bmiColor);
        }
    }
    private double calculateBMI()
    {
        double result = -1;
        int myWeight;
        double myHeight;
        double myHeightInMeters;

        try
        {
            myWeight = Profile.listAll(Profile.class).get(0).getWeight();
            myHeight = Profile.listAll(Profile.class).get(0).getHeight();
            myHeightInMeters = myHeight/100;
            result = (myWeight/(myHeightInMeters*myHeightInMeters));
        }
        catch (Exception ex)
        {
            return -1;
        }

        return result;
    }

    private int calculateBMR()
    {
        int result = -1;
        int myWeight;
        double myHeight;
        int myAge;
        Sex mySex;

        try
        {
            myWeight = Profile.listAll(Profile.class).get(0).getWeight();
            myHeight = Profile.listAll(Profile.class).get(0).getHeight();
            mySex = Profile.listAll(Profile.class).get(0).getSex();
            myAge = Profile.listAll(Profile.class).get(0).getAge();

            switch (mySex)
            {
                case MALE:
                    result = 66 + (int)(13.7 * myWeight) + (int)(5 * myHeight) - (int)(6.8 * myAge);
                    break;
                case FEMALE:
                    result = 655 + (int)(9.6 * myWeight) + (int)(1.8 * myHeight) - (int)(4.7 * myAge);
                    break;
            }
        }
        catch (Exception ex)
        {
            return -1;
        }

        return result;
    }
}
