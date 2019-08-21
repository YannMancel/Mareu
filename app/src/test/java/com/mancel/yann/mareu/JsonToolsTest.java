package com.mancel.yann.mareu;

import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.utils.JsonTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yann MANCEL on 21/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu
 *
 * Unit test on {@link JsonTools}
 */
@RunWith(JUnit4.class)
public class JsonToolsTest {

    // FIELDS --------------------------------------------------------------------------------------

    private final Meeting mMeeting = new Meeting(1, "Meeting A", "14:00", "Peach", "maxime@lamzone.com");

    private final String mJsonFormat = "{"                                  +
                                       "\"identifier\":1,"                  +
                                       "\"topic\":\"Meeting A\","           +
                                       "\"hour\":\"14:00\","                +
                                       "\"room\":\"Peach\","                +
                                       "\"member\":\"maxime@lamzone.com\""  +
                                       "}";

    // METHODS -------------------------------------------------------------------------------------

    @Test
    public void JsonTools_convertObjectToJson() {
        final String actualJson = JsonTools.convertJavaToJson(this.mMeeting);

        assertEquals("Object to Json", this.mJsonFormat, actualJson);
    }

    @Test
    public void JsonTools_convertListToJson() {
        final String expectedJson = "[" + this.mJsonFormat + "]";

        final List<Meeting> list = Collections.singletonList(this.mMeeting);
        final String actualJson = JsonTools.convertJavaToJson(list);

        assertEquals("List to Json", expectedJson, actualJson);
    }

    @Test
    public void JsonTools_convertObjectToJsonToObject() {
        String json = JsonTools.convertJavaToJson(this.mMeeting);
        Meeting actualMeeting = JsonTools.convertJsonToJava(json, Meeting.class);

        assertEquals("Object to Json to Object", this.mMeeting, actualMeeting);
    }

    @Test
    public void JsonTools_convertListToJsonToList() {
        List<Meeting> expectedList = Collections.singletonList(this.mMeeting);

        String json = JsonTools.convertJavaToJson(expectedList);
        List<Meeting> actualList = JsonTools.convertJsonToJavaList(json, Meeting.class);

        assertEquals("List to Json to List", expectedList, actualList);
    }
}
