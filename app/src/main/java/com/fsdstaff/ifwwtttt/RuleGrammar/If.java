package com.fsdstaff.ifwwtttt.RuleGrammar;

import java.io.Serializable;

/**
 * Created by adithyah on 12/13/15.
 */
public class If implements Serializable {
    private String chosenApp;
    private String feature;
    private String value;

    public String getChosenApp() {
        return chosenApp;
    }

    public If setChosenApp(String chosenApp) {
        this.chosenApp = chosenApp;
        return this;
    }

    public String getFeature() {
        return feature;
    }

    public If setFeature(String feature) {
        this.feature = feature;
        return this;
    }

    public String getValue() {
        return value;
    }

    public If setValue(String value) {
        this.value = value;
        return this;
    }
}
