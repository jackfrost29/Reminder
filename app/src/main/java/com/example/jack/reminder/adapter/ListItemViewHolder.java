package com.example.jack.reminder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jack.reminder.R;
import com.example.jack.reminder.activity.ListItemDetailActivity;

public class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, txtView1, txtView2, txtViewDate;


    public ListItemViewHolder(@NonNull View view) {
        super(view);
        title = (TextView)view.findViewById(R.id.row_list_item_title_textView);
        txtView1 = (TextView)view.findViewById(R.id.row_list_item_firstElement);
        txtView2 = (TextView)view.findViewById(R.id.row_list_item_secondElement);
        txtViewDate = (TextView)view.findViewById(R.id.row_list_item_date);

        view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = getAdapterPosition();
        Intent intent = new Intent(v.getContext(), ListItemDetailActivity.class);
        intent.putExtra("position", i);
        v.getContext().startActivity(intent);
    }
}
