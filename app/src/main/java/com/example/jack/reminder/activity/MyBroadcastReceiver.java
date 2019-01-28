package com.example.jack.reminder.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.jack.reminder.data.DataHandler;
import com.example.jack.reminder.data.Item;
import com.example.jack.reminder.data.ListItem;
import com.example.jack.reminder.data.Reminder;

public class MyBroadcastReceiver extends BroadcastReceiver {

    // show notifications for reminder or any list

    NotificationHelper notificationHelper;
    NotificationCompat.Builder nb;

    @Override
    public void onReceive(Context context, Intent intent) {
        /* where will these intents be created
         from reminderDetailsActivity
         or from ListItemDetailsActivity
         So in order to get the correct notification message
         the position is required

         */

        Log.d("onReceive: ", "begin");

        notificationHelper = new NotificationHelper(context);

        int position = intent.getIntExtra("position", 0);
        String source = intent.getStringExtra("source");
        Item obj;

        if(source.equals("Reminder")) {
            obj = (Reminder) DataHandler.getItem(position);
            nb = notificationHelper.getChannel1Notification("You set a reminder", obj.getTitle());

            notificationHelper.getManager().notify(1, nb.build());
        }

        else {
            obj = (ListItem) DataHandler.getItem(position);
            nb = notificationHelper.getChannel2Notification("You have a due list today", obj.getTitle());

            notificationHelper.getManager().notify(2, nb.build());
        }

        // build notification and set title and message from object


    }
}
