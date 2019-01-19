package com.example.jack.reminder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class Sample{   // extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /*class ViewHolder0 extends RecyclerView.ViewHolder {

        public ViewHolder0(View itemView) {
            super(itemView);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {

        public ViewHolder2(View itemView) {
            super(itemView);
        }

    }

        @Override
        public int getItemViewType(int position) {
            // Just as an example, return 0 or 2 depending on position
            // Note that unlike in ListView adapters, types don't have to be contiguous
            return position % 2 * 2;
        }

    @Override
    public int getItemCount() {
        return 0;
    }

    // on create view holder: it creates new viewholder from inflating new view
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewHolder0(...);
            case 2:
                return new ViewHolder2(...);
        }
    }

    // this method binds the view holder with data of the parcitular position
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolder0 viewHolder0 = (ViewHolder0) holder;
            ...
                break;

            case 2:
                ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            ...
                break;
        }
    }*/
}