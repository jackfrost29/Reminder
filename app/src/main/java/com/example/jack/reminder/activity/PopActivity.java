package com.example.jack.reminder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.jack.reminder.R;
import com.example.jack.reminder.activity.ListItemDetailActivity;
import com.example.jack.reminder.activity.NoteDetailsActivity;
import com.example.jack.reminder.activity.ReminderDetailsActivity;

public class PopActivity extends Activity {

    Button buttonRem, buttonNote, buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics ds = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ds);

        int width = ds.widthPixels;
        int height = ds.heightPixels;


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));
        getWindow().setAttributes(params);

        buttonRem = (Button) findViewById(R.id.button_create_new_reminder);
        buttonRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new reminder will be displayed
                startActivity(new Intent(getApplicationContext(), ReminderDetailsActivity.class));
                finish();
            }
        });

        buttonNote = (Button) findViewById(R.id.button_create_new_note);
        buttonNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new note will be displayed
                startActivity(new Intent(getApplicationContext(), NoteDetailsActivity.class));
                finish();
            }
        });

        buttonList = (Button) findViewById(R.id.button_create_new_list);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new list will be displayed
                startActivity(new Intent(getApplicationContext(), ListItemDetailActivity.class));
                finish();
            }
        });
    }
}
