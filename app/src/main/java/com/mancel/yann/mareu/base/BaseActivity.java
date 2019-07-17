package com.mancel.yann.mareu.base;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

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

    // MESSAGES ************************************************************************************

    /**
     * Shows a {@link Snackbar} with a message
     * @param coordinatorLayout a {@link CoordinatorLayout} that contains the view
     * @param message a {@link String} that contains the message to display
     */
    protected void showMessageWithSnackbar(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Shows a {@link Toast} with a message
     * @param message a {@link String} that contains the message to display
     */
    protected void showMessageWithToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}