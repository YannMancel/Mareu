package com.mancel.yann.mareu.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.base.BaseActivity;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragmentListener;
import com.mancel.yann.mareu.ui.fragments.CreatorOfMeetingFragment;
import com.mancel.yann.mareu.ui.fragments.MeetingFragment;
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
public class MainActivity extends BaseActivity implements BaseFragment.FragmentListener,
                                                          TimePickerFragmentListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.activity_main_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private MeetingFragment mMeetingFragment;
    private CreatorOfMeetingFragment mCreatorOfMeetingFragment;

    public static final int REQUEST_CODE_SECOND_ACTIVITY = 100;
    public static final int REQUEST_CODE_FILTER_ACTIVITY = 200;

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Toolbar getToolBar() {
        return this.mToolbar;
    }

    @Override
    protected void configureDesign() {
        // Configures the ToolBar
        this.configureToolBar();

        // Configures and shows the main fragment
        this.configureAndShowMainFragment(R.id.activity_main_main_frame_layout);

        // Configures and shows the second fragment
        this.configureAndShowSecondFragment(R.id.activity_main_second_frame_layout);
    }

    // ACTIVITY ************************************************************************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Creates a MenuInflater to add the menu xml file to this activity
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Depending on the item Id
        switch (item.getItemId()) {
            case R.id.menu_activity_main_filter_date: {
                // Bundle
                Bundle args = new Bundle();
                args.putInt(FilterActivity.BUNDLE_EXTRA_FILTER_TYPE, FilterActivity.HOUR_FILTER);

                this.startAnotherActivityForResult(this, FilterActivity.class, args, REQUEST_CODE_FILTER_ACTIVITY);
                return true;
            }
            case R.id.menu_activity_main_filter_room: {
                // Bundle
                Bundle args = new Bundle();
                args.putInt(FilterActivity.BUNDLE_EXTRA_FILTER_TYPE, FilterActivity.ROOM_FILTER);

                this.startAnotherActivityForResult(this, FilterActivity.class, args, REQUEST_CODE_FILTER_ACTIVITY);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // SECOND ACTIVITY
        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY && resultCode == RESULT_OK) {
            final String meetingFromString = data.getStringExtra(CreationActivity.BUNDLE_EXTRA_MEETING);
            this.mMeetingFragment.addMeeting(meetingFromString);
        }

        // FILTER ACTIVITY
        if (requestCode == REQUEST_CODE_FILTER_ACTIVITY && resultCode == RESULT_OK) {

            final int filterType = data.getIntExtra(FilterActivity.BUNDLE_EXTRA_FILTER_TYPE, 0);

            switch (filterType) {
                case FilterActivity.HOUR_FILTER: {
                    final String minHour = data.getStringExtra(FilterActivity.BUNDLE_EXTRA_MINIMAL_HOUR);
                    final String maxHour = data.getStringExtra(FilterActivity.BUNDLE_EXTRA_MAXIMAL_HOUR);
                    this.mMeetingFragment.filterPerHours(minHour, maxHour);
                    break;
                }
                case FilterActivity.ROOM_FILTER: {
                    final String room = data.getStringExtra(FilterActivity.BUNDLE_EXTRA_ROOM);
                    this.mMeetingFragment.filterPerRoom(room);
                    break;
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Hides the FAB into Fragment if there are 2 fragments displayed
        this.mMeetingFragment.setVisibilityOfFAB(this.mCreatorOfMeetingFragment == null);
    }

    // INTERFACE OF FRAGMENT LISTENER **************************************************************

    @Override
    public void showMessageFromFragment(String message) {
        ShowMessage.showMessageWithSnackbar(this.mCoordinatorLayout, message);
    }

    @Override
    public void onClickFromFragment(String message) {
        // Only one fragment is displayed
        if (this.mCreatorOfMeetingFragment == null) {
            this.startAnotherActivityForResult(this, CreationActivity.class, REQUEST_CODE_SECOND_ACTIVITY);
        }
        else {
            // Creates Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Modifies the title
            builder.setTitle(getString(R.string.creation_of_meeting))
                    .setMessage(getString(R.string.question_for_creation_of_meeting))
                    .setPositiveButton(getString(R.string.yes),
                                       (dialog, which) -> {this.mMeetingFragment.addMeeting(message);})
                    .setNegativeButton(getString(R.string.no),
                                       (dialog, which) -> {});

            // Creates and shows the AlertDialog widget
            builder.create().show();
        }
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
     * Configures and shows the main fragment (see {@link MeetingFragment}
     * @param idOfFrameLayout an integer that contains the id value
     */
    private void configureAndShowMainFragment(final int idOfFrameLayout) {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mMeetingFragment = (MeetingFragment) getSupportFragmentManager().findFragmentById(idOfFrameLayout);

        // If the fragment is not displayed
        if (this.mMeetingFragment == null) {
            // Creates the main fragment
            this.mMeetingFragment = MeetingFragment.newInstance();

            this.addFragment(idOfFrameLayout, this.mMeetingFragment);
        }
    }

    /**
     * Configures and shows the second fragment (see {@link CreatorOfMeetingFragment}
     * @param idOfFrameLayout an integer that contains the id value
     */
    private void configureAndShowSecondFragment(final int idOfFrameLayout) {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mCreatorOfMeetingFragment = (CreatorOfMeetingFragment) getSupportFragmentManager().findFragmentById(idOfFrameLayout);

        // If the fragment is not displayed and it exists
        if (this.mCreatorOfMeetingFragment == null && findViewById(R.id.activity_main_second_frame_layout) != null) {
            // Creates the second fragment
            this.mCreatorOfMeetingFragment = CreatorOfMeetingFragment.newInstance();

            this.addFragment(idOfFrameLayout, this.mCreatorOfMeetingFragment);
        }
    }
}