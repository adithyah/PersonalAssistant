package com.fsdstaff.ifwwtttt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

import com.fsdstaff.ifwwtttt.RuleGrammar.Operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateRuleActivity extends AppCompatActivity {
    private LinearLayout homeLayout;
    HashMap<String, App> appMap;
    MySpinner appSpinner;
    Button registerButton;
    Rule rule;

    /* Yet to be used */
    List<Operator> operatorList;
    Spinner operatorSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateApps();
        rule = new Rule(appMap);
        homeLayout = (LinearLayout) findViewById(R.id.HomeLayout);
        MySpinnerListener appListener = new MySpinnerListener(appMap, rule,
                homeLayout);
        appSpinner = new MySpinner(this);
        appSpinner.setName("Choose If - App")
                .setItems(new ArrayList<Object>(appMap.values()))
                .setType(MySpinner.Type.IF_APP)
                .setOnItemSelectedListener(appListener);

        addRegisterButton();

        setContentView(R.layout.activity_create_rule);
    }

    private void addRegisterButton(){
        registerButton = new Button(this);
        registerButton.setText("Register");
        registerButton.setWidth(LayoutParams.WRAP_CONTENT);
        registerButton.setHeight(LayoutParams.WRAP_CONTENT);
        registerButton.setGravity(Gravity.BOTTOM);
        homeLayout.addView(registerButton);

    }

    protected void registerOnClick(View view){
        Intent result = new Intent(this, HomeActivity.class);
        //set parameters of rules
        result.putExtra(Constants.NEW_RULE, rule);
        setResult(Constants.CREATE_RULE_CODE, result);
        finish();
    }

    private void populateApps(){

    }
}
