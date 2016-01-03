package com.fsdstaff.ifwwtttt;

import android.content.Intent;

import com.fsdstaff.ifwwtttt.App;
import com.fsdstaff.ifwwtttt.Feature;
import com.fsdstaff.ifwwtttt.RuleGrammar.If;
import com.fsdstaff.ifwwtttt.RuleGrammar.Then;
import com.fsdstaff.ifwwtttt.RuleGrammar.When;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adithyah on 12/6/15.
 */
public class Rule implements Serializable {
    If ifCond;
    When when;
    Then then;
    HashMap<String, App> appMap;

    public Rule(HashMap<String, App> appMap) {
        this.appMap = appMap;
    }

    public If getIfCond() {
        return ifCond;
    }

    public When getWhen() {
        return when;
    }

    public Then getThen() {
        return then;
    }

    public void setIfCond(If ifCond) {
        this.ifCond = ifCond;
    }

    public void setWhen(When when) {
        this.when = when;
    }

    public void setThen(Then then) {
        this.then = then;
    }

    public List<String> getIfActionList(){
        App app = appMap.get(ifCond.getChosenApp());
        Feature feature = app.getIfFeature().get(ifCond.getFeature());
        return feature.getActions();
    }

    public Intent getThenIntent(Intent intent){
        App app = appMap.get(then.getChosenApp());
        return app.getThenIntent(then.getFeature(), intent);
    }

    public boolean checkIf(Intent bdIntent){
        App app = appMap.get(ifCond.getChosenApp());
        return app.checkIf(ifCond, bdIntent);
    }

    public boolean checkWhen(){
        return when.check();
    }

}