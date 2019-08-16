package com.mancel.yann.mareu.ui.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.TimePicker;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseActivity;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragmentListener;
import com.mancel.yann.mareu.ui.fragments.HoursFilterFragment;
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

    private HoursFilterFragment mHoursFilterFragment;

    public static final String BUNDLE_EXTRA_MINIMAL_HOUR = "BUNDLE_EXTRA_MINIMAL_HOUR";
    public static final String BUNDLE_EXTRA_MAXIMAL_HOUR = "BUNDLE_EXTRA_MAXIMAL_HOUR";

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

        // Configures and shows the main fragment
        this.configureAndShowMainFragment();
    }

    // INTERFACE OF FRAGMENT LISTENER **************************************************************

    @Override
    public void showMessageFromFragment(String message) {}

    @Override
    public void onClickFromFragment(String message) {}

    @Override
    public void onClickFromFragment(String messageA, String messageB) {
        // Creates Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Modifies the title
        builder.setTitle(getString(R.string.creation_of_hours_filter))
               .setMessage(getString(R.string.question_for_creation_of_hours_filter, messageA, messageB))
               .setPositiveButton(getString(R.string.yes),
                        (dialog, which) -> {
                            Intent intent = new Intent();
                            intent.putExtra(BUNDLE_EXTRA_MINIMAL_HOUR, messageA);
                            intent.putExtra(BUNDLE_EXTRA_MAXIMAL_HOUR, messageB);

                            // Good execution and closes the activity
                            setResult(RESULT_OK, intent);
                            finish();})
               .setNegativeButton(getString(R.string.no),
                        (dialog, which) -> {finish();});

        // Creates and shows the AlertDialog widget
        builder.create().show();
    }

    // INTERFACE OF ON TIME PICKER FRAGMENT LISTENER ***********************************************

    @Override
    public void onTimeSet(int id, TimePicker view, int hourOfDay, int minute) {
        final String time = TimeTools.convertHourAndMinuteToString(hourOfDay, minute);
        this.mHoursFilterFragment.setTextViewById(id, time);
    }

    // FRAGMENTS ***********************************************************************************

    /**
     * Configures and shows the main fragment (see {@link HoursFilterFragment}
     */
    private void configureAndShowMainFragment() {
        // Creates a Fragment [FragmentManager -> Fragment]
        this.mHoursFilterFragment = (HoursFilterFragment) getSupportFragmentManager().findFragmentById(R.id.activity_filter_main_frame_layout);

        // If the fragment is not displayed
        if (this.mHoursFilterFragment == null) {
            // Creates the main fragment
            this.mHoursFilterFragment = HoursFilterFragment.newInstance();

            this.addFragment(R.id.activity_filter_main_frame_layout, this.mHoursFilterFragment);
        }
    }
}
