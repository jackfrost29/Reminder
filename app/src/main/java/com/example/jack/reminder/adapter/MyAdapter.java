package com.example.jack.reminder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jack.reminder.data.Item;
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

            obj = new ReminderViewHolder(view, i, context);
        }

        else if(type == 2){       // Note
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_plain_note_view, viewGroup, false);

            obj = new NoteViewHolder(view, context);
        }
        else{   // List

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ReminderViewHolder){

        }
        el
    }
/*
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, final int i) {



        final Item item = items.get(i);

        viewHolder.intro.setText(item.intro);
        viewHolder.title.setText(item.title);
        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item " +(i+1)+ " was clicked!", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, NoteDetailsActivity.class));
            }
        });

    }*/

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
