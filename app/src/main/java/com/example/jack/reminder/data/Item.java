package com.example.jack.reminder.data;

import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Item implements Serializable{
    String title;
    Date timeOfCreation;


    public Item(String title){
        this.title = title;
        timeOfCreation = new Date();
    }

    public String getRowViewDateString(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
        return (sdf1.format(timeOfCreation) + ", " + sdf2.format(timeOfCreation));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

//    public Date getDate() {
//        return timeOfCreation;
//    }
}