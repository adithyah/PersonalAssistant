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
public class WLIfComponents extends WeatherLayoutComponents {
    CreateRuleSpinner featureSp;
    CreateRuleSpinner featureValSp;
    ArrayList<Object> featureValues;
    List<WeatherFeature> featureList;

    public WLIfComponents(Context context, List<WeatherFeature> featureList) {
        super(context);
        this.featureList = featureList;
        featureSp.setName("Choose Feature")
                .setItems(new ArrayList<Object>(featureList))
                .setOnItemSelectedListener(new ChosenFeatureListener(featureLayout,
                        ParameterType.IF_FEATURE, featureValues));
        featureValSp.setName("Choose Value")
                .setItems(featureValues);
    }
    public void addLayoutComponents(LinearLayout pLayout){
        super.addLayoutComponents(pLayout);
        featureLayout.addView(featureSp);
        featureLayout.addView(featureValSp);
    }
    public void scanLayoutComponents(LinearLayout pLayout, HashMap<String, String> appParameters){
        super.scanLayoutComponents(pLayout, appParameters);
        WeatherFeature chosenFeature = (WeatherFeature) featureSp.getSelectedItem();
        String chosenVal = (String) featureValSp.getSelectedItem();
        appParameters.put(ParameterType.IF_FEATURE.toString(), chosenFeature.toString());
        appParameters.put(ParameterType.IF_VAL.toString(), chosenVal);
    }
    public WeatherFeature getChosenFeature(){
        return (WeatherFeature) featureSp.getSelectedItem();
    }
}
