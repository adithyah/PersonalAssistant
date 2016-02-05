package com.fsdstaff.ifwwtttt;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Created by adithyah on 12/18/15.
 */
public class Feature {
    protected String name;
    protected String appName;
    protected List<String> values;
    protected List<String> thenActions;
    protected List<String> bdEvents;

    public Feature(String appName){
        this.appName = appName;
    }

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        return values;
    }

    public String toString() {
        return name;
    }

    public List<String> getThenActions() {
        return thenActions;
    }

    public List<String> getbdEvents() {
        return bdEvents;
    }
}
