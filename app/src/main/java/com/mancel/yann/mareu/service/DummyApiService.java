package com.mancel.yann.mareu.service;

import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;

import java.util.List;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.service
 *
 * A class which implements {@link ApiService}.
 */
public class DummyApiService implements ApiService {

    // FIELDS --------------------------------------------------------------------------------------

    private List<Meeting> mMeetings = DummyGenerator.generatorOfDummyMeetings();
    private List<Room> mRooms = DummyGenerator.generatorOfDummyRooms();
    private List<Member> mMembers = DummyGenerator.generatorOfDummyMembers();

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMeeting(Meeting meeting) {
        this.mMeetings.add(meeting);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Room> getRooms() {
        return this.mRooms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Member> getMembers() {
        return this.mMembers;
    }
}
