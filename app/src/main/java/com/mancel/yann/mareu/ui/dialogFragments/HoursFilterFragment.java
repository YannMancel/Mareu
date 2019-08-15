package com.mancel.yann.mareu.ui.dialogFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.NumberPicker;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseDialogFragment;
import com.mancel.yann.mareu.utils.TimeTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yann MANCEL on 14/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogFragments
 *
 * A simple {@link BaseDialogFragment} subclass
 */
public class HoursFilterFragment extends BaseDialogFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.dialog_filter_date_np_min_hour)
    NumberPicker mMinHour;
    @BindView(R.id.dialog_filter_date_np_min_minute)
    NumberPicker mMinMinute;
    @BindView(R.id.dialog_filter_date_np_max_hour)
    NumberPicker mMaxHour;
    @BindView(R.id.dialog_filter_date_np_max_minute)
    NumberPicker mMaxMinute;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public HoursFilterFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Creates the View
        View view = getActivity().getLayoutInflater()
                                 .inflate(R.layout.dialog_filter_date, null);

        // Using the ButterKnife library
        ButterKnife.bind(this, view);

        // Configures the design
        this.configureDesign();

        // Creates Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Modifies the title
        builder.setView(view)
               .setTitle(getString(R.string.filter_hour))
               .setPositiveButton(getString(R.string.yes),
                        (dialog, which) -> {
                            this.mCallback.selectHoursForFilter(TimeTools.convertHourAndMinuteToString(this.mMinHour.getValue(),
                                                                                                       this.mMinMinute.getValue()),
                                                                TimeTools.convertHourAndMinuteToString(this.mMaxHour.getValue(),
                                                                                                       this.mMaxMinute.getValue()));
                        })
               .setNegativeButton(getString(R.string.no),
                        (dialog, which) -> {});

        return builder.create();
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link HoursFilterFragment}
     *
     * @return a {@link HoursFilterFragment}
     */
    public static HoursFilterFragment newInstance() {
        return new HoursFilterFragment();
    }

    // NUMBER PICKERS ******************************************************************************

    /**
     * Configures the {@link NumberPicker}
     */
    private void configureNumberPicker(NumberPicker numberPicker, int minValue,  int maxValue, int currentValue) {
        numberPicker.setMinValue(minValue);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setValue(currentValue);
    }

    // UI ******************************************************************************************

    /**
     * Configures the design
     */
    private void configureDesign() {
        // HOUR
        configureNumberPicker(this.mMinHour, 0, 23, 8);
        configureNumberPicker(this.mMaxHour, 0, 23, 20);

        // MINUTE
        configureNumberPicker(this.mMinMinute, 0, 59, 0);
        configureNumberPicker(this.mMaxMinute, 0, 59, 30);
    }
}
