package com.example.jack.reminder.data;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ListItem extends Item {
    List<String> list;
    MyTime date;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar cal;

    public ListItem(String title, ArrayList<String> list, MyTime date) {
        super(title);
        this.list = list;
        this.date = date;
    }

    public ListItem(){
        super("<To-Do-List>");
        list = new ArrayList<>();
        date = new MyTime();

    }

    public ListItem(ListItem item){
        super(item.title);
        this.list = item.list;

    }

    public void setAlarmManager(AlarmManager alarmManager, PendingIntent pendingIntent){
        // called from ListItemDetailsActivity

        /* sets the alarm
            three things
            1. Alarm manager
            2. Intent
            3. PendingIntent

            ok no need for these
            we shall only either ser alarm of cancel the alarm
        */

        /*
            this method should be called at time of new ListItem creation in the activity
         */

        this.alarmManager = alarmManager;
        this.pendingIntent = pendingIntent;

        cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.clear();

        // alarm will go off at 9:00 on that specific date
        cal.set(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                9, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

    }

    public void setAlarm(){
        // in case of listItem, alarm is always set
        // no way to cancel it
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }


    public MyTime getMyTime() {
        return date;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    public void addToListFromArray(String[] s){
        Collections.addAll(list, s);
    }

    public String getFirstText(){
        return "♦ " + list.get(0);
    }
    public String getSecondText(){
        if(list.size() > 1)
            return list.get(1);

        return "♦ " + "<No Item Selected>";
    }

    public MyTime getRemTime(){
        return date;
    }
}
