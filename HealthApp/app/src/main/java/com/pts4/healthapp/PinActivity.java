package com.pts4.healthapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class PinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        try
        {
            //throws RuntimeError if there is no profile yet
            List<Profile> p = Profile.listAll(Profile.class);
            String n = p.get(0).getName();
        }
        catch(Exception ex)
        {
            Intent createProfileIntent = new Intent(findViewById(R.id.pinButton).getContext(), RegisterProfileActivity.class);
            findViewById(R.id.pinButton).getContext().startActivity(createProfileIntent);
        }

        Button confirmPinButton = (Button)findViewById(R.id.pinButton);
        confirmPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinIsCorrect())
                {
                    Intent homeIntent = new Intent(v.getContext(), Home.class);
                    v.getContext().startActivity(homeIntent);
                }
            }
        });
    }

    private boolean pinIsCorrect()
    {
        return true;
    }
}
