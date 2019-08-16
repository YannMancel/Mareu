package com.mancel.yann.mareu.utils;

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
     * @return a {@link String} that contains the time in hh[SEPARATOR]mm
     */
    public static String convertHourAndMinuteToString(int hour, int minute) {
        final String result = ((hour < 10) ? "0" : "")   + hour +
                              TimeTools.SEPARATOR        +
                              ((minute < 10) ? "0" : "") + minute;

        return result;
    }
}
