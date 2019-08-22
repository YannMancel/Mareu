package com.mancel.yann.mareu.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Created by Yann MANCEL on 22/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.utils
 *
 * A class which implements {@link ViewAction}
 */
public class ButtonViewAction implements ViewAction {

    // FIELDS --------------------------------------------------------------------------------------

    private final int mId;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param id an integer that contains the id value
     */
    public ButtonViewAction(int id) {
        this.mId = id;
    }

    // METHODS -------------------------------------------------------------------------------------

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(this.mId);
        button.performClick();
    }
}
