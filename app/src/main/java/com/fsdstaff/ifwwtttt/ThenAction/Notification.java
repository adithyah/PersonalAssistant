package com.fsdstaff.ifwwtttt.ThenAction;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.fsdstaff.ifwwtttt.R;

/**
 * Created by adithyah on 1/26/16.
 */
public class Notification {
    public static final String name = "NOTIF";
    String appName;
    String featureName;
    Context context;

    public Notification(String appName, String featureName, Context context) {
        this.appName = appName;
        this.featureName = featureName;
        this.context = context;
    }

    public void createNotification(Intent nIntent, String message){
        PendingIntent pIntent = PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(),
                nIntent, 0);
        android.app.Notification notif = new android.app.Notification.Builder(this.context)
                .setContentTitle(this.appName + ":" + this.featureName)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();
        NotificationManager nManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(0, notif);
    }
}
