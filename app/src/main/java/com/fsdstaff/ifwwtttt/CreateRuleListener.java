package com.fsdstaff.ifwwtttt;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.fsdstaff.ifwwtttt.RuleGrammar.IfThen;

import java.util.HashMap;

/**
 * Created by adithyah on 12/16/15.
 */
public class CreateRuleListener implements AdapterView.OnItemSelectedListener {
    HashMap<String, App> appMap;
    Rule rule;
    LinearLayout pLayout;
    IfThen.Type ifThenType;

    public CreateRuleListener(HashMap<String, App> appMap,
                              Rule rule,
                              LinearLayout pLayout,
                              IfThen.Type ifThenType) {
        this.appMap = appMap;
        this.rule = rule;
        this.pLayout = pLayout;
        this.ifThenType = ifThenType;
    }

    public CreateRuleListener setAppMap(HashMap<String, App> appMap){
        this.appMap = appMap;
        return this;
    }

    public CreateRuleListener setParentLayout(LinearLayout pLayout){
        this.pLayout = pLayout;
        return this;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position > 0) {
            CreateRuleSpinner createRuleSpinner = (CreateRuleSpinner) parent;
            App chosenApp = (App) parent.getItemAtPosition(position);
            CreateRuleSpinner.Type sType = createRuleSpinner.getType();
            if (ifThenType == IfThen.Type.IF) {
                this.rule.setIfCond(new IfThen().setChosenApp(chosenApp));
            } else {
                this.rule.setThen(new IfThen().setChosenApp(chosenApp));
            }
            chosenApp.addLayoutComponents(ifThenType, pLayout);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
