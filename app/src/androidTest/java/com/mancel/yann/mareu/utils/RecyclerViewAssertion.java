package com.mancel.yann.mareu.utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;

/**
 * Created by Yann MANCEL on 22/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.utils
 *
 * A class which implements {@link ViewAssertion}
 */
public class RecyclerViewAssertion implements ViewAssertion {

    // FIELDS --------------------------------------------------------------------------------------

    private final Matcher<Integer> mMatcher;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param matcher a {@link Matcher} of {@link Integer}
     */
    private RecyclerViewAssertion(Matcher<Integer> matcher) {
        this.mMatcher = matcher;
    }

    // METHODS -------------------------------------------------------------------------------------

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        assertThat(adapter.getItemCount(), this.mMatcher);
    }

    // WITH ITEM COUNT *****************************************************************************

    /**
     * returns a {@link RecyclerViewAssertion}
     * @param expectedCount an integer that contains the expected value
     * @return a {@link RecyclerViewAssertion}
     */
    public static RecyclerViewAssertion withItemCount(final int expectedCount) {
        return withItemCount(Matchers.is(expectedCount));
    }

    /**
     * returns a {@link RecyclerViewAssertion}
     * @param matcher a {@link Matcher} of {@link Integer}
     * @return a {@link RecyclerViewAssertion}
     */
    public static RecyclerViewAssertion withItemCount(Matcher<Integer> matcher) {
        return new RecyclerViewAssertion(matcher);
    }
}
