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


public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
    }
}
