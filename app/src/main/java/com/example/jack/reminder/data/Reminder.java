package com.example.jack.reminder.data;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import com.example.jack.reminder.activity.AlarmNotificationReceiver;
import com.example.jack.reminder.activity.MainActivity;
import com.example.jack.reminder.activity.NotificationHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reminder extends Item {

    MyTime remTime;
    Calendar cal;
    boolean isAlarmSet;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;


    public boolean getIsAlarmSet() {
        return isAlarmSet;
    }

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

    public void setIsAlarmSet(boolean flag){
        isAlarmSet = flag;
    }

    public void setAlarmManager(AlarmManager alarmManager, PendingIntent pendingIntent){
        /* sets the alarm
            three things
            1. Alarm manager
            2. Intent
            3. PendingIntent

            ok no need for these
            we shall only either ser alarm of cancel the alarm
        */

        //  this method should be called at time of new ListItem creation in the activity

        this.alarmManager = alarmManager;
        this.pendingIntent = pendingIntent;

        cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.clear();
        cal.set(remTime.getYear(), remTime.getMonth(), remTime.getDayOfMonth(),
                remTime.getHour24Format(), remTime.getMinute());



    }

    public void setAlarm(){
        if(getIsAlarmSet())
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    public void cancelAlarm(){
        // not done

        if(!getIsAlarmSet())
            alarmManager.cancel(pendingIntent);


    }

    public String getRowViewAlarmTimeText(){
        return (remTime.getHour24Format() + ":" + remTime.getMinute()) ;
    }
}