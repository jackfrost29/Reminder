package com.example.jack.reminder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    List<Item> items;
    TextView title, text;
    LinearLayout parent;
    public Note mNote;
    public int mPosition;
    public NoteViewHolder(@NonNull View itemView, Note mNote, int mPosition) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mNote = mNote;
        this.mPosition = mPosition;
    }

    @Override
    public void onClick(View v) {

    }

    public void setItem(Note mNote, int mPosition){

    }
}
