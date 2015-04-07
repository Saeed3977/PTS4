package com.pts4.healthapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;


public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setOnClicks();

        try
        {
            //throws RuntimeError if there is no profile yet
            List<Profile> p = Profile.listAll(Profile.class);
            String n = p.get(0).getName();
        }
        catch(Exception ex)
        {
            Intent createProfileIntent = new Intent(findViewById(R.id.diaryButton).getContext(), RegisterProfileActivity.class);
            findViewById(R.id.diaryButton).getContext().startActivity(createProfileIntent);
        }


    }

    private void setOnClicks()
    {
        View foodDiaryButton = findViewById(R.id.diaryButton);
        foodDiaryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent diaryIntent = new Intent(v.getContext(), FoodDiaryActivity.class);
                v.getContext().startActivity(diaryIntent);
            }
        });

        View foodListButton = findViewById(R.id.foodListButton);
        foodListButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent foodListIntent = new Intent(v.getContext(), FoodListActivity.class);
                v.getContext().startActivity(foodListIntent);
            }
        });
    }
}
