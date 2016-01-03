package com.fsdstaff.ifwwtttt;

import android.content.Intent;

import com.fsdstaff.ifwwtttt.RuleGrammar.If;

import java.util.HashMap;

/**
 * Created by adithyah on 12/6/15.
 */
public abstract class App{
    String name;
    HashMap<String, Feature> ifFeature;
    HashMap<String, Feature> thenFeature;

    public String getName(){
        return name;
    }

    public String toString() {
        return name;
    }

    public HashMap<String, Feature> getIfFeature() {
        return ifFeature;
    }

    public HashMap<String, Feature> getThenFeature() {
        return thenFeature;
    }

    /**
     * Called from the broadcastReceiver
     * an action to be executed as a result of a successful "if" condition
     * any info required from the "if" is found in the bdIntent
     *
     * @param   fName       name of the feature in the App
     * @param   bdIntent    the intent that triggered the broadcast receiver
     * @return              an action in the inform of intent, to be executed
     */
    public abstract Intent getThenIntent(String fName, Intent bdIntent);

    /**
     * Called from the broadcastReceiver.
     * some checks will result in an object to be returned. In such cases, the object
     * should be serialized and stored in the bdIntent
     *
     * @param   ifCond      "if" condition to be checked
     * @param   bdIntent    the intent that triggered the broadcast receiver
     * @return              "if" condition passes or fails
     */
    public abstract boolean checkIf(If ifCond, Intent bdIntent);

}
