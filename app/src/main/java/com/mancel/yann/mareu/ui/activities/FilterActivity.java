package com.mancel.yann.mareu.ui.activities;

import android.support.v7.widget.Toolbar;
import android.widget.TimePicker;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.base.BaseActivity;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragmentListener;
import com.mancel.yann.mareu.ui.fragments.HoursFilterFragment;
import com.mancel.yann.mareu.ui.fragments.RoomFilterFragment;
import com.mancel.yann.mareu.utils.TimeTools;

import butterknife.BindView;

/**
 * Created by Yann MANCEL on 16/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.activities
 *
 * A {@link BaseActivity} subclass which implements
 * {@link BaseFragment.FragmentListener} and {@link TimePickerFragmentListener}.
 */
public class FilterActivity extends BaseActivity implements BaseFragment.FragmentListener,
                                                            TimePickerFragmentListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private int mFilterType;
    private HoursFilterFragment mHoursFilterFragment;
    private RoomFilterFragment mRoomFilterFragment;

    public static final int HOUR_FILTER = 1;
    public static final int ROOM_FILTER = 2;

    public static final String BUNDLE_EXTRA_FILTER_TYPE = "BUNDLE_EXTRA_FILTER_TYPE";

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_filter;
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

        // Retrieves the filter type sent by another
        this.retrieveValueFromIntent();

        // Selects the good filter
        this.selectFragmentToDisplay(this.mFilterType);
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
            this.mHoursFilterFragment.setTextById(id, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // INTENT **************************************************************************************

    /**
     * Retrieves the filter type sent by another {@link android.app.Activity}
     */
    private void retrieveValueFromIntent() {
        this.mFilterType = getIntent().getIntExtra(BUNDLE_EXTRA_FILTER_TYPE, 0);
    }

    // FRAGMENTS ***********************************************************************************

    /**
     * Selects the good filter thanks to the value in argument
     * @param choice an integer that contains the choice
     */
    private void selectFragmentToDisplay(final int choice) {
        switch (choice) {
            case HOUR_FILTER: {
                this.configureAndShowHoursFilterFragment(R.id.activity_filter_main_frame_layout);
                break;
            }
            case ROOM_FILTER: {
                this.configureAndShowRoomFilterFragment(R.id.activity_filter_main_frame_layout);
                break;
            }
        }
    }

    /**
     * Configures and shows the hours filter fragment (see {@link HoursFilterFragment}
     * @param idOfFrameLayout an integer that contains the id value
     */
    private void configureAndShowHoursFilterFragment(final int idOfFrameLayout) {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mHoursFilterFragment = (HoursFilterFragment) getSupportFragmentManager().findFragmentById(idOfFrameLayout);

        // If the fragment is not displayed
        if (this.mHoursFilterFragment == null) {
            // Creates the main fragment
            this.mHoursFilterFragment = HoursFilterFragment.newInstance();

            this.addFragment(idOfFrameLayout, this.mHoursFilterFragment);
        }
    }

    /**
     * Configures and shows the room filter fragment (see {@link RoomFilterFragment}
     * @param idOfFrameLayout an integer that contains the id value
     */
    private void configureAndShowRoomFilterFragment(final int idOfFrameLayout) {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mRoomFilterFragment = (RoomFilterFragment) getSupportFragmentManager().findFragmentById(idOfFrameLayout);

        // If the fragment is not displayed
        if (this.mRoomFilterFragment == null) {
            // Creates the main fragment
            this.mRoomFilterFragment = RoomFilterFragment.newInstance();

            this.addFragment(idOfFrameLayout, this.mRoomFilterFragment);
        }
    }
}