package com.example.jack.reminder;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public abstract class Item implements Serializable{
    String title;
    Date date;
    public Item(String title){
        this.title = title;
        date = new Date();
    }
}