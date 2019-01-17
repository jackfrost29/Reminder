package com.example.jack.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initItems();
        recyclerView = findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this, items));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // called when the + button on app bar is pressed
    // generates pop up window from PopActivity

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            // nothing yet
            startActivity(new Intent(getApplicationContext(), PopActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    public void initItems(){
        for(int i=1; i<=100; i++){
            items.add(new Item("Item "+i, "This is the "+i+"th item."));
        }
    }

}
