package com.fsdstaff.ifwwtttt.RuleGrammar;

import com.fsdstaff.ifwwtttt.App;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adithyah on 12/13/15.
 */
public class IfThen implements Serializable {
    private String                  chosenApp;
    private HashMap<String, String> appParameters;
    private List<String>            bdEventList;
    private Type                    ifThenType;

    public IfThen(){
        this.appParameters = new HashMap<>();
        this.bdEventList = new ArrayList<>();
    }

    public String getChosenApp() {
        return chosenApp;
    }

    public HashMap<String, String> getAppParameters() {
        return appParameters;
    }

    public List<String> getBdEventList() {
        return bdEventList;
    }

    public Type getIfThenType() {
        return ifThenType;
    }

    public IfThen setChosenApp(App chosenApp) {
        this.chosenApp = chosenApp.getClass().getCanonicalName();
        return this;
    }

    public IfThen setBdEventList(List<String> actionList) {
        this.bdEventList = actionList;
        return this;
    }

    public IfThen setIfThenType(Type ifThenType) {
        this.ifThenType = ifThenType;
        return this;
    }

    public enum Type {
        IF, THEN
    }
}
