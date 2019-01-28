package com.example.jack.reminder.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.jack.reminder.R;

public class NotificationHelper extends ContextWrapper {

    public static final String channel1ID = "chalnel1ID";
    public static final String channel1Name = "Chalnel 1";
    public static final String channel2ID = "Chalnel2ID";
    public static final String channel2Name = "Chalnel 2";

    NotificationManager notificationManager;
    Uri uri;


    public NotificationHelper(Context base) {
        super(base);

        uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(uri == null){
            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if(uri == null){
                uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels(){
        NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);

        NotificationChannel channel2 = new NotificationChannel(channel2ID, channel2Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.colorPrimary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel2);

    }

    public NotificationManager getManager(){
        if(notificationManager == null)
            notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        return notificationManager;

    }

    public NotificationCompat.Builder getChannel1Notification(String title, String message){
        // assuming channel 1 for ReminderDetailsActivity

  /*      Intent resultIntent = new Intent(this, ReminderDetailsActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
*/
        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                .setSound(uri)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder getChannel2Notification(String title, String message){
        // assuming channel 2 for ListItemActivity
/*
        Intent resultIntent = new Intent(this, ListItemDetailActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/
        return new NotificationCompat.Builder(getApplicationContext(), channel2ID)
                .setSound(uri)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true);
    }
}
