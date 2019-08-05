package com.mancel.yann.mareu.presenter;

import com.mancel.yann.mareu.model.Meeting;

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

    public interface FragmentPresenter {
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
         * Deletes the binding between the View and the Presenter
         */
        void onDetach();
    }
}
