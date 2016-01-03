package com.fsdstaff.ifwwtttt.RuleGrammar;

import android.content.Intent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by adithyah on 12/13/15.
 */
public class Then implements Serializable {
    private String chosenApp;
    private String feature;
    private String value;

    public String getChosenApp() {
        return chosenApp;
    }

    public Then setChosenApp(String chosenApp) {
        this.chosenApp = chosenApp;
        return this;
    }

    public String getFeature() {
        return feature;
    }

    public Then setFeature(String feature) {
        this.feature = feature;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Then setValue(String value) {
        this.value = value;
        return this;
    }
}
