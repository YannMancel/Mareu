package com.mancel.yann.mareu.ui.dialogFragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by Yann MANCEL on 12/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogFragments
 *
 * A simple {@link DialogFragment} subclass
 */
public class TimePickerFragment extends DialogFragment {

    // CONSTRUCTORS --------------------------------------------------------------------------------

    // METHODS -------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();

        final int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getContext(),
                                    (TimePickerDialog.OnTimeSetListener) getActivity(),
                                    hourOfDay,
                                    minute,
                                    DateFormat.is24HourFormat(getContext()));
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link TimePickerFragment}
     * @return a {@link TimePickerFragment}
     */
    public static TimePickerFragment newInstance() {
        return new TimePickerFragment();
    }
}
