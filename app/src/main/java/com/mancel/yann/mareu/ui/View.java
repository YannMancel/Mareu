package com.mancel.yann.mareu.ui;

/**
 * Created by Yann MANCEL on 12/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 */
public interface View {

    // INTERFACES ----------------------------------------------------------------------------------

    interface FragmentView {

        /**
         * Updates the {@link android.support.v7.widget.RecyclerView}
         * @param isFilter a boolean
         */
        void updateRecyclerView(boolean isFilter);

        /**
         * Updates the hour in hh:mm format by id
         * @param id an integer that contains the id value
         * @param time a {@link String} that contains the hour in hh:mm format
         */
        void setTextById(int id, String time);
    }
}
