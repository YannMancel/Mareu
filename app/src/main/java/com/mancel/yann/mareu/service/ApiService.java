package com.mancel.yann.mareu.service;

import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;

import java.util.List;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.service
 */
public interface ApiService {

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

    /**
     * Adds the {@link Meeting} in argument
     * @param meeting a {@link Meeting}
     */
    void addMeeting(Meeting meeting);

    /**
     * Returns a {@link List} of {@link Room}
     * @return a {@link List} of {@link Room}
     */
    List<Room> getRooms();

    /**
     * Returns a {@link List} of {@link Member}
     * @return a {@link List} of {@link Member}
     */
    List<Member> getMembers();
}
