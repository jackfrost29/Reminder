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

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> items;
    private Context context;

    public MyAdapter(Context context, List<Item> items){
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_list, viewGroup, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, final int i) {
        final Item item = items.get(i);

        viewHolder.intro.setText(item.intro);
        viewHolder.title.setText(item.title);

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item " +(i+1)+ " was clicked!", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, ItemDetailsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
