package com.mancel.yann.mareu;

import com.mancel.yann.mareu.di.DI;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.service.DummyMeetingGenerator;
import com.mancel.yann.mareu.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu
 *
 * Unit test on {@link MeetingApiService}
 */
@RunWith(JUnit4.class)
public class MeetingApiServiceTest {

    // FIELDS --------------------------------------------------------------------------------------

    private MeetingApiService mService;

    // METHODS -------------------------------------------------------------------------------------

    @Before
    public void setUp() throws Exception {
        this.mService = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> actualMeetings = this.mService.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.generatorOfDummyMeetings();

        assertThat(actualMeetings, containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meeting = this.mService.getMeetings().get(0);
        this.mService.deleteMeeting(meeting);

        assertFalse(this.mService.getMeetings().contains(meeting));
    }
}
