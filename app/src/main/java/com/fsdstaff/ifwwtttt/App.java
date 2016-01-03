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

    public abstract Intent getThenIntent(String fName, Intent intent);

    public abstract boolean checkIf(If IfCond, Intent bdIntent);

}
