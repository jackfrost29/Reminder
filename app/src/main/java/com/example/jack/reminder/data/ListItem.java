package com.example.jack.reminder.data;

import java.util.ArrayList;
import java.util.List;

public class ListItem extends Item {
    List<String> list;
    public ListItem(String title, ArrayList<String> list) {
        super(title);
        this.list = list;
    }

    public ListItem(ListItem item){
        super(item.title);
        this.list = item.list;

    }
}
