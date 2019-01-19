package com.example.jack.reminder.data;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Reminder extends Item {
    MyTime remTime;

    public Reminder(String title, MyTime remTime) {
        super(title);
        this.remTime = remTime;
    }

    public Reminder(Reminder rm){
        super(rm.title);
        this.remTime = rm.remTime;

    }

    public MyTime getRemTime() {
        return remTime;
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }
}