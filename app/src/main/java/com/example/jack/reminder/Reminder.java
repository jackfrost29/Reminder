package com.example.jack.reminder;


import android.os.Parcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reminder extends Item {
    Date date;

    public Reminder(String title, Date date) {
        super(title);
        this.date = date;
    }

    public Reminder(Reminder rm){
        super(rm.title);
        this.date = rm.date;

    }

}