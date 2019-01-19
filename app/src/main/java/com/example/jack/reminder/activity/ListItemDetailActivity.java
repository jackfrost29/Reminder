package com.example.jack.reminder.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jack.reminder.data.DataHandler;
import com.example.jack.reminder.data.ListItem;
import com.example.jack.reminder.R;

import java.util.ArrayList;

public class ListItemDetailActivity extends Activity {

    private LinearLayout parentLinearLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_details, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String s = getIntent().getStringExtra("index");

        if(item.getItemId() == R.id.action_save){
            // save newly created item to item list
            int count = parentLinearLayout.getChildCount();
            ArrayList<String> createdList = new ArrayList<>();

            for(int i=0; i<count-1; i++){   // runs count-1 times as count'th time is the button
                View view = parentLinearLayout.getChildAt(i);
                EditText x = (EditText)view.findViewById(R.id.number_edit_text);
                createdList.add(x.getText().toString());
            }
            DataHandler.setNewItem(new ListItem("<to-do-list>", createdList));
        }

        else if(item.getItemId() == R.id.action_delete){
            if(s != null){
                // this item is created in items list, need to be destroyed
                int i = Integer.parseInt(getIntent().getStringExtra("index"));
                DataHandler.deleteItem(i);
            }

        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
    }


    public void onAddField(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }

}
