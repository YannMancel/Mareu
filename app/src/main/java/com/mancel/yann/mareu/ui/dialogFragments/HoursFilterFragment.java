package com.mancel.yann.mareu.ui.dialogFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseDialogFragment;

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

        // Creates Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Modifies the title
        builder.setView(view)
               .setTitle(getString(R.string.filter_hour))
               .setPositiveButton(getString(R.string.yes),
                        (dialog, which) -> {this.mCallback.selectHoursForFilter("08:00","9:30");})
               .setNegativeButton(getString(R.string.no),
                        (dialog, which) -> {});

        return builder.create();
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link HoursFilterFragment}
     * @return a {@link HoursFilterFragment}
     */
    public static HoursFilterFragment newInstance() {
        return new HoursFilterFragment();
    }
}
