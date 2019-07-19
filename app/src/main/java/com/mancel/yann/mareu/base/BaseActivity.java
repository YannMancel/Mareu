package com.mancel.yann.mareu.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.base
 *
 * A {@link AppCompatActivity} subclass.
 */
public abstract class BaseActivity extends AppCompatActivity {

    // METHODS -------------------------------------------------------------------------------------

    protected abstract int getActivityLayout();
    protected abstract Toolbar getToolBar();
    protected abstract void configureDesign();

    // ACTIVITY ************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Associates the layout file to this class
        setContentView(this.getActivityLayout());

        // Using the ButterKnife library
        ButterKnife.bind(this);

        // Configures the design of the activity
        this.configureDesign();
    }

    // TOOL BAR ************************************************************************************

    /**
     * Configures the ToolBar field
     */
    protected void configureToolBar() {
        // If ToolBar exists
        if (this.getToolBar() != null) {
            setSupportActionBar(this.getToolBar());
        }
    }

    /**
     * Adds the Up button of the {@link android.support.v7.widget.Toolbar}
     */
    protected void addUpButtonOfToolBar() {
        // Gets a Support ActionBar corresponding to this ToolBar
        ActionBar actionBar = getSupportActionBar();

        // Enables the Up Button
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // FRAGMENT ************************************************************************************

    /**
     * Adds a {@link Fragment} to the {@link android.app.Activity}
     * @param idFrameLayout an integer that corresponds to the id of a {@link android.widget.FrameLayout}
     * @param fragment a instance of class which extends to {@link Fragment}
     * @param <T> a type parameter section
     */
    protected <T extends Fragment> void addFragment(int idFrameLayout, T fragment) {
        // Adds the transaction to create the fragment [FragmentManager -> FragmentTransaction -> int]
        getSupportFragmentManager().beginTransaction()
                                   .add(idFrameLayout, fragment)
                                   .commit();
    }

    // LAUNCHERS OF ACTIVITY ***********************************************************************

    /**
     * Starts the another Activity with its request code
     * @param context a {@link Context} that sends the {@link Intent}
     * @param cls a {@link Class} that correspond to the launched {@link Activity}
     * @param requestCode an integer that corresponds to the request code
     * @param <T> a type parameter section
     */
    protected <T extends Activity> void startAnotherActivityForResult(Context context, Class<T> cls, int requestCode) {
        Intent intent = new Intent(context, cls);

        startActivityForResult(intent, requestCode);
    }
}