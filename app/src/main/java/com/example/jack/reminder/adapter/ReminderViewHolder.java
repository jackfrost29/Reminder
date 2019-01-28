package com.example.jack.reminder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jack.reminder.data.DataHandler;
import com.example.jack.reminder.data.Item;
import com.example.jack.reminder.R;
import com.example.jack.reminder.data.Reminder;
import com.example.jack.reminder.activity.ReminderDetailsActivity;

import java.util.ArrayList;

public class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    Context context;

    TextView alarmTime, alarmDate, alarmDay;    // these are for holding widgets
    Switch alarmSwitch;

    public ReminderViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        alarmTime = itemView.findViewById(R.id.alarm_time);
        alarmDate = itemView.findViewById(R.id.alarm_date);
        alarmDay = itemView.findViewById(R.id.alarm_day);
        alarmSwitch = itemView.findViewById(R.id.alarm_switch);
        itemView.setOnClickListener(this);
        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // not done

                ((Reminder)DataHandler.getItem(getAdapterPosition())).setIsAlarmSet(isChecked);

                if(isChecked){
                    ((Reminder)DataHandler.getItem(getAdapterPosition())).setAlarm();
                }
                else
                    ((Reminder)DataHandler.getItem(getAdapterPosition())).cancelAlarm();

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ReminderDetailsActivity.class);
        int i = getAdapterPosition();
        intent.putExtra("position", i);
        context.startActivity(intent);
    }

}
