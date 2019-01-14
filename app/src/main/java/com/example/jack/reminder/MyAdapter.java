package com.example.jack.reminder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Item> items;
    private Context context;

    public MyAdapter(Context context, ArrayList<Item> items){
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        int x = getItemViewType(i);

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_list, viewGroup, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

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
        Item obj = items.get(position)
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
