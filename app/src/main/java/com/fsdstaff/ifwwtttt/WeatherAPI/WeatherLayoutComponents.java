package com.fsdstaff.ifwwtttt.WeatherAPI;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fsdstaff.ifwwtttt.CreateRuleSpinner;
import com.fsdstaff.ifwwtttt.Rule;
import com.fsdstaff.ifwwtttt.RuleGrammar.IfThen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adithyah on 2/2/16.
 */
public class WeatherLayoutComponents {
    EditText locationTv;
    CreateRuleSpinner forecastSp;
    LinearLayout featureLayout;

    public WeatherLayoutComponents(Context context){
        locationTv = new EditText(context);
        forecastSp = new CreateRuleSpinner(context);
        featureLayout = new LinearLayout(context);

        locationTv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        locationTv.setHeight(100);
        locationTv.setHint("Type Location");

        List<Object> forecastSpData = new ArrayList<>();
        for (int i = 0; i < WeatherApp.FORECAST_DAY_MAX; i++) {
            forecastSpData.add("" + i);
        }
        forecastSp.setName("Choose Forecast Day")
                .setItems(forecastSpData);
        featureLayout.setOrientation(LinearLayout.VERTICAL);
    }

    public void addLayoutComponents(LinearLayout pLayout){
        pLayout.addView(locationTv);
        pLayout.addView(forecastSp);
        pLayout.addView(featureLayout);
    }

    public void scanLayoutComponents(LinearLayout pLayout, HashMap<String, String> appParameters){
        appParameters.put(ParameterType.LOCATION.toString(),
                locationTv.getText().toString());
        appParameters.put(ParameterType.FORECAST_DAY.toString(),
                (String) forecastSp.getSelectedItem());
    }
}
