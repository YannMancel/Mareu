package com.mancel.yann.mareu.ui;

import com.mancel.yann.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Yann MANCEL on 12/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 */
public interface View {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface ActivityView {
    }

    public interface FragmentView {
        /**
         * Updates the data
         */
        void UpdateDataOfRecyclerView();

        /**
         * Configures and shows the Bottom Sheet
         * @param meetings a {@link List} of {@link Meeting}
         */
        void configureAndShowBottomSheet(List<Meeting> meetings);

        /**
         * Updates the hour in hh:mm format
         * @param time a {@link String} that contains the hour in hh:mm format
         */
        void updateHourOfTextView(String time);
    }
}
