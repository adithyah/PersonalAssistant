package com.fsdstaff.ifwwtttt.WeatherAPI;

import android.content.Context;
import android.widget.LinearLayout;

import com.fsdstaff.ifwwtttt.CreateRuleSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adithyah on 2/2/16.
 */
public class WLThenComponents extends WeatherLayoutComponents{
    CreateRuleSpinner featureSp;
    CreateRuleSpinner featureActionSp;
    ArrayList<Object> featureActions;
    List<WeatherFeature> featureList;

    public WLThenComponents(Context context, List<WeatherFeature> featureList) {
        super(context);
        this.featureList = featureList;
        featureSp.setName("Choose Feature")
                .setItems(new ArrayList<Object>(featureList))
                .setOnItemSelectedListener(new ChosenFeatureListener(featureLayout,
                        ParameterType.THEN_FEATURE, featureActions));
        featureActionSp.setName("Choose Action")
                .setItems(featureActions);
    }
    public void addLayoutComponents(LinearLayout pLayout){
        super.addLayoutComponents(pLayout);
        featureLayout.addView(featureSp);
        featureLayout.addView(featureActionSp);
    }
    public void scanLayoutComponents(LinearLayout pLayout, HashMap<String, String> appParameters){
        super.scanLayoutComponents(pLayout, appParameters);
        WeatherFeature chosenFeature = (WeatherFeature) featureSp.getSelectedItem();
        String chosenAction = (String) featureActionSp.getSelectedItem();
        appParameters.put(ParameterType.THEN_FEATURE.toString(), chosenFeature.toString());
        appParameters.put(ParameterType.THEN_ACTION.toString(), chosenAction);
    }
    public WeatherFeature getChosenFeature(){
        return (WeatherFeature) featureSp.getSelectedItem();
    }
}
