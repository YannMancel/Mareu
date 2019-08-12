package com.mancel.yann.mareu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yann MANCEL on 12/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.utils
 */
public abstract class TimeTools {

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Returns a {@link String} that contains the time in specific format
     * @param hour an integer that contains the hour
     * @param minute an integer that contains the minute
     * @param separator a {@link String} that contains the separator
     * @return a {@link String} that contains the time in specific format
     */
    public static String convertHourAndMinuteToString(int hour, int minute, String separator) {
        final String PATTERN = "hh" + separator + "mm";
        final String time =    hour + separator + minute;

        DateFormat dateFormat = new SimpleDateFormat(PATTERN);
        Date date = null;

        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateFormat.format(date);
    }
}
