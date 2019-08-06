package com.mancel.yann.mareu.service;

import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.service
 */
public abstract class DummyGenerator {

    // FIELDS --------------------------------------------------------------------------------------

    private static List<Meeting> dummyMeetings = Arrays.asList(
            new Meeting(1, "Réunion A", "14h00", "Peach", "maxime@lamzone.com"),
            new Meeting(2, "Réunion B", "16h00", "Mario", "paul@lamzone.com"),
            new Meeting(3, "Réunion C", "19h00", "Luigi", "amandine@lamzone.com")
    );

    private static List<Room> dummyRooms = Arrays.asList(
            new Room(1, "Peach"),
            new Room(2, "Mario"),
            new Room(3, "Luigi")
    );

    private static List<Member> dummyMembers = Arrays.asList(
            new Member(1, "maxime", "Dupond", "maxime@lamzone.com"),
            new Member(2, "paul", "Patru", "paul@lamzone.com"),
            new Member(3, "amandine", "Delorme", "amandine@lamzone.com")
    );

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Generates the dummy meetings
     * @return a {@link List} of {@link Meeting}
     */
    public static List<Meeting> generatorOfDummyMeetings() {
        return new ArrayList<>(dummyMeetings);
    }

    /**
     * Generates the dummy rooms
     * @return a {@link List} of {@link Room}
     */
    public static List<Room> generatorOfDummyRooms() {
        return new ArrayList<>(dummyRooms);
    }

    /**
     * Generates the dummy members
     * @return a {@link List} of {@link Member}
     */
    public static List<Member> generatorOfDummyMembers() {
        return new ArrayList<>(dummyMembers);
    }
}
