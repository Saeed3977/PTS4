package com.pts4.healthapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BodyStatsActivity extends Activity {

    private int caloriesConsumed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_stats);

        displayBMI();
        displayProgressBar();
        displayBMR();

        RadioGroup rButtons = (RadioGroup)findViewById(R.id.activityLevelGroup);
        rButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ActivityLevel selectedActivityLevel = null;
                int radioButtonID = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioButtonID);
                int selectedIndex = group.indexOfChild(radioButton);
                switch (selectedIndex)
                {
                    case 0:
                        selectedActivityLevel = ActivityLevel.SEDENTARY;
                        break;
                    case 1:
                        selectedActivityLevel = ActivityLevel.LIGHTLYACTIVE;
                        break;
                    case 2:
                        selectedActivityLevel = ActivityLevel.MODERATELYACTIVE;
                        break;
                    case 3:
                        selectedActivityLevel = ActivityLevel.VERYACTIVE;
                        break;
                    case 4:
                        selectedActivityLevel = ActivityLevel.EXTREMELYACTIVE;
                        break;
                }

                if (selectedActivityLevel != null)
                {
                    Profile myProfile = Profile.listAll(Profile.class).get(0);
                    myProfile.activityLevel = selectedActivityLevel;
                    myProfile.save();
                    displayBMR();
                    displayProgressBar();
                }

            }
        });
    }

    private void displaySavedActivityLevel()
    {
        Profile myProfile = Profile.listAll(Profile.class).get(0);
        ActivityLevel myActivityLevel = myProfile.activityLevel;
        RadioGroup rButtons = (RadioGroup)findViewById(R.id.activityLevelGroup);

        switch (myActivityLevel)
        {
            case SEDENTARY:
                rButtons.check(R.id.activityLevelSedentary);
                break;
            case LIGHTLYACTIVE:
                rButtons.check(R.id.activityLevelLightlyActive);
                break;
            case MODERATELYACTIVE:
                rButtons.check(R.id.activityLevelModeratelyActive);
                break;
            case VERYACTIVE:
                rButtons.check(R.id.activityLevelVeryActive);
                break;
            case EXTREMELYACTIVE:
                rButtons.check(R.id.activityLevelExtremelyActive);
                break;
        }
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
            bmrValue.setText(String.valueOf(caloriesConsumed) + "/" + String.valueOf(bmrResult));
        }

        displaySavedActivityLevel();
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
        double subTotal = 0;
        int myWeight;
        double myHeight;
        int myAge;
        Sex mySex;
        ActivityLevel myActivityLevel;

        try
        {
            Profile myProfile = Profile.listAll(Profile.class).get(0);
            myWeight = myProfile.getWeight();
            myHeight = myProfile.getHeight();
            mySex = myProfile.getSex();
            myAge = myProfile.getAge();
            myActivityLevel = myProfile.activityLevel;

            double activityFactor = 0;

            switch (myActivityLevel)
            {
                case SEDENTARY:
                    activityFactor = 1.2;
                    break;
                case LIGHTLYACTIVE:
                    activityFactor = 1.375;
                    break;
                case MODERATELYACTIVE:
                    activityFactor = 1.55;
                    break;
                case VERYACTIVE:
                    activityFactor = 1.725;
                    break;
                case EXTREMELYACTIVE:
                    activityFactor = 1.9;
                    break;
            }

            switch (mySex)
            {
                case MALE:
                    subTotal = 66 + (13.7 * myWeight) + (5 * myHeight) - (6.8 * myAge);
                    break;
                case FEMALE:
                    subTotal = 655 + (9.6 * myWeight) + (1.8 * myHeight) - (4.7 * myAge);
                    break;
            }
            result = (int)(subTotal * activityFactor);
        }
        catch (Exception ex)
        {
            return -1;
        }

        return result;
    }

    private void displayProgressBar()
    {
        ProgressBar calorieProgress = (ProgressBar)findViewById(R.id.customProgress);
        TextView calorieProgressPercentage = (TextView)findViewById(R.id.calorieProgressPercentage);
        caloriesConsumed = 0;
        int caloriesNeeded = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String today = sdf.format(new Date());

        for (Ingredient i : Ingredient.listAll(Ingredient.class)) {
            if (i.getMeal().getEntryDate() == null) continue;
            if (i.getMeal().getEntryDate().equals(today)) {
                caloriesConsumed += i.getFood().getCalories() * i.getAmount() / 100;
            }
        }

        caloriesNeeded = calculateBMR();
        int caloriePercentage = ((caloriesConsumed*100)/(caloriesNeeded));
        calorieProgress.setProgress(caloriePercentage);
        calorieProgressPercentage.setText(caloriePercentage + "%");

        if (caloriePercentage > 100)
        {
            calorieProgress.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        }
        else
        {
            calorieProgress.getProgressDrawable().clearColorFilter();
        }
    }
}
