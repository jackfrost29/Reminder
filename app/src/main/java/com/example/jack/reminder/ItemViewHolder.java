package com.example.jack.reminder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    TextView title, intro;
    LinearLayout parent;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.item_title);   // it attaches the layout element
        intro = itemView.findViewById(R.id.item_brief_intro);   // same as above
        parent = itemView.findViewById(R.id.parent);    // same as above
    }
}