package com.fsdstaff.ifwwtttt;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.fsdstaff.ifwwtttt.RuleGrammar.When;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    List<Rule>              ruleList;
    ListView                ruleLv;
    Button                  createRuleButton;
    AlarmManager            alarmManager;
    PendingIntent           bdPendIntent;
    Intent                  bdServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<Rule> ruleAdapter  = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, getRuleList());
        ruleLv                          = (ListView) findViewById(R.id.Rules);
        createRuleButton                = (Button) findViewById(R.id.CreateRule);
        alarmManager                    = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent broadcastIntent          = new Intent(this, WakeUpReceiver.class);
        bdPendIntent                    = PendingIntent.getBroadcast(this, Constants.BD_REC_CODE,
                broadcastIntent, 0);
        bdServiceIntent                 = new Intent(this, WakeUpService.class);

        ruleLv.setAdapter(ruleAdapter);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == Constants.CREATE_RULE_CODE){
            Rule newRule = (Rule) data.getSerializableExtra(Constants.NEW_RULE);
            ruleList.add(newRule);

            writeRulesToDisk();
            updateAlarmManager(newRule);

            bdServiceIntent.putExtra(Constants.RECEIVER_ACTIONS, getBdActionList());
            stopService(bdServiceIntent);
            startService(bdServiceIntent);
        }
    }

    protected List<Rule> getRuleList(){
        if(ruleList == null){
            try {
                FileInputStream fis = this.openFileInput(Constants.RULES_FILE);
                ObjectInputStream is = new ObjectInputStream(fis);
                ruleList = (List<Rule>) is.readObject();
                is.close();
                fis.close();
            }
            catch (FileNotFoundException e){
                ruleList = new ArrayList<>();
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        return ruleList;
    }

    protected void writeRulesToDisk(){
        try {
            FileOutputStream fos = this.openFileOutput(Constants.RULES_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ruleList);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        This is attached to the onClick listener of the createRuleButton in the XML file
     */
    protected void createRule(View view){
        Intent intent = new Intent(this, CreateRuleActivity.class);
        startActivityForResult(intent, Constants.CREATE_RULE_CODE);
    }


    protected void updateAlarmManager(Rule newRule){
        When when = newRule.getWhen();
        Calendar cal = CalendarHelper.convertTimeToCal(when.getTriggerTime());
        if(when.getTriggerInterval() == 0){
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), bdPendIntent);
        }
        else {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                    when.getTriggerInterval(), bdPendIntent);
        }
    }

    protected ArrayList<String> getBdActionList(){
        ArrayList<String> bdActionList = new ArrayList<>();
        for (Rule rule : ruleList){
            bdActionList.addAll(rule.getIfActionList());
        }
        return bdActionList;
    }
}
