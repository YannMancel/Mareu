package com.mancel.yann.mareu.ui.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseActivity;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.ui.fragments.CreatorOfMeetingFragment;
import com.mancel.yann.mareu.ui.fragments.MeetingFragment;
import com.mancel.yann.mareu.utils.ShowMessage;

import butterknife.BindView;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.activities
 *
 * A {@link BaseActivity} subclass which implements {@link BaseFragment.FragmentListener}.
 */
public class MainActivity extends BaseActivity implements BaseFragment.FragmentListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.activity_main_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private MeetingFragment mMeetingFragment;
    private CreatorOfMeetingFragment mCreatorOfMeetingFragment;

    public static final int REQUEST_CODE_SECOND_ACTIVITY = 100;

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
        this.configureAndShowMainFragment();

        // Configures and shows the second fragment
        this.configureAndShowSecondFragment();
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
                this.mMeetingFragment.filterPerDate();
                return true;
            }
            case R.id.menu_activity_main_filter_room: {
                this.mMeetingFragment.filterPerRoom();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    // CALLBACKS OF FRAGMENT ***********************************************************************

    @Override
    public void showMessageFromFragment(String message) {
        ShowMessage.showMessageWithSnackbar(this.mCoordinatorLayout, message);
    }

    @Override
    public void onClickFromFragment() {
        // Only one fragment is displayed
        if (this.mCreatorOfMeetingFragment == null) {
            this.startAnotherActivityForResult(this, SecondActivity.class, REQUEST_CODE_SECOND_ACTIVITY);
        }
    }

    // FRAGMENTS ***********************************************************************************

    /**
     * Configures and shows the main fragment (see {@link MeetingFragment}
     */
    private void configureAndShowMainFragment() {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mMeetingFragment = (MeetingFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_main_frame_layout);

        // If the fragment is not displayed
        if (this.mMeetingFragment == null) {
            // Creates the main fragment
            this.mMeetingFragment = MeetingFragment.newInstance();

            this.addFragment(R.id.activity_main_main_frame_layout, this.mMeetingFragment);
        }
    }

    /**
     * Configures and shows the second fragment (see {@link CreatorOfMeetingFragment}
     */
    private void configureAndShowSecondFragment() {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mCreatorOfMeetingFragment = (CreatorOfMeetingFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_second_frame_layout);

        // If the fragment is not displayed and it exists
        if (this.mCreatorOfMeetingFragment == null && findViewById(R.id.activity_main_second_frame_layout) != null) {
            // Creates the second fragment
            this.mCreatorOfMeetingFragment = CreatorOfMeetingFragment.newInstance();

            this.addFragment(R.id.activity_main_second_frame_layout, this.mCreatorOfMeetingFragment);
        }
    }
}