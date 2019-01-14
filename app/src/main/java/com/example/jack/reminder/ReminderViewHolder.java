package com.example.jack.reminder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ArrayList<Item> items;   // these are for holding data
    Reminder reminder;
    int position;
    Context context;

    TextView alarmTime, alarmDate, alarmDay;    // these are for holding widgets
    Switch alarmSwitch;

    public ReminderViewHolder(@NonNull View itemView, ArrayList<Item> items, int position, Context context) {
        super(itemView);
        this.items = items;
        this.position = position;
        this.context = context;
        alarmTime = itemView.findViewById(R.id.alarm_time);
        alarmDate = itemView.findViewById(R.id.alarm_date);
        alarmDay = itemView.findViewById(R.id.alarm_day);
        alarmSwitch = itemView.findViewById(R.id.alarm_switch);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ReminderDetailsActivity.class);
        intent.putExtra("source", items);
        context.startActivity(intent);
    }
}
