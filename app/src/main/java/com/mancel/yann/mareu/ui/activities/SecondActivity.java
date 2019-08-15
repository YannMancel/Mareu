package com.mancel.yann.mareu.ui.activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.TimePicker;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseActivity;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.ui.fragments.CreatorOfMeetingFragment;
import com.mancel.yann.mareu.utils.ShowMessage;
import com.mancel.yann.mareu.utils.TimeTools;

import butterknife.BindView;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.activities
 *
 * A {@link BaseActivity} subclass which implements
 * {@link BaseFragment.FragmentListener} and {@link TimePickerDialog.OnTimeSetListener}.
 */
public class SecondActivity extends BaseActivity implements BaseFragment.FragmentListener,
                                                            TimePickerDialog.OnTimeSetListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_second_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    private CreatorOfMeetingFragment mCreatorOfMeetingFragment;

    public static final String BUNDLE_EXTRA_MEETING = "BUNDLE_EXTRA_MEETING";

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected Toolbar getToolBar() {
        return this.mToolbar;
    }

    @Override
    protected void configureDesign() {
        // Configures the ToolBar
        this.configureToolBar();

        // Add Up button of ToolBar
        this.addUpButtonOfToolBar();

        // Configures and shows the main fragment
        this.configureAndShowMainFragment();
    }

    // INTERFACE OF FRAGMENT LISTENER **************************************************************

    @Override
    public void showMessageFromFragment(String message) {
        ShowMessage.showMessageWithSnackbar(this.mCoordinatorLayout, message);
    }

    @Override
    public void onClickFromFragment(String meetingFromString) {
        // Creates Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Modifies the title
        builder.setTitle(getString(R.string.creation_of_meeting))
               .setMessage(getString(R.string.question_for_creation_of_meeting))
               .setPositiveButton(getString(R.string.yes),
                                  (dialog, which) -> {Intent intent = new Intent();
                                                      intent.putExtra(BUNDLE_EXTRA_MEETING, meetingFromString);

                                                      // Good execution and closes the activity
                                                      setResult(RESULT_OK, intent);
                                                      finish();})
               .setNegativeButton(getString(R.string.no),
                                  (dialog, which) -> {finish();});

        // Creates and shows the AlertDialog widget
        builder.create().show();
    }

    // INTERFACE OF ON TIME SET LISTENER ***********************************************************

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        final String time = TimeTools.convertHourAndMinuteToString(hourOfDay, minute);
        this.mCreatorOfMeetingFragment.updateHourOfTextView(time);
    }

    // FRAGMENTS ***********************************************************************************

    /**
     * Configures and shows the main fragment (see {@link CreatorOfMeetingFragment}
     */
    private void configureAndShowMainFragment() {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mCreatorOfMeetingFragment = (CreatorOfMeetingFragment) getSupportFragmentManager().findFragmentById(R.id.activity_second_frame_layout);

        // If the fragment is not displayed
        if (this.mCreatorOfMeetingFragment == null) {
            // Creates the main fragment
            this.mCreatorOfMeetingFragment = CreatorOfMeetingFragment.newInstance();

            this.addFragment(R.id.activity_second_frame_layout, this.mCreatorOfMeetingFragment);
        }
    }
}
