package com.fsdstaff.ifwwtttt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adithyah on 12/11/15.
 */
public class WakeUpReceiver extends BroadcastReceiver {
    List<Rule> ruleList;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Constants.DEBUG, "calling WakeUpReceiver");
        List<Intent> thenIntents = new ArrayList<>();
        HashMap<String, App> appMap = new HashMap<>();
        try {
            FileInputStream fis = context.openFileInput(Constants.RULES_FILE);
            ObjectInputStream is = new ObjectInputStream(fis);
            ruleList = (List<Rule>) is.readObject();
            is.close();
            fis.close();
        }
        catch (FileNotFoundException e){
            Log.e("WakeUpReceiver", "file not found");
            e.printStackTrace();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        for(Rule rule : ruleList){
            if(!rule.checkWhen()){
                continue;
            }
            if(!rule.checkIf(context, intent, appMap)){
                continue;
            }
            thenIntents.add(rule.getThenIntent(context, intent, appMap));
        }
        context.startActivities((Intent[]) thenIntents.toArray());
    }
}
