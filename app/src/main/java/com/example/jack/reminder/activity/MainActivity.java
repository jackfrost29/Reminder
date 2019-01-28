package com.example.jack.reminder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jack.reminder.data.DataHandler;
import com.example.jack.reminder.data.Item;
import com.example.jack.reminder.adapter.MyAdapter;
import com.example.jack.reminder.R;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // data will be loader from files inside this method

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            DataHandler.load(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        items =  DataHandler.getFullList();

        recyclerView = findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this, items));

    }

    @Override
    protected void onStart() {
        super.onStart();

        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // called when the + button on app bar is pressed
    // generates pop up window from PopActivity

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            startActivity(new Intent(getApplicationContext(), PopActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }
/*

    public void onClickFAB(View view) {
        // create the pop activity
        startActivity(new Intent(this, PopActivity.class));
    }*/
}
