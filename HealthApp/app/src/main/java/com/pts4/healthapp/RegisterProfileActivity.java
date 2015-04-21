package com.pts4.healthapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class RegisterProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);

        ((Button)findViewById(R.id.saveProfileButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((EditText)findViewById(R.id.profileNameValue)).getText().length() > 0
                        && checkIfNumeric( ((EditText)findViewById(R.id.profileAgeValue)).getText().toString() )
                        && checkIfNumeric( ((EditText)findViewById(R.id.profileHeightValue)).getText().toString() )
                        && checkIfNumeric( ((EditText)findViewById(R.id.profileWeightValue)).getText().toString() )
                        && checkIfSexWasSelected())
                {
                    try {
                        Profile newProfile;
                        String name = ((EditText) findViewById(R.id.profileNameValue)).getText().toString();
                        int age = Integer.parseInt(((EditText) findViewById(R.id.profileAgeValue)).getText().toString());
                        int weight = Integer.parseInt(((EditText) findViewById(R.id.profileWeightValue)).getText().toString());
                        int height = Integer.parseInt(((EditText) findViewById(R.id.profileHeightValue)).getText().toString());
                        Sex sex = null;

                        if (((RadioButton) findViewById(R.id.profileIsMale)).isChecked()) {
                            sex = Sex.MALE;
                        } else if (((RadioButton) findViewById(R.id.profileIsFemale)).isChecked()) {
                            sex = Sex.FEMALE;
                        }

                        newProfile = new Profile(name, weight, height, age, sex, ActivityLevel.MODERATELYACTIVE);

                        // If a passcode is entered
                        String passcode = ((EditText)findViewById(R.id.profilePinValue)).getText().toString();
                        String passcodeConfirm = ((EditText)findViewById(R.id.profileConfirmPinValue)).getText().toString();


                        if (passcode != null || passcode.equals("") || passcodeConfirm != null || passcodeConfirm.equals("")) {

                            // Throw an exception if the passcodes do not match
                            if (passcode.equals(passcodeConfirm) == false) throw new Exception("Passcodes do not match");

                            // Otherwise set the passcode
                            newProfile.createPasscode(passcode);
                        }


                        newProfile.save();

                        finish();
                    }
                    catch(Exception ex)
                    {
                        Toast.makeText(v.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(v.getContext(), "Your input was invalid. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean checkIfNumeric(String input)
    {
        if (input.length() < 1)
        {
            return false;
        }

        try
        {
            int i = Integer.parseInt(input);
        }
        catch (Exception ex)
        {
            return false;
        }

        return true;
    }

    private boolean checkIfSexWasSelected()
    {
        return (((RadioButton)findViewById(R.id.profileIsMale)).isChecked()
                || ((RadioButton)findViewById(R.id.profileIsFemale)).isChecked());
    }
}
