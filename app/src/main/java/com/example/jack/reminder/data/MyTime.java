package com.example.jack.reminder.data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyTime {
    List<String> weeklyDays, yearlyMonths;

    int dayOfMonth, dayOfWeek, month, year, hour, minute;
    String amPm;
    boolean finalDateSetFlag, finalTimeSetFlag;


    public MyTime(int dayOfMonth, int dayOfWeek, int year, int month, int hour, int minute, String amPm) {

        finalDateSetFlag = true;
        finalTimeSetFlag = true;

        setLists();

        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.year = year;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
        this.amPm = amPm;
    }

    public MyTime(){
        finalDateSetFlag = false;
        finalTimeSetFlag = false;

        setLists();

        this.dayOfMonth = -1;
        this.dayOfWeek = -1;
        this.year = -1;
        this.month = -1;
        this.hour = -1;
        this.minute = -1;
        this.amPm = "";
    }


    private void setLists(){

        weeklyDays = new ArrayList<>();
        yearlyMonths = new ArrayList<>();
        String[] s = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] x = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Collections.addAll(weeklyDays, s);
        Collections.addAll(yearlyMonths, x);

    }

    public void setDateManually(int dayOfMonth, int dayOfWeek, int month, int year){
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.month = month;
        this.year = year;

        finalDateSetFlag = true;

    }

    public void setTimeManually(int hour, int minute, String amPm){
        this.hour = hour;
        this.minute = minute;
        this.amPm = amPm;

        finalTimeSetFlag = true;

    }

    public int isFlagSet(){
        if(finalDateSetFlag==true && finalTimeSetFlag == false)     // time is not set yet
            return 1;

        else if(finalDateSetFlag==false && finalTimeSetFlag == true)     // date is not set yet
            return 2;
        else if(finalDateSetFlag==false && finalTimeSetFlag == false)     // none is not set
            return 3;

        else    // everything is set;
            return 4;
    }

    public boolean isTimeSet(){
        return finalTimeSetFlag;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour24Format(){
        int a = hour;
        if(a == 12)
            a = 0;

        if(amPm.equals("PM"))
            a = a+12;

        return a;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getAmPm() {
        return amPm;
    }

    public boolean isDateSet(){
        return finalDateSetFlag;
    }

    public String getDayOfWeekString(){
        return weeklyDays.get(dayOfWeek-1);
    }

    public String getDayString(){       // used for displaying date in screen button
        if(finalDateSetFlag == false)
            return "No Date Selected";

        String s = dayOfMonth+" "+yearlyMonths.get(month)+", "+year+". "+weeklyDays.get(dayOfWeek);
        return s;
    }

    public String getTimeString(){
        if(finalTimeSetFlag == false)
            return "No Time Selected";

        String s = hour+":"+minute+" "+amPm;
        return s;
    }

/*
    @Override
    public String toString(){

    }*/
}
