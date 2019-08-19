package com.mancel.yann.mareu.ui.activities;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.TimePicker;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.base.BaseActivity;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragmentListener;
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
 * {@link BaseFragment.FragmentListener} and {@link TimePickerFragmentListener}.
 */
public class CreationActivity extends BaseActivity implements BaseFragment.FragmentListener,
                                                              TimePickerFragmentListener {

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
        return R.layout.activity_creation;
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
        this.configureAndShowMainFragment(R.id.activity_creation_frame_layout);
    }

    // INTERFACE OF FRAGMENT LISTENER **************************************************************

    @Override
    public void showMessageFromFragment(String message) {
        ShowMessage.showMessageWithSnackbar(this.mCoordinatorLayout, message);
    }

    @Override
    public void onClickFromFragment(String message) {
        // Creates Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Modifies the title
        builder.setTitle(getString(R.string.creation_of_meeting))
               .setMessage(getString(R.string.question_for_creation_of_meeting))
               .setPositiveButton(getString(R.string.yes),
                                  (dialog, which) -> {Intent intent = new Intent();
                                                      intent.putExtra(BUNDLE_EXTRA_MEETING, message);

                                                      // Good execution and closes the activity
                                                      setResult(RESULT_OK, intent);
                                                      finish();})
               .setNegativeButton(getString(R.string.no),
                                  (dialog, which) -> {finish();});

        // Creates and shows the AlertDialog widget
        builder.create().show();
    }

    @Override
    public void onClickFromFragment(String messageA, String messageB) {}

    // INTERFACE OF ON TIME PICKER FRAGMENT LISTENER ***********************************************

    @Override
    public void onTimeSet(int id, TimePicker view, int hourOfDay, int minute) {
        final String time = TimeTools.convertHourAndMinuteToString(hourOfDay, minute);
        this.mCreatorOfMeetingFragment.setTextViewById(id, time);
    }

    // FRAGMENTS ***********************************************************************************

    /**
     * Configures and shows the main fragment (see {@link CreatorOfMeetingFragment}
     * @param idOfFrameLayout an integer that contains the id value
     */
    private void configureAndShowMainFragment(final int idOfFrameLayout) {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mCreatorOfMeetingFragment = (CreatorOfMeetingFragment) getSupportFragmentManager().findFragmentById(idOfFrameLayout);

        // If the fragment is not displayed
        if (this.mCreatorOfMeetingFragment == null) {
            // Creates the main fragment
            this.mCreatorOfMeetingFragment = CreatorOfMeetingFragment.newInstance();

            this.addFragment(idOfFrameLayout, this.mCreatorOfMeetingFragment);
        }
    }
}