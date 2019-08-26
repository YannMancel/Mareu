package com.mancel.yann.mareu.ui.activities;

import android.support.v7.widget.Toolbar;
import android.widget.TimePicker;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.base.BaseActivity;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragmentListener;
import com.mancel.yann.mareu.ui.fragments.CreatorOfMeetingFragment;
import com.mancel.yann.mareu.utils.TimeTools;

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

    private CreatorOfMeetingFragment mCreatorOfMeetingFragment;

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_creation;
    }

    @Override
    protected Toolbar getToolBar() {
        return null;
    }

    @Override
    protected void configureDesign() {
        this.configureAndShowMainFragment(R.id.activity_creation_frame_layout);
    }

    // INTERFACE OF FRAGMENT LISTENER **************************************************************

    @Override
    public void showMessageFromFragment(String message) {}

    @Override
    public void onClickFromFragment(String message) {
        // Good execution and closes the activity
        setResult(RESULT_OK);
        finish();
    }

    // INTERFACE OF ON TIME PICKER FRAGMENT LISTENER ***********************************************

    @Override
    public void onTimeSet(int id, TimePicker view, int hourOfDay, int minute) {
        final String time;
        try {
            time = TimeTools.convertHourAndMinuteToString(hourOfDay, minute);
            this.mCreatorOfMeetingFragment.setTextById(id, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
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