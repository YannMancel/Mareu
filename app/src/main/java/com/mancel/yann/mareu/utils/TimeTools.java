package com.mancel.yann.mareu.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yann MANCEL on 12/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.utils
 */
public abstract class TimeTools {

    // FIELDS --------------------------------------------------------------------------------------

    public static final String SEPARATOR = ":";
    public static final String PATTERN_FORMAT = "hh" + TimeTools.SEPARATOR + "mm";

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Returns a {@link String} that contains the time in specific format
     * @param hour an integer that contains the hour
     * @param minute an integer that contains the minute
     * @return a {@link String} that contains the time in specific format
     */
    public static String convertHourAndMinuteToString(int hour, int minute) {
        final String PATTERN_US = "hh" + TimeTools.SEPARATOR + "mm aa";

        DateFormat dateFormatUS = new SimpleDateFormat(PATTERN_US, Locale.US);
        DateFormat dateFormatFR = new SimpleDateFormat(TimeTools.PATTERN_FORMAT, Locale.US);

        // Convert 12 hours format to 24 hours format
        final String addAA = (hour > 11) ? " pm" : "";

        final String time =    hour + TimeTools.SEPARATOR + minute + addAA;

        DateFormat dateFormat = (hour > 11) ? new SimpleDateFormat(PATTERN_US, Locale.US) :
                                              new SimpleDateFormat(TimeTools.PATTERN_FORMAT, Locale.US);

        Date date = null;

        try {
            date = dateFormat.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        return (hour > 11) ? new SimpleDateFormat(PATTERN_FR, Locale.US).format(date) :
//                             dateFormat.format(date);

        return dateFormatFR.format(date);
    }
}
