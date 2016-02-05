package com.fsdstaff.ifwwtttt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.fsdstaff.ifwwtttt.RuleGrammar.IfThen;
import com.fsdstaff.ifwwtttt.RuleGrammar.Operator;
import com.fsdstaff.ifwwtttt.RuleGrammar.When;
import com.fsdstaff.ifwwtttt.WeatherAPI.WeatherApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CreateRuleActivity extends AppCompatActivity {
    HashMap<String, App> appMap;
    Rule rule;
    EditText ruleNameText;
    LinearLayout ifLayout;
    LinearLayout whenLayout;
    LinearLayout thenLayout;
    CreateRuleSpinner ifAppSpinner;
    CreateRuleSpinner hourSpinner;
    CreateRuleSpinner minSpinner;
    CreateRuleSpinner intervalSpinner;
    CreateRuleSpinner thenAppSpinner;

    /* Yet to be used */
    List<Operator> operatorList;
    Spinner operatorSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rule);
        appMap                              = new HashMap<>();
        populateApps();
        rule                                = new Rule();
        ruleNameText                        = (EditText) findViewById(R.id.ruleNameText);
        ifLayout                            = (LinearLayout) findViewById(R.id.ifLayout);
        whenLayout                          = (LinearLayout) findViewById(R.id.whenLayout);
        thenLayout                          = (LinearLayout) findViewById(R.id.thenLayout);
        ifAppSpinner                        = new CreateRuleSpinner(this);
        hourSpinner                         = new CreateRuleSpinner(this);
        minSpinner                          = new CreateRuleSpinner(this);
        intervalSpinner                     = new CreateRuleSpinner(this);
        thenAppSpinner                      = new CreateRuleSpinner(this);
        CreateRuleListener ifAppListener    = new CreateRuleListener(appMap, rule, ifLayout, IfThen.Type.IF);
        CreateRuleListener thenAppListener  = new CreateRuleListener(appMap, rule, thenLayout, IfThen.Type.THEN);

        ifAppSpinner.setName("Choose If - App")
                .setItems(new ArrayList<Object>(appMap.values()))
                .setType(CreateRuleSpinner.Type.IF_APP)
                .setOnItemSelectedListener(ifAppListener);
        hourSpinner.setName("Choose When - Hour")
                .setItems(new ArrayList<Object>(Arrays.asList(When.HOURS)))
                .setType(CreateRuleSpinner.Type.IF_HOUR);
        minSpinner.setName("Choose When - Min")
                .setItems(new ArrayList<Object>(Arrays.asList(When.MINS)))
                .setType(CreateRuleSpinner.Type.IF_MIN);
        intervalSpinner.setName("Choose When - Interval")
                .setItems(new ArrayList<Object>(Arrays.asList(When.INTERVALS)))
                .setType(CreateRuleSpinner.Type.IF_INTERVAL);
        thenAppSpinner.setName("Choose Then - App")
                .setItems(new ArrayList<Object>(appMap.values()))
                .setType(CreateRuleSpinner.Type.THEN_APP)
                .setOnItemSelectedListener(thenAppListener);

        ifLayout.addView(ifAppSpinner);
        whenLayout.addView(hourSpinner);
        whenLayout.addView(minSpinner);
        whenLayout.addView(intervalSpinner);
        thenLayout.addView(thenAppSpinner);

    }

    public void registerOnClick(View view){
        if(rule.getIfCond() != null) {
            App chosenIfApp = appMap.get(rule.getIfCond().getChosenApp());
            chosenIfApp.scanLayoutComponents(IfThen.Type.IF, ifLayout, rule);
        }

        String ruleName = ruleNameText.getText().toString();
        rule.setName(ruleName);

        App chosenThenApp = appMap.get(rule.getThen().getChosenApp());
        chosenThenApp.scanLayoutComponents(IfThen.Type.THEN, thenLayout, rule);

        scanWhenLayout();

        Intent result = new Intent(this, HomeActivity.class);
        result.putExtra(Constants.NEW_RULE, rule);
        setResult(Constants.CREATE_RULE_CODE, result);
        finish();
    }

    protected void scanWhenLayout(){
        When when = new When();
        when.setHour(hourSpinner.getSelectedItem().toString());
        when.setMin(minSpinner.getSelectedItem().toString());
        when.setInterval(intervalSpinner.getSelectedItem().toString());
        rule.setWhen(when);
    }

    private void populateApps(){
        //TODO
        App weather = new WeatherApp();
        appMap.put(weather.getClass().getCanonicalName(), weather);
    }
}
