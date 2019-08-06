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

    public interface ActivityPresenter {
    }

    public interface FragmentPresenterInterface {

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
         * Returns a {@link List} of {@link Room}
         * @return a {@link List} of {@link Room}
         */
        List<Room> getRooms();

        /**
         * Returns a {@link List} of {@link String}
         * @return a {@link List} of {@link String}
         */
        List<String> getRoomsName();

        /**
         * Returns a {@link List} of {@link Member}
         * @return a {@link List} of {@link Member}
         */
        List<Member> getMembers();

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
    }
}
