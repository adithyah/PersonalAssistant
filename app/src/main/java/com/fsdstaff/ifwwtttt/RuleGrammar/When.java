package com.fsdstaff.ifwwtttt.RuleGrammar;

import android.app.AlarmManager;

import com.fsdstaff.ifwwtttt.CalendarHelper;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by adithyah on 12/13/15.
 */
public class When implements Serializable {
    private String hour;
    private String min;
    private String interval;
    public static final long TIME_FUZZY_LIMIT = 1000 * 60 * 5;//5 mins

    public static final String[] HOURS = {
            "00","01","02","03","04","05","06","07","08","09","10","11",
            "12","13","14","15","16","17","18","19","20","21","22","23"};
    public static final String[] MINS = {"00","15","30","45"};
    public static final String[] INTERVALS = {"DAY", "HOUR", "NONE"};

    public When setHour(String hour) {
        this.hour = hour;
        return this;
    }

    public When setMin(String min) {
        this.min = min;
        return this;
    }

    public When setInterval(String interval) {
        this.interval = interval;
        return this;
    }

    public String getTriggerTime(){
        return hour + ":" + min;
    }

    public long getTriggerInterval(){
        switch(interval){
            case "DAY": return AlarmManager.INTERVAL_DAY;
            case "HOUR": return AlarmManager.INTERVAL_HOUR;
            case "NONE" : return 0;
        }
        return 0;
    }

    public boolean check(){
        Calendar tCal = CalendarHelper.convertTimeToCal(getTriggerTime());
        Calendar pCal = Calendar.getInstance();
        return pCal.compareTo(tCal) < TIME_FUZZY_LIMIT;
    }
}
