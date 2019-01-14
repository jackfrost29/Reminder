package com.example.jack.reminder;


import android.os.Parcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reminder extends Item {
    Date date;

    public Reminder(String title, Date date, ArrayList<String> list) {
        super(title);
        this.date = date;
    }
}