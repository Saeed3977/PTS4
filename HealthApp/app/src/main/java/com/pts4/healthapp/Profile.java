package com.pts4.healthapp;

import com.orm.SugarRecord;

import org.mindrot.BCrypt;

/**
 * Created by Joep on 7-4-2015.
 */
public class Profile extends SugarRecord<Profile> {

    private String name;
    private int weight;
    private int height;
    private int age;
    private Sex sex;

    private String passcode;
    private String salt;
    private ActivityLevel activityLevel;

    public Profile() {
    }

    public Profile(String name, int weight, int height, int age, Sex sex, ActivityLevel activityLevel) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.activityLevel = activityLevel;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public ActivityLevel getActivityLevel()
    {
        return activityLevel;
    }

    /**
     * Sets a passcode for a profile without a current passcode
     *
     * @param newPasscode The new passcode
     * @return true if the passcode is now equal to newPasscode.
     */
    public boolean createPasscode(String newPasscode) {
        if (this.passcode != null || newPasscode == null) return false;

        this.salt = BCrypt.gensalt(12);
        this.passcode = BCrypt.hashpw(newPasscode, this.salt);
        return true;

    }

    /**
     * Changes the passcode of the profile
     *
     * @param oldPasscode The current passcode
     * @param newPasscode The new passcode
     * @return true if the passcode is now equal to newPasscode. false if the oldPasscode is
     * incorrect or one of the codes is null
     */
    public boolean changePasscode(String oldPasscode, String newPasscode) {
        if (oldPasscode == null || newPasscode == null) return false;
        if (oldPasscode.equals(newPasscode)) return true;

        if (checkPasscode(oldPasscode)) {
            this.salt = BCrypt.gensalt(12);
            this.passcode = BCrypt.hashpw(newPasscode, this.salt);
            return true;
        }

        return false;
    }

    /**
     * Checks if the entered code equals the stored passcode
     *
     * @param enteredCode The code to check
     * @return true if the enteredCode equals the passcode
     */
    public boolean checkPasscode(String enteredCode) {
        if (enteredCode == null) return false;
        return BCrypt.hashpw(enteredCode, this.salt).equals(this.passcode);
    }
}
