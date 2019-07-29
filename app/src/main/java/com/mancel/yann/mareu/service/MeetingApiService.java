package com.mancel.yann.mareu.service;

import com.mancel.yann.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.service
 */
public interface MeetingApiService {

    /**
     * Returns a {@link List} of {@link Meeting}
     * @return a {@link List} of {@link Meeting}
     */
    List<Meeting> getMeetings();

    /**
     * Deletes the {@link Meeting} in argument
     * @param meeting a {@link Meeting}
     */
    void deleteMeeting(Meeting meeting);
}
