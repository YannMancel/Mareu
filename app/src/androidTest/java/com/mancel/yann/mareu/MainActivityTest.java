package com.mancel.yann.mareu;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;
import com.mancel.yann.mareu.service.DummyGenerator;
import com.mancel.yann.mareu.ui.activities.MainActivity;
import com.mancel.yann.mareu.utils.ButtonViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // FIELDS --------------------------------------------------------------------------------------

    private MainActivity mMainActivity;

    private List<Meeting> mMeetings = DummyGenerator.generatorOfDummyMeetings();
    private List<Room> mRooms = DummyGenerator.generatorOfDummyRooms();
    private List<Member> mMembers = DummyGenerator.generatorOfDummyMembers();

    // toolbar
    private final int mIdToolBar = R.id.toolbar;
    private final int mIdFilterToolBar = R.string.filter;
    private final int mIdRoomFilterToolBar = R.string.filter_room;
    private final int mIdHoursFilterToolBar = R.string.filter_hour;

    // fragment_meeting
    private final int mIdMeetingRecyclerView = R.id.fragment_meeting_recycler_view;
    private final int mIdAddFabOfMeetingFragment = R.id.fragment_meeting_fab_add;
    private final int idOfFilterFab = R.id.fragment_meeting_fab_filter;
    private final int mIdNoData = R.id.fragment_meeting_tv_no_data;

    // item_meeting
    private final int mIdDeleteButton = R.id.item_meeting_iv_delete;

    // fragment_creator_of_meeting
    private final int mIdSpinnerOfCreation = R.id.fragment_creator_of_meeting_spinner_room;
    private final int mIdMemberRecyclerView = R.id.fragment_creator_recycler_view;
    private final int mIdFabOfMemberFragment = R.id.fragment_creator_of_meeting_fab;

    // item_member
    private final int mIdMemberCheckBox = R.id.item_member_cb_check;

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
    public void mainActivity_recyclerView_shouldNotBeEmpty() {
        // RECYCLER VIEW: Checks if at least one item is present
        onView(withId(this.mIdMeetingRecyclerView)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void mainActivity_deleteAction_shouldRemoveItem() {
        final int INITIAL_SIZE = this.mMeetings.size();

        // RECYCLER VIEW:
        //  - Checks the size
        //  - Deletes the item at position 0
        //  - Checks the size
        onView(withId(this.mIdMeetingRecyclerView)).check(withItemCount(INITIAL_SIZE))
                                                   .perform(actionOnItemAtPosition(0, new ButtonViewAction(this.mIdDeleteButton)))
                                                   .check(withItemCount(INITIAL_SIZE - 1));
    }

    @Test
    public void mainActivity_deleteAction_shouldRemoveAllItems() {
        // TEXT VIEW: Checks if displayed
        onView(withId(this.mIdNoData)).check(matches(not((isDisplayed()))));

        // RECYCLER VIEW: Deletes all items
        for (Meeting meeting : this.mMeetings) {
            onView(withId(this.mIdMeetingRecyclerView))
                    .perform(actionOnItemAtPosition(0, new ButtonViewAction(this.mIdDeleteButton)));
        }

        // RECYCLER VIEW: Checks if the size is zero
        onView(withId(this.mIdMeetingRecyclerView)).check(withItemCount(0));

        // TEXT VIEW: Checks if displayed
        onView(withId(this.mIdNoData)).check(matches(isDisplayed()));
    }

    @Test
    public void mainActivity_selectAction_shouldAddItem() {
        final int INITIAL_SIZE_OF_MEMBER = this.mMembers.size();
        final int INITIAL_SIZE_OF_MEETING = this.mMeetings.size();
        
        // FAB: Clicks
        onView(withId(this.mIdAddFabOfMeetingFragment)).perform(click());

        for (Room room : this.mRooms) {
            // SPINNER: Clicks on the spinner
            onView(withId(this.mIdSpinnerOfCreation)).perform(click());

            // DATA: Clicks on all the things that matcher with a String class and have the good string
            onData(allOf(is(instanceOf(String.class)), is(room.getName()))).perform(click());

            // SPINNER: Checks if the selected item is correct
            onView(withId(this.mIdSpinnerOfCreation)).check(matches(withSpinnerText(containsString(room.getName()))));
        }

        // RECYCLER VIEW:
        //  - Checks if it is displayed
        //  - Checks the size
        //  - clicks on the item at position 0
        onView(withId(this.mIdMemberRecyclerView)).check(matches(isDisplayed()))
                                                  .check(withItemCount(INITIAL_SIZE_OF_MEMBER))
                                                  .perform(actionOnItemAtPosition(0, new ButtonViewAction(this.mIdMemberCheckBox)));

        // FAB: Clicks
        onView(withId(this.mIdFabOfMemberFragment)).perform(click());

        // ALERT DIALOG: Clicks on the Yes button
        onView(withText(R.string.yes)).perform(click());

        // RECYCLER VIEW: Checks the size
        onView(withId(this.mIdMeetingRecyclerView)).check(withItemCount(INITIAL_SIZE_OF_MEETING + 1));
    }

    // FILTER **************************************************************************************

    @Test
    public void mainActivity_selectAction_shouldFilterPerRoom() {
        ViewInteraction actionMenu;

        // TOOL BAR: Clicks on the filter icon
        actionMenu = onView(allOf(withContentDescription(this.mIdFilterToolBar),
                                  childAtPosition(childAtPosition(withId(this.mIdToolBar),
                                                                 1),
                                                 0),
                                  isDisplayed()));
        actionMenu.perform(click());

        // TOOL BAR: Clicks on the room filter
        actionMenu = onView(allOf(withId(R.id.title),
                                  withText(this.mIdRoomFilterToolBar),
                                  childAtPosition(childAtPosition(withId(R.id.content),
                                                                 1),
                                                 0),
                                  isDisplayed()));
        actionMenu.perform(click());
    }

    @Test
    public void mainActivity_selectAction_shouldFilterPerHours() {
        ViewInteraction actionMenu;

        // TOOL BAR: Clicks on the filter icon
        actionMenu = onView(allOf(withContentDescription(this.mIdFilterToolBar),
                                  childAtPosition(childAtPosition(withId(this.mIdToolBar),
                                                                 1),
                                                 0),
                                  isDisplayed()));
        actionMenu.perform(click());

        // TOOL BAR: Clicks on the hours filter
        actionMenu = onView(allOf(withId(R.id.title),
                                  withText(this.mIdHoursFilterToolBar),
                                  childAtPosition(childAtPosition(withId(R.id.content),
                                                                 1),
                                                 0),
                                  isDisplayed()));
        actionMenu.perform(click());
    }
}
