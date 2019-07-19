package com.mancel.yann.mareu.ui.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseActivity;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.ui.fragments.CreatorOfMeetingFragment;
import com.mancel.yann.mareu.utils.ShowMessage;

import butterknife.BindView;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.activities
 * <p>
 * A {@link BaseActivity} subclass which implements {@link BaseFragment.FragmentListener}.
 */
public class SecondActivity extends BaseActivity implements BaseFragment.FragmentListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_second_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    private CreatorOfMeetingFragment mCreatorOfMeetingFragment;

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

    // CALLBACKS OF FRAGMENT ***********************************************************************

    @Override
    public void showMessageFromFragment(String message) {
        ShowMessage.showMessageWithSnackbar(this.mCoordinatorLayout, message);
    }

    @Override
    public void onClickFAB() {

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
