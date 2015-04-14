package com.pts4.healthapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class BodyStatsActivity extends Activity {
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_stats);

        TextView bmiValue = (TextView)findViewById(R.id.bodyStatsBMIValue);
        TextView bmiBodyType = (TextView)findViewById(R.id.bodyStatsBMIBodyType);
        c=bmiBodyType.getContext();
        double bmiResult = calculateBMI();

        if (bmiResult == -1)
        {
            Toast.makeText(bmiValue.getContext(), "Body Statistics calculation failed. Please contact a developer.", Toast.LENGTH_LONG).show();
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

            Toast.makeText(c, "Weight: " + myWeight + " Height: " + myHeight, Toast.LENGTH_LONG).show();

            result = (myWeight/(myHeightInMeters*myHeightInMeters));
        }
        catch (Exception ex)
        {
            return -1;
        }

        return result;
    }
}
