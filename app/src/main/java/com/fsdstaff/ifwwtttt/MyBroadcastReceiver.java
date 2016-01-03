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
import java.util.List;

/**
 * Created by adithyah on 12/11/15.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    List<Rule> ruleList;
    @Override
    public void onReceive(Context context, Intent intent) {
        List<Intent> thenIntents = new ArrayList<Intent>();
        try {
            FileInputStream fis = context.openFileInput(Constants.RULES_FILE);
            ObjectInputStream is = new ObjectInputStream(fis);
            ruleList = (List<Rule>) is.readObject();
            is.close();
            fis.close();
        }
        catch (FileNotFoundException e){
            Log.e("MyBroadcastReceiver", "file not found");
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(Rule rule : ruleList){
            if(!rule.checkWhen()){
                continue;
            }
            if(!rule.checkIf(intent)){
                continue;
            }
            thenIntents.add(rule.getThenIntent(intent));
        }
        context.startActivities((Intent[]) thenIntents.toArray());
    }
}
