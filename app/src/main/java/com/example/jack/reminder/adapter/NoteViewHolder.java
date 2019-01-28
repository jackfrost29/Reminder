package com.example.jack.reminder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.reminder.R;
import com.example.jack.reminder.activity.NoteDetailsActivity;
import com.example.jack.reminder.data.Item;
import com.example.jack.reminder.data.Note;

import java.util.List;

public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView title, text, date;


    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        date = (TextView) itemView.findViewById(R.id.row_note_view_date_id);
        title = (TextView) itemView.findViewById(R.id.row_note_view_title_id);
        text = (TextView) itemView.findViewById(R.id.row_note_view_text_id);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), NoteDetailsActivity.class);
        int i = getAdapterPosition();
        intent.putExtra("position", i);
        v.getContext().startActivity(intent);

    }


}
