package com.fsdstaff.ifwwtttt;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by adithyah on 12/14/15.
 */

public class WakeUpService extends Service {
    WakeUpReceiver myReceiver;
    IntentFilter myReceiverActions;

    @Override
    public void onCreate() {
        super.onCreate();
        myReceiver = new WakeUpReceiver();
        myReceiverActions = new IntentFilter();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.DEBUG, "starting WakeUpService");
        List<String> actions = intent.getStringArrayListExtra(Constants.RECEIVER_ACTIONS);
        for (String action : actions){
            myReceiverActions.addAction(action);
        }
        registerReceiver(myReceiver, myReceiverActions);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.DEBUG, "closing WakeUpService");
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
