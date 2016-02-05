package com.fsdstaff.ifwwtttt;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;

import com.fsdstaff.ifwwtttt.RuleGrammar.IfThen;

import java.io.Serializable;

/**
 * Created by adithyah on 12/6/15.
 */
public abstract class App {
    protected String name;

    public String getName(){
        return name;
    }

    public String toString() {
        return name;
    }

    /**
     *
     * @param ifThenType
     * @param pLayout
     */
    public abstract void scanLayoutComponents(IfThen.Type ifThenType, LinearLayout pLayout, Rule rule);

    /**
     * Called from CreateRuleListener
     * Apps have different requirements to be configured
     * use this function to create new components and display
     * @param ifThenType    Type of Spinner that called this function (if or then)
     * @param pLayout       parent layout - use this to add new components
     */
    public abstract void addLayoutComponents(IfThen.Type ifThenType, LinearLayout pLayout);
    /**
     * Called from the broadcastReceiver
     * an action to be executed as a result of a successful "if" condition
     * any info required from the "if" is found in the bdIntent
     *
     * @param   then        "then" action to be performed
     * @param   bdIntent    the intent that triggered the broadcast receiver
     * @param   context     context from the calling broadcastReceiver
     * @return              an action in the inform of intent, to be executed
     */
    public abstract Intent getThenIntent(Context context, IfThen then, Intent bdIntent);

    /**
     * Called from the broadcastReceiver.
     * some checks will result in an object to be returned. In such cases, the object
     * should be serialized and stored in the bdIntent
     *
     * @param   ifCond      "if" condition to be checked
     * @param   bdIntent    the intent that triggered the broadcast receiver
     * @param   context     context from the calling broadcastReceiver
     * @return              "if" condition passes or fails
     */
    public abstract boolean checkIf(Context context, IfThen ifCond, Intent bdIntent);

    public abstract App getInstance();

}
