package com.fsdstaff.ifwwtttt.WeatherAPI;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.fsdstaff.ifwwtttt.Constants;
import com.fsdstaff.ifwwtttt.Feature;

import java.util.ArrayList;

/**
 * Created by adithyah on 1/17/16.
 */
public class ChosenFeatureListener implements AdapterView.OnItemSelectedListener {
    LinearLayout pLayout;
    ParameterType pType;
    ArrayList<Object> valActionList;

    public ChosenFeatureListener(LinearLayout pLayout, ParameterType pType, ArrayList valActionList) {
        this.pLayout = pLayout;
        this.pType = pType;
        this.valActionList = valActionList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position > 0) {
            Feature chosenFeature = (Feature) parent.getItemAtPosition(position);
            Log.d(Constants.DEBUG, "chosen feature: " + chosenFeature);
            clearLayoutFromIndex(pLayout.indexOfChild(parent) + 1);
            if (pType == ParameterType.IF_FEATURE) {
                valActionList.addAll(chosenFeature.getValues());
            } else {
                valActionList.addAll(chosenFeature.getThenActions());
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void clearLayoutFromIndex(int start){
        int totalViews = pLayout.getChildCount();
        Log.d(Constants.DEBUG, "total views to be removed: " + (totalViews-start));
        if(start < totalViews) {
            pLayout.removeViews(start, totalViews - start);
        }
    }
}
