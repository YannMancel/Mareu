package com.mancel.yann.mareu.ui.dialogFragments;

/**
 * Created by Yann MANCEL on 14/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogFragments
 */
public interface FilterFragmentListener {

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Start the room filter from the activity
     * @param roomFilter a {@link String} that contains the room name of filter
     */
    void selectRoomForFilter(String roomFilter);

    /**
     * Start the hour filter from the activity
     * @param minDate a {@link String} that contains the minimal hour
     * @param maxDate a {@link String} that contains the maximal hour
     */
    void selectHoursForFilter(String minDate, String maxDate);
}
