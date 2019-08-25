package com.mancel.yann.mareu.ui.fragments;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 16/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass.
 */
public class HoursFilterFragment extends BaseFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_filter_hours_b_minimal_hour)
    Button mMinHourButton;
    @BindView(R.id.fragment_filter_hours_b_maximal_hour)
    Button mMaxHourButton;
    @BindView(R.id.fragment_filter_hours_b_filter)
    Button mFilterButton;

    public final int ID_MINIMAL_HOUR = 1;
    public final int ID_MAXIMAL_HOUR = 2;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public HoursFilterFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_filter_hours;
    }

    @Override
    protected void configureDesign() {}

    // INTERFACE FRAGMENT VIEW *********************************************************************

    @Override
    public void setTextById(int id, String time) {
        switch (id) {
            // TIME PICKER: MINIMAL HOURS
            case ID_MINIMAL_HOUR: {
                this.mMinHourButton.setText(time);
                break;
            }
            // TIME PICKER: MAXIMAL HOURS
            case ID_MAXIMAL_HOUR: {
                this.mMaxHourButton.setText(time);
                break;
            }
        }
    }

    // ACTIONS *************************************************************************************

    @OnClick({R.id.fragment_filter_hours_b_minimal_hour,
              R.id.fragment_filter_hours_b_maximal_hour,
              R.id.fragment_filter_hours_b_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // SEARCH BUTTON: MINIMAL HOURS
            case R.id.fragment_filter_hours_b_minimal_hour: {
                TimePickerFragment.newInstance(ID_MINIMAL_HOUR)
                                  .show(getActivity().getSupportFragmentManager(), "TIME PICKER");
                break;
            }
            // SEARCH BUTTON: MAXIMAL HOURS
            case R.id.fragment_filter_hours_b_maximal_hour: {
                TimePickerFragment.newInstance(ID_MAXIMAL_HOUR)
                                  .show(getActivity().getSupportFragmentManager(), "TIME PICKER");
                break;
            }
            // FILTER BUTTON
            case R.id.fragment_filter_hours_b_filter: {
                this.configureAndShowAlertDialog();
                break;
            }
        }
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link HoursFilterFragment}
     * @return a {@link HoursFilterFragment}
     */
    public static HoursFilterFragment newInstance() {
        return new HoursFilterFragment();
    }

    // ALERT DIALOG ********************************************************************************

    /**
     * Configures and show the {@link AlertDialog}
     */
    private void configureAndShowAlertDialog() {
        // Creates Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Modifies Alert Dialog
        builder.setTitle(getString(R.string.creation_of_hours_filter))
               .setMessage(getString(R.string.question_for_creation_of_hours_filter,
                                     this.mMinHourButton.getText().toString(),
                                     this.mMaxHourButton.getText().toString()))
               .setPositiveButton(getString(R.string.yes),
                        (dialog, which) -> {
                            try {
                                this.mFragmentPresenter.filterPerHours(this.mMinHourButton.getText().toString(),
                                                                       this.mMaxHourButton.getText().toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            this.mCallback.onClickFromFragment(null);})
               .setNegativeButton(getString(R.string.no),
                        (dialog, which) -> {});

        // Creates and shows the AlertDialog widget
        builder.create().show();
    }
}