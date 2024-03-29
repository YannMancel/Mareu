package com.mancel.yann.mareu;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mancel.yann.mareu.model.Room;
import com.mancel.yann.mareu.service.DummyGenerator;
import com.mancel.yann.mareu.ui.activities.MainActivity;
import com.mancel.yann.mareu.utils.ButtonViewAction;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mancel.yann.mareu.utils.RecyclerViewAssertion.withItemCount;
import static com.mancel.yann.mareu.utils.ToolBarMatcher.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Yann MANCEL on 22/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu
 *
 * Instrumented test on {@link MainActivity}
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // FIELDS --------------------------------------------------------------------------------------

    private MainActivity mMainActivity;

    private List<Room> mRooms = DummyGenerator.generatorOfDummyRooms();

    private final int SIZE_MEETINGS = DummyGenerator.generatorOfDummyMeetings().size();
    private final int SIZE_MEMBERS = DummyGenerator.generatorOfDummyMembers().size();

    // RULES ---------------------------------------------------------------------------------------

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    // METHODS -------------------------------------------------------------------------------------

    @Before
    public void setUp() {
        this.mMainActivity = this.mActivityRule.getActivity();
        assertThat(this.mMainActivity, notNullValue());
    }

    // MEETING *************************************************************************************
    
    @Test
    public void mainActivity_D_deleteAction_shouldRemoveAllItems() {
        // TEXT VIEW: Checks if displayed
        onView(withId(R.id.fragment_meeting_tv_no_data)).check(matches(not((isDisplayed()))));

        // RECYCLER VIEW: Deletes all items
        for (int i = 0 ; i < SIZE_MEETINGS ; i++) {
            onView(withId(R.id.fragment_meeting_recycler_view))
                    .perform(actionOnItemAtPosition(0, new ButtonViewAction(R.id.item_meeting_iv_delete)));
        }

        // RECYCLER VIEW: Checks if the size is zero
        onView(withId(R.id.fragment_meeting_recycler_view)).check(withItemCount(0));

        // TEXT VIEW: Checks if displayed
        onView(withId(R.id.fragment_meeting_tv_no_data)).check(matches(isDisplayed()));
    }

    @Test
    public void mainActivity_C_selectAction_shouldAddItem_thenShouldDeleteThisItem() {
        // IDENTIFIER NOT W600dp
        if (this.mMainActivity.getResources().getConfiguration().screenWidthDp < this.mMainActivity.getResources().getInteger(R.integer.identifier_w600dp)) {
            // FAB: Clicks
            onView(withId(R.id.fragment_meeting_fab_add)).perform(click());
        }

        // TEXT INPUT EDIT TEXT: Writes something
        onView(withId(R.id.fragment_creator_of_meeting_tiet_topic)).perform(replaceText("test"),
                                                                            closeSoftKeyboard());

        for (Room room : this.mRooms) {
            // SPINNER: Clicks on the spinner
            onView(withId(R.id.fragment_creator_of_meeting_spinner_room)).perform(click());

            // DATA: Clicks on all the things that matcher with a String class and have the good string
            onData(allOf(is(instanceOf(String.class)), is(room.getName()))).perform(click());

            // SPINNER: Checks if the selected item is correct
            onView(withId(R.id.fragment_creator_of_meeting_spinner_room)).check(matches(withSpinnerText(containsString(room.getName()))));
        }

        // BUTTON: Clicks
        onView(withId(R.id.fragment_creator_of_meeting_b_hour)).perform(click());

        // ALERT DIALOG: Clicks on the Yes button
        onView(withText(R.string.cancel)).perform(click());

        // RECYCLER VIEW:
        //  - Checks if it is displayed
        //  - Checks the size
        //  - clicks on the item at position 0
        onView(withId(R.id.fragment_creator_recycler_view)).check(matches(isDisplayed()))
                                                           .check(withItemCount(SIZE_MEMBERS))
                                                           .perform(actionOnItemAtPosition(0,
                                                                                            new ButtonViewAction(R.id.item_member_cb_check)));

        // FAB: Clicks
        onView(withId(R.id.fragment_creator_of_meeting_fab)).perform(click());

        // ALERT DIALOG: Clicks on the Yes button
        onView(withText(R.string.yes)).perform(click());

        // RECYCLER VIEW:
        //  - Checks the size
        //  - Deletes the last item
        //  - Checks the size
        onView(withId(R.id.fragment_meeting_recycler_view)).check(matches(hasChildCount(SIZE_MEETINGS + 1)))
                                                           .perform(actionOnItemAtPosition(SIZE_MEETINGS - 1, new ButtonViewAction(R.id.item_meeting_iv_delete)))
                                                           .check(matches(hasChildCount(SIZE_MEETINGS)));
    }

    // FILTER **************************************************************************************

    @Test
    public void mainActivity_A_selectAction_shouldFilterPerRoom() {
        ViewInteraction actionMenu;

        // TOOL BAR: Clicks on the filter icon
        actionMenu = onView(allOf(withContentDescription(R.string.filter),
                                  childAtPosition(childAtPosition(withId(R.id.toolbar),
                                                                 1),
                                                 0),
                                  isDisplayed()));
        actionMenu.perform(click());

        // IDENTIFIER NOT W600dp
        if (this.mMainActivity.getResources().getConfiguration().screenWidthDp < this.mMainActivity.getResources().getInteger(R.integer.identifier_w600dp)) {
            // TOOL BAR: Clicks on the room filter
            actionMenu = onView(allOf(withId(R.id.title),
                                      withText(R.string.filter_room),
                                      childAtPosition(childAtPosition(withId(R.id.content),
                                                                     1),
                                                     0),
                                      isDisplayed()));
            actionMenu.perform(click());
        }
        else {
            // TOOL BAR: Clicks on the room filter
            actionMenu = onView(allOf(withId(R.id.title),
                                      withText(R.string.filter_room),
                                      childAtPosition(allOf(withId(R.id.content),
                                                            childAtPosition(withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                                                           1)),
                                                            1),
                                      isDisplayed()));
            actionMenu.perform(click());
        }

        for (Room room : this.mRooms) {
            // SPINNER: Clicks on the spinner
            onView(withId(R.id.fragment_filter_room_spinner)).perform(click());

            // DATA: Clicks on all the things that matcher with a String class and have the good string
            onData(allOf(is(instanceOf(String.class)), is(room.getName()))).perform(click());

            // SPINNER: Checks if the selected item is correct
            onView(withId(R.id.fragment_filter_room_spinner)).check(matches(withSpinnerText(containsString(room.getName()))));
        }

        // BUTTON: Clicks
        onView(withId(R.id.fragment_filter_room_button)).perform(click());

        // ALERT DIALOG: Clicks on the Yes button
        onView(withText(R.string.yes)).perform(click());

        // RECYCLER VIEW: Checks the size
        onView(withId(R.id.fragment_meeting_recycler_view)).check(matches(not(hasChildCount(SIZE_MEETINGS))));

        // BUTTON: Clicks
        onView(withId(R.id.fragment_meeting_fab_filter)).perform(click());

        // RECYCLER VIEW: Checks the size
        onView(withId(R.id.fragment_meeting_recycler_view)).check(matches(hasChildCount(SIZE_MEETINGS)));
    }

    @Test
    public void mainActivity_B_selectAction_shouldFilterPerHours() {
        ViewInteraction actionMenu;

        // TOOL BAR: Clicks on the filter icon
        actionMenu = onView(allOf(withContentDescription(R.string.filter),
                                  childAtPosition(childAtPosition(withId(R.id.toolbar),
                                                                 1),
                                                 0),
                                  isDisplayed()));
        actionMenu.perform(click());

        // IDENTIFIER NOT W600dp
        if (this.mMainActivity.getResources().getConfiguration().screenWidthDp < this.mMainActivity.getResources().getInteger(R.integer.identifier_w600dp)) {
            // TOOL BAR: Clicks on the hours filter
            actionMenu = onView(allOf(withId(R.id.title),
                                      withText(R.string.filter_hour),
                                      childAtPosition(childAtPosition(withId(R.id.content),
                                                                     1),
                                                     0),
                                      isDisplayed()));
            actionMenu.perform(click());
        }
        else {
            // TOOL BAR: Clicks on the hours filter
            actionMenu = onView(allOf(withId(R.id.title),
                                      withText(R.string.filter_hour),
                                      childAtPosition(allOf(withId(R.id.content),
                                                            childAtPosition(withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                                                           1)),
                                                     1),
                                      isDisplayed()));
            actionMenu.perform(click());
        }

        // BUTTON: Clicks
        onView(withId(R.id.fragment_filter_hours_b_minimal_hour)).perform(click());

        // ALERT DIALOG: Clicks on the Yes button
        onView(withText(R.string.cancel)).perform(click());

        // BUTTON: Clicks
        onView(withId(R.id.fragment_filter_hours_b_maximal_hour)).perform(click());

        // ALERT DIALOG: Clicks on the Yes button
        onView(withText(R.string.cancel)).perform(click());

        // BUTTON: Clicks
        onView(withId(R.id.fragment_filter_hours_b_filter)).perform(click());

        // ALERT DIALOG: Clicks on the Yes button
        onView(withText(R.string.yes)).perform(click());

        // RECYCLER VIEW: Checks the size
        onView(withId(R.id.fragment_meeting_recycler_view)).check(matches(not(hasChildCount(SIZE_MEETINGS))));

        // BUTTON: Clicks
        onView(withId(R.id.fragment_meeting_fab_filter)).perform(click());

        // RECYCLER VIEW: Checks the size
        onView(withId(R.id.fragment_meeting_recycler_view)).check(matches(hasChildCount(SIZE_MEETINGS)));
    }
}
