package com.fsdstaff.ifwwtttt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by adithyah on 12/12/15.
 */
public class MyCalendar {
    public static Calendar convertTimeToCal(String targetTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Calendar cal = Calendar.getInstance();
        int currDay = cal.get(Calendar.DAY_OF_MONTH);
        int currMonth = cal.get(Calendar.MONTH);
        int currYear = cal.get(Calendar.YEAR);

        try {
            cal.setTime(sdf.parse(targetTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.set(currYear, currMonth, currDay);
        return cal;
    }
}
