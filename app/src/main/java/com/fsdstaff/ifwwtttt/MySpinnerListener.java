package com.fsdstaff.ifwwtttt;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.fsdstaff.ifwwtttt.RuleGrammar.If;
import com.fsdstaff.ifwwtttt.RuleGrammar.Then;
import com.fsdstaff.ifwwtttt.RuleGrammar.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by adithyah on 12/16/15.
 */
public class MySpinnerListener implements AdapterView.OnItemSelectedListener {
    HashMap<String, App> appMap;
    Rule rule;
    LinearLayout pLayout;

    public MySpinnerListener(HashMap<String, App> appMap,
                             Rule rule,
                             LinearLayout pLayout) {
        this.appMap = appMap;
        this.rule = rule;
        this.pLayout = pLayout;
    }

    public MySpinnerListener setAppMap(HashMap<String, App> appMap){
        this.appMap = appMap;
        return this;
    }

    public MySpinnerListener setParentLayout(LinearLayout pLayout){
        this.pLayout = pLayout;
        return this;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MySpinner mySpinner = (MySpinner) parent;
        switch (mySpinner.getType()){
            case IF_APP: {
                App chosenApp = (App) parent.getItemAtPosition(position);
                this.rule.setIfCond(new If().setChosenApp(chosenApp.toString()));
                MySpinner fSpinner = new MySpinner(parent.getContext());
                fSpinner.setName("Choose Feature")
                        .setItems(new ArrayList<Object>(chosenApp.getIfFeature().values()))
                        .setType(MySpinner.Type.IF_FEATURE)
                        .setOnItemSelectedListener(this);
                pLayout.addView(fSpinner);
                break;
            }
            case IF_FEATURE: {
                Feature chosenFeature = (Feature) parent.getItemAtPosition(position);
                this.rule.getIfCond().setFeature(chosenFeature.toString());
                MySpinner vSpinner = new MySpinner(parent.getContext());
                vSpinner.setName("Choose Val")
                        .setItems(new ArrayList<Object>(chosenFeature.getValues()))
                        .setType(MySpinner.Type.IF_VAL)
                        .setOnItemSelectedListener(this);
                pLayout.addView(vSpinner);
                break;
            }
            case IF_VAL: {
                String chosenVal = (String) parent.getItemAtPosition(position);
                this.rule.getIfCond().setValue(chosenVal);
                MySpinner wSpinner = new MySpinner(parent.getContext());
                wSpinner.setName("Choose When - Hour")
                        .setItems(new ArrayList<Object>(Arrays.asList(When.HOURS)))
                        .setType(MySpinner.Type.IF_HOUR)
                        .setOnItemSelectedListener(this);
                pLayout.addView(wSpinner);
            }
            case IF_HOUR: {
                String chosenHour = (String) parent.getItemAtPosition(position);
                this.rule.setWhen(new When().setHour(chosenHour));
                MySpinner wSpinner = new MySpinner(parent.getContext());
                wSpinner.setName("Choose When - Min")
                        .setItems(new ArrayList<Object>(Arrays.asList(When.MINS)))
                        .setType(MySpinner.Type.IF_MIN)
                        .setOnItemSelectedListener(this);
                pLayout.addView(wSpinner);
            }
            case IF_MIN: {
                String chosenMin = (String) parent.getItemAtPosition(position);
                this.rule.getWhen().setMin(chosenMin);
                MySpinner wSpinner = new MySpinner(parent.getContext());
                wSpinner.setName("Choose When - Interval")
                        .setItems(new ArrayList<Object>(Arrays.asList(When.INTERVALS)))
                        .setType(MySpinner.Type.IF_INTERVAL)
                        .setOnItemSelectedListener(this);
                pLayout.addView(wSpinner);
            }
            case IF_INTERVAL: {
                String chosenInterval = (String) parent.getItemAtPosition(position);
                this.rule.getWhen().setInterval(chosenInterval);
                MySpinner wSpinner = new MySpinner(parent.getContext());
                wSpinner.setName("Choose Then - App")
                        .setItems(new ArrayList<Object>(appMap.values()))
                        .setType(MySpinner.Type.THEN_APP)
                        .setOnItemSelectedListener(this);
                pLayout.addView(wSpinner);
                break;
            }
            case THEN_APP: {
                App chosenApp = (App) parent.getItemAtPosition(position);
                this.rule.setThen(new Then().setChosenApp(chosenApp.toString()));
                MySpinner fSpinner = new MySpinner(parent.getContext());
                fSpinner.setName("Choose Feature")
                        .setItems(new ArrayList<Object>(chosenApp.getThenFeature().values()))
                        .setType(MySpinner.Type.THEN_FEATURE)
                        .setOnItemSelectedListener(this);
                pLayout.addView(fSpinner);
                break;
            }
            case THEN_FEATURE: {
                Feature chosenFeature = (Feature) parent.getItemAtPosition(position);
                this.rule.getThen().setFeature(chosenFeature.toString());
                MySpinner vSpinner = new MySpinner(parent.getContext());
                vSpinner.setName("Choose Val")
                        .setItems(new ArrayList<Object>(chosenFeature.getValues()))
                        .setType(MySpinner.Type.THEN_VAL)
                        .setOnItemSelectedListener(this);
                pLayout.addView(vSpinner);
                break;
            }
            case THEN_VAL: {
                String chosenVal = (String) parent.getItemAtPosition(position);
                this.rule.getThen().setValue(chosenVal);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
