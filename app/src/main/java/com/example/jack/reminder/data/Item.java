package com.example.jack.reminder.data;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public abstract class Item implements Serializable{
    String title;
    Date timeOfCreation;


    public Item(String title){
        this.title = title;
        timeOfCreation = new Date();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return timeOfCreation;
    }
}