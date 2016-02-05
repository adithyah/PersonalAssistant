package com.fsdstaff.ifwwtttt.WeatherAPI;

import android.content.Context;
import android.content.Intent;

import com.fsdstaff.ifwwtttt.Constants;
import com.fsdstaff.ifwwtttt.HomeActivity;
import com.fsdstaff.ifwwtttt.RuleGrammar.IfThen;
import com.fsdstaff.ifwwtttt.ThenAction.Notification;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by adithyah on 1/10/16.
 */
public class TemperatureFeature extends WeatherFeature {
    public TemperatureFeature(String appName, IfThen.Type ifThenType) {
        super(appName);
        name = "Temperature";
        if(ifThenType == IfThen.Type.IF) {
            this.values = new ArrayList<>();
            for (int i = 30; i < 80; i++) {
                this.values.add("" + i);
            }
        }
        else{
            this.thenActions = new ArrayList<>();
            this.thenActions.add(Notification.name);
            this.bdEvents = new ArrayList<>();
            this.bdEvents.add(Constants.SystemEvents.AIRPLANE_MODE);
        }
    }

    @Override
    public boolean checkIf(Context context,
                           String ifValue,
                           int forecastDay,
                           WeatherResponse wResponse){
        float ifTemperature = Float.parseFloat(ifValue);
        float temperature = extractTemperature(forecastDay, wResponse);
        return (temperature > ifTemperature);
    }

    @Override
    public Intent getThenIntent(Context context,
                                String thenAction,
                                int forecastDay,
                                WeatherResponse wResponse) {
        float temperature = extractTemperature(forecastDay, wResponse);
        if(thenAction == Notification.name){
            Intent nIntent = new Intent(context, HomeActivity.class);
            Notification notif = new Notification(
                    appName,
                    name,
                    context);
            notif.createNotification(nIntent, ""+temperature);
        }
        return null;
    }

    public float extractTemperature(int forecastDay, WeatherResponse wResponse){
        Document wDoc = wResponse.getWeatherDoc();
        Element eForecast = (Element) wDoc.getElementsByTagName("forecast").item(0);
        Element eDay = (Element) eForecast.getElementsByTagName("time").item(forecastDay);
        Element eTemperature = (Element) eDay.getElementsByTagName("temperature").item(0);
        return Float.parseFloat(eTemperature.getAttribute("day"));
    }
}
