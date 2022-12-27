package com.example.keymystery.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity

public class User {
    String userName;
@NonNull
@PrimaryKey
    String email;
    boolean gender;
    String country;
    String birthDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public User(String userName, String email, boolean gender, String country, String birthDate) {
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.birthDate = birthDate;
    }
}
