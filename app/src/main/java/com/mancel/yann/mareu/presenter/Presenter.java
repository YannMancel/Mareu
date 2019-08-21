package com.mancel.yann.mareu.presenter;

import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;

import java.util.List;

/**
 * Created by Yann MANCEL on 05/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.presenter
 */
public interface Presenter {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface FragmentPresenterInterface {

        // MEETINGS ********************************************************************************

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
         * Adds a {@link Meeting} in argument
         * @param meetingFromString a {@link String}
         */
        void addMeeting(String meetingFromString);

        /**
         * Creates a new {@link Meeting} and convert to {@link String}
         * @param topic a{@link String} that contains the topic
         * @param hour a{@link String} that contains the hour
         * @param room a{@link String} that contains the room
         * @param member a{@link String} that contains the members
         * @return a {@link String} that contains the new {@link Meeting}
         */
        String createNewMeetingToString(String topic, String hour, String room, String member);

        // ROOMS ***********************************************************************************

        /**
         * Returns a {@link List} of {@link String}
         * @return a {@link List} of {@link String}
         */
        List<String> getRoomsName();

        // MEMBERS *********************************************************************************

        /**
         * Returns a {@link List} of {@link Member}
         * @return a {@link List} of {@link Member}
         */
        List<Member> getMembers();

        /**
         * Adds or deletes the {@link Member} according to the boolean in argument
         * @param member a {@link Member} to analyse
         * @return a boolean [True: Add] en [False: Delete]
         */
        boolean AddOrDeleteMember(Member member);

        /**
         * Returns a {@link String} that contains all the selected {@link Member}
         * @return a {@link String} that contains all the selected {@link Member}
         */
        String getSelectedMembers();

        // MEMORY LEAKS ****************************************************************************

        /**
         * Deletes the binding between the View and the Presenter
         */
        void onDetach();

        // FILTERS *********************************************************************************

        /**
         * Returns a {@link List} of {@link Meeting} which is filtered by {@link Room}
         * @param roomName a {@link String} that contains the name of the {@link Room}
         * @return a {@link List} of {@link Meeting}
         */
        List<Meeting> filterPerRoom(String roomName);

        /**
         * Returns a {@link List} of {@link Meeting} which is filtered by hours
         * @param minDate a {@link String} that contains the minimal hour
         * @param maxDate a {@link String} that contains the maximal hour
         * @return a {@link List} of {@link Meeting}
         * @exception Exception throws when the dates are not correct
         */
        List<Meeting> filterPerHours(String minDate, String maxDate) throws Exception;
    }
}
