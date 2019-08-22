package com.mancel.yann.mareu;

import com.mancel.yann.mareu.utils.TimeTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Yann MANCEL on 21/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu
 *
 * Unit test on {@link TimeTools}
 */
@RunWith(JUnit4.class)
public class TimeToolsTest {

    // METHODS -------------------------------------------------------------------------------------

    @Test
    public void timeTools_convertHourAndMinuteToString() throws Exception {
        assertEquals("00:00", TimeTools.convertHourAndMinuteToString(0,0));
        assertEquals("15:15", TimeTools.convertHourAndMinuteToString(15,15));
    }

    @Test(expected = Exception.class)
    public void timeTools_convertHourAndMinuteToString_hourLowerThan0() throws Exception {
        String hourLowerThan0 = TimeTools.convertHourAndMinuteToString(-1,0);
    }

    @Test(expected = Exception.class)
    public void timeTools_convertHourAndMinuteToString_hourGreaterThan24() throws Exception {
        String hourGreaterThan24 = TimeTools.convertHourAndMinuteToString(25,0);
    }

    @Test(expected = Exception.class)
    public void timeTools_convertHourAndMinuteToString_minuteLowerThan0() throws Exception {
        String minuteLowerThan0 = TimeTools.convertHourAndMinuteToString(0,-1);
    }

    @Test(expected = Exception.class)
    public void timeTools_convertHourAndMinuteToString_minuteGreaterThan60() throws Exception {
        String minuteGreaterThan60 = TimeTools.convertHourAndMinuteToString(0,61);
    }
}