package com.mancel.yann.mareu.ui.dialogFragments;

import android.widget.TimePicker;

/**
 * Created by Yann MANCEL on 16/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogFragments
 */
public interface TimePickerFragmentListener {

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Allows to retrieve the id of the {@link TimePicker}
     * @param id an integer that allows to identifier the {@link TimePicker}
     * @param view a {@link TimePicker}
     * @param hourOfDay an integer that contains the hour value
     * @param minute an integer that contains the minute value
     */
    void onTimeSet(int id, TimePicker view, int hourOfDay, int minute);
}
