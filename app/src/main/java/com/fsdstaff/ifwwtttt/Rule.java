package com.fsdstaff.ifwwtttt;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fsdstaff.ifwwtttt.RuleGrammar.IfThen;
import com.fsdstaff.ifwwtttt.RuleGrammar.When;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adithyah on 12/6/15.
 */
public class Rule implements Serializable {
    IfThen ifCond;
    When when;
    IfThen then;
    String name;

    public String getName() {
        return name;
    }

    public Rule setName(String name) {
        this.name = name;
        return this;
    }

    public IfThen getIfCond() {
        return ifCond;
    }

    public When getWhen() {
        return when;
    }

    public IfThen getThen() {
        return then;
    }

    public void setIfCond(IfThen ifCond) {
        this.ifCond = ifCond;
    }

    public void setWhen(When when) {
        this.when = when;
    }

    public void setThen(IfThen then) {
        this.then = then;
    }

    public List<String> getIfBdEventList(){
        return ifCond.getBdEventList();
    }

    public Intent getThenIntent(Context context, Intent intent, HashMap<String, App> appMap){
        App thenApp = getAppInstance(context, then.getChosenApp(), appMap);
        return thenApp.getThenIntent(context, then, intent);
    }

    public boolean checkIf(Context context, Intent bdIntent, HashMap<String, App> appMap){
        if(ifCond != null) {
            App ifApp = getAppInstance(context, ifCond.getChosenApp(), appMap);
            return ifApp.checkIf(context, ifCond, bdIntent);
        }
        return true;
    }

    public boolean checkWhen(){
        return when.check();
    }

    private App getAppInstance(Context context, String appName, HashMap<String, App> appMap){
        App app = appMap.get(appName);
        if(app == null) {
            try {
                Class<?> appClass = Class.forName(appName);
                app = (App) appClass.getMethod("getInstance").invoke(null);
                appMap.put(appName, app);
            } catch (ClassNotFoundException e) {
                Log.e("RULE", "class name " + appName);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                Log.e("RULE", "class name " + appName);
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                Log.e("RULE", "class name " + appName);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Log.e("RULE", "class name " + appName);
                e.printStackTrace();
            }
        }
        return app;
    }

}