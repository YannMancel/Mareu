package com.mancel.yann.mareu.ui.activities;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseActivity;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.ui.fragments.MeetingFragment;

import butterknife.BindView;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.activities
 * <p>
 * A {@link BaseActivity} subclass which implements {@link BaseFragment.FragmentListener}.
 */
public class MainActivity extends BaseActivity implements BaseFragment.FragmentListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_main_main_frame_layout)
    FrameLayout mMainFrameLayout;

    private MeetingFragment mMeetingFragment;

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
                Toast.makeText(this, getString(R.string.filter_hour), Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.menu_activity_main_filter_room: {
                Toast.makeText(this, getString(R.string.filter_room), Toast.LENGTH_SHORT).show();
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

            // Adds the transaction to create the fragment [FragmentManager -> FragmentTransaction -> int]
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.activity_main_main_frame_layout, this.mMeetingFragment)
                                       .commit();
        }
    }
}