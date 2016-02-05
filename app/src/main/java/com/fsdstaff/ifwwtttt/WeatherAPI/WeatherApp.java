package com.fsdstaff.ifwwtttt.WeatherAPI;

import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;

import com.fsdstaff.ifwwtttt.App;
import com.fsdstaff.ifwwtttt.CreateRuleSpinner;
import com.fsdstaff.ifwwtttt.Rule;
import com.fsdstaff.ifwwtttt.RuleGrammar.IfThen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adithyah on 1/10/16.
 */
public class WeatherApp extends App {
    public final static String API_KEY = "&appid=b44be438bcb9635db7770a7bd81ebfcd";
    public final static String API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily";
    public final static String API_URL_TRAILER = "&mode=xml&units=imperial";
    public final static int FORECAST_DAY_MAX = 16;
    private WeatherResponse weatherResponse;
    private App appSingleton;
    private HashMap<String, WeatherFeature> ifFeature;
    private HashMap<String, WeatherFeature> thenFeature;
    WLIfComponents ifComponents;
    WLThenComponents thenComponents;


    public WeatherApp(){
        name = "Weather";
        ifFeature = new HashMap<>();
        thenFeature = new HashMap<>();
        WeatherFeature ifTemp = new TemperatureFeature(name, IfThen.Type.IF);
        WeatherFeature thenTemp = new TemperatureFeature(name, IfThen.Type.THEN);
        ifFeature.put(ifTemp.getName(), ifTemp);
        thenFeature.put(thenTemp.getName(), thenTemp);
    }

    @Override
    public void addLayoutComponents(IfThen.Type ifThenType, LinearLayout pLayout) {
        if(ifThenType == IfThen.Type.IF){
            ifComponents = new WLIfComponents(pLayout.getContext(), (List) ifFeature.values());
            ifComponents.addLayoutComponents(pLayout);
        }
        else{
            thenComponents = new WLThenComponents(pLayout.getContext(), (List) thenFeature.values());
            thenComponents.addLayoutComponents(pLayout);
        }
    }

    @Override
    public void scanLayoutComponents(IfThen.Type ifThenType, LinearLayout pLayout, Rule rule){
        if(ifThenType == IfThen.Type.IF){
            IfThen ifCond = rule.getIfCond();
            ifComponents.scanLayoutComponents(pLayout, ifCond.getAppParameters());
            ifCond.getBdEventList().addAll(ifComponents.getChosenFeature().getbdEvents());
        }
        else{
            IfThen then = rule.getThen();
            thenComponents.scanLayoutComponents(pLayout, then.getAppParameters());
        }
    }
    @Override
    public Intent getThenIntent(Context context, IfThen then, Intent bdIntent) {
        HashMap<String, String> appParameters = then.getAppParameters();
        String location = appParameters.get(ParameterType.LOCATION);
        int forecastDay = Integer.parseInt(appParameters.get(ParameterType.FORECAST_DAY));
        String fName = appParameters.get(ParameterType.THEN_FEATURE);
        String fAction  = appParameters.get(ParameterType.THEN_ACTION);

        WeatherFeature feature = thenFeature.get(fName);
        if(weatherResponse == null){
            String urlString = buildUrl(location);
            String responseString = downloadUrl(urlString);
            weatherResponse = new WeatherResponse(responseString);
        }
        return feature.getThenIntent(context, fAction, forecastDay, weatherResponse);
    }

    @Override
    public boolean checkIf(Context context, IfThen ifCond, Intent bdIntent) {
        HashMap<String, String> appParameters = ifCond.getAppParameters();
        String location = appParameters.get(ParameterType.LOCATION);
        int forecastDay = Integer.parseInt(appParameters.get(ParameterType.FORECAST_DAY));
        String fName = appParameters.get(ParameterType.IF_FEATURE);
        String fVal  = appParameters.get(ParameterType.IF_VAL);

        WeatherFeature feature = ifFeature.get(fName);
        if(weatherResponse == null){
            String urlString = buildUrl(location);
            String responseString = downloadUrl(urlString);
            weatherResponse = new WeatherResponse(responseString);
        }
        return feature.checkIf(context, fVal, forecastDay, weatherResponse);
    }

    @Override
    public App getInstance() {
        if(appSingleton == null){
            appSingleton = new WeatherApp();
        }
        return appSingleton;
    }

    public String buildUrl(String location){
        return API_URL + "?q=" + location + API_URL_TRAILER + API_KEY;
    }

    public String downloadUrl(String urlStr){
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
