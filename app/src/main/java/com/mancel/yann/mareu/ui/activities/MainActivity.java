package com.mancel.yann.mareu.ui.activities;

import android.support.v7.widget.Toolbar;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseActivity;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.activities
 *
 * A {@link BaseActivity} subclass.
 */
public class MainActivity extends BaseActivity {

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Toolbar getToolBar() {
        return null;
    }

    @Override
    protected void configureDesign() {

    }
}