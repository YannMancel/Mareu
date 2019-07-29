package com.mancel.yann.mareu.service;

import com.mancel.yann.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.service
 */
public abstract class DummyMeetingGenerator {

    // FIELDS --------------------------------------------------------------------------------------

    private static List<Meeting> dummyMeetings = Arrays.asList(
            new Meeting(1, "Réunion A", "14h00", "Peach", "maxime@lamzone.com"),
            new Meeting(2, "Réunion B", "16h00", "Mario", "paul@lamzone.com"),
            new Meeting(3, "Réunion C", "19h00", "Luigi", "amandine@lamzone.com")
    );

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Generates the dummy meetings
     * @return a {@link List} of {@link Meeting}
     */
    public static List<Meeting> generatorOfDummyMeetings() {
        return new ArrayList<>(dummyMeetings);
    }
}
