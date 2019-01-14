package com.example.jack.reminder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListItem extends Item {
    Date date;
    List<String> list;
    public ListItem(String title, ArrayList<String> list, Date date) {
        super(title);
        this.list = list;
        this.date = date;
    }
}
