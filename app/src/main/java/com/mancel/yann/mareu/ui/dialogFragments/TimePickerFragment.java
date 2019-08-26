package com.mancel.yann.mareu.ui.dialogFragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.mancel.yann.mareu.ui.base.BaseDialogFragment;

import java.util.Calendar;

/**
 * Created by Yann MANCEL on 12/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogFragments
 *
 * A simple {@link BaseDialogFragment} subclass which implements {@link TimePickerDialog.OnTimeSetListener}.
 */
public class TimePickerFragment extends BaseDialogFragment implements TimePickerDialog.OnTimeSetListener {

    // FIELDS --------------------------------------------------------------------------------------

    private int mId;

    private static final String BUNDLE_KEY_ID = "BUNDLE_KEY_ID";

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public TimePickerFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        this.retrieveArgument();

        Calendar calendar = Calendar.getInstance();

        final int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getContext(),
                                    this,
                                    hourOfDay,
                                    minute,
                                    DateFormat.is24HourFormat(getContext()));
    }

    // INTERFACE OF ON TIME SET LISTENER ***********************************************************

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.mListener.onTimeSet(this.mId, view, hourOfDay, minute);
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link TimePickerFragment}
     * @param id an integer that contains the id
     * @return a {@link TimePickerFragment}
     */
    public static TimePickerFragment newInstance(int id) {
        TimePickerFragment fragment = new TimePickerFragment();

        // Bundle
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_ID, id);

        fragment.setArguments(bundle);

        return fragment;
    }

    // ARGUMENTS ***********************************************************************************

    /**
     * Retrieves the argument
     */
    private void retrieveArgument() {
        this.mId = getArguments().getInt(BUNDLE_KEY_ID, 0);
    }
}
