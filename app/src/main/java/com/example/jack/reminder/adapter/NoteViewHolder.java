package com.example.jack.reminder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.reminder.data.Item;
import com.example.jack.reminder.data.Note;

import java.util.List;

public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    List<Item> items;
    TextView title, text;
    LinearLayout parent;
    public Note note;
    public int position;
    public NoteViewHolder(@NonNull View itemView, int position, Context context) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.note = note;
        this.position = position;
    }

    @Override
    public void onClick(View v) {

    }

    public void setItem(Note mNote, int mPosition){

    }
}
