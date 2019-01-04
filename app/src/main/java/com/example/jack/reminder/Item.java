package com.example.jack.reminder;

import java.util.Date;

public class Item {
    String title, intro;
    Date date;
    public Item(String title, String intro, Date date){
        this.title = title;
        this.intro = intro;
        this.date = date;
    }
}