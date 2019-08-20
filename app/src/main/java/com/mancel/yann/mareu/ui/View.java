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
         * @param meetings a {@link List} of {@link Meeting}
         * @param isFilter a boolean
         */
        void updateDataOfRecyclerView(List<Meeting> meetings, boolean isFilter);

        /**
         * Updates the hour in hh:mm format by id
         * @param id an integer that contains the id value
         * @param time a {@link String} that contains the hour in hh:mm format
         */
        void setTextViewById(int id, String time);
    }
}
