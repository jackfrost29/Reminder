package com.example.jack.reminder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jack.reminder.data.Item;
import com.example.jack.reminder.data.ListItem;
import com.example.jack.reminder.data.Note;
import com.example.jack.reminder.R;
import com.example.jack.reminder.data.Reminder;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Item> items;
    private Context context;

    public MyAdapter(Context context, ArrayList<Item> items){
        this.items = items;
        this.context = context;
    }


    // on create view holder: it creates new viewholder from inflating new view
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {

        RecyclerView.ViewHolder obj;

        if(type == 1){     // Reminder

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_reminder_view, viewGroup, false);
            obj = new ReminderViewHolder(view);
        }

        else if(type == 2){       // Note

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_plain_note_view, viewGroup, false);
            obj = new NoteViewHolder(view);
        }
        else {   // List

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list_view, viewGroup, false);
            obj = new ListItemViewHolder(view);
        }

        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Item item;

        if(viewHolder instanceof ReminderViewHolder){

            // set row view of reminder to the viewholder
            item = (Reminder)items.get(i);
            ((ReminderViewHolder) viewHolder).alarmTime.setText(((Reminder)item).getRowViewAlarmTimeText());
            ((ReminderViewHolder) viewHolder).alarmDate.setText(((Reminder)item).getRemTime().getDayString());
            ((ReminderViewHolder) viewHolder).alarmDay.setText(((Reminder)item).getRemTime().getDayOfWeekString());
            ((ReminderViewHolder) viewHolder).alarmSwitch.setChecked(((Reminder)item).getIsAlarmSet());
        }

        else if(viewHolder instanceof ListItemViewHolder){

            // set row view of reminder to the viewholder
            item = (ListItem)items.get(i);
            ((ListItemViewHolder)viewHolder).title.setText(((ListItem)item).getTitle());
            ((ListItemViewHolder)viewHolder).txtView1.setText(((ListItem)item).getFirstText());
            ((ListItemViewHolder)viewHolder).txtView2.setText(((ListItem)item).getSecondText());
            ((ListItemViewHolder)viewHolder).txtViewDate.setText(((ListItem)item).getRemTime().getDayString());
        }

        else if(viewHolder instanceof NoteViewHolder){
            item = (Note)items.get(i);
            ((NoteViewHolder) viewHolder).title.setText(((Note)item).getTitle());
            ((NoteViewHolder) viewHolder).date.setText(((Note)item).getRowViewDateString());
            ((NoteViewHolder) viewHolder).text.setText(((Note)item).getText());

        }
    }



    @Override
    public int getItemViewType(int position){
        Item obj = items.get(position);
        if(obj instanceof Reminder)
            return 1;
        else if(obj instanceof Note)
            return 2;
        else
            return 3;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
