package com.mancel.yann.mareu;

import com.mancel.yann.mareu.di.DI;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;
import com.mancel.yann.mareu.service.DummyGenerator;
import com.mancel.yann.mareu.service.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu
 *
 * Unit test on {@link ApiService}
 */
@RunWith(JUnit4.class)
public class ApiServiceTest {

    // FIELDS --------------------------------------------------------------------------------------

    private ApiService mService;

    // METHODS -------------------------------------------------------------------------------------

    @Before
    public void setUp() throws Exception {
        this.mService = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> actualMeetings = this.mService.getMeetings();
        List<Meeting> expectedMeetings = DummyGenerator.generatorOfDummyMeetings();

        assertThat(actualMeetings, containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meeting = this.mService.getMeetings().get(0);
        this.mService.deleteMeeting(meeting);

        assertFalse(this.mService.getMeetings().contains(meeting));
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting meeting = new Meeting(5, "Meeting E", "09:30", "Mario", "yann@lamzone.com");

        assertFalse(this.mService.getMeetings().contains(meeting));

        this.mService.addMeeting(meeting);

        assertTrue(this.mService.getMeetings().contains(meeting));
    }

    @Test
    public void getRoomsWithSuccess() {
        List<Room> actualRooms = this.mService.getRooms();
        List<Room> expectedRooms = DummyGenerator.generatorOfDummyRooms();

        assertThat(actualRooms, containsInAnyOrder(expectedRooms.toArray()));
    }

    @Test
    public void getMembersWithSuccess() {
        List<Member> actualMembers = this.mService.getMembers();
        List<Member> expectedMembers = DummyGenerator.generatorOfDummyMembers();

        assertThat(actualMembers, containsInAnyOrder(expectedMembers.toArray()));
    }
}
