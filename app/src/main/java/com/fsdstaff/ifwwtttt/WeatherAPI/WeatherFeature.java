package com.fsdstaff.ifwwtttt.WeatherAPI;

import android.content.Context;
import android.content.Intent;

import com.fsdstaff.ifwwtttt.Feature;

/**
 * Created by adithyah on 1/17/16.
 */
public abstract class WeatherFeature extends Feature {
    public WeatherFeature(String appName) {
        super(appName);
    }

    public abstract boolean checkIf(Context context, String ifValue, int forecastDay,
                                    WeatherResponse wResponse);
    public abstract Intent getThenIntent(Context context, String thenAction, int forecastDay,
                                         WeatherResponse wResponse);
}
