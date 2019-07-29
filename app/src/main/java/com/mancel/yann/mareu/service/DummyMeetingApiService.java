package com.mancel.yann.mareu.service;

import com.mancel.yann.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.service
 *
 * A class which implements {@link MeetingApiService}.
 */
public class DummyMeetingApiService implements MeetingApiService {

    // FIELDS --------------------------------------------------------------------------------------

    private List<Meeting> mMeetings = DummyMeetingGenerator.generatorOfDummyMeetings();

    // METHODS -------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return this.mMeetings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        this.mMeetings.remove(meeting);
    }
}
