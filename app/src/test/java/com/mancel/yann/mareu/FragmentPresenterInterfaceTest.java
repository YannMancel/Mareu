package com.mancel.yann.mareu;

import com.mancel.yann.mareu.di.DI;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.presenter.FragmentPresenter;
import com.mancel.yann.mareu.presenter.Presenter;
import com.mancel.yann.mareu.service.DummyGenerator;
import com.mancel.yann.mareu.ui.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yann MANCEL on 21/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu
 *
 * Unit test on {@link Presenter.FragmentPresenterInterface}
 */
@RunWith(JUnit4.class)
public class FragmentPresenterInterfaceTest {

    // FIELDS --------------------------------------------------------------------------------------

    private Presenter.FragmentPresenterInterface mFragmentPresenter;

    private final Meeting mMeeting = new Meeting(5, "Meeting E", "14:00", "Peach", "yann@lamzone.com");

    private final String mJsonFormat = "{"                                +
                                       "\"identifier\":5,"                +
                                       "\"topic\":\"Meeting E\","         +
                                       "\"hour\":\"14:00\","              +
                                       "\"room\":\"Peach\","              +
                                       "\"member\":\"yann@lamzone.com\""  +
                                       "}";

    private final List<Member> mMembers = Arrays.asList(
            new Member(1, "Maxime", "Dupond", "maxime@lamzone.com"),
            new Member(2, "Paul", "Patru", "paul@lamzone.com"),
            new Member(3, "Amandine", "Delorme", "amandine@lamzone.com")
            );

    // METHODS -------------------------------------------------------------------------------------

    @Before
    public void setUp() {
        View.FragmentView fragmentViewMock = new View.FragmentView() {
            @Override
            public void updateDataOfRecyclerView(List<Meeting> meetings, boolean isFilter) {}

            @Override
            public void setTextById(int id, String time) {}
        };

        this.mFragmentPresenter = new FragmentPresenter(fragmentViewMock, DI.getNewInstanceApiService());
    }

    // MEETINGS ************************************************************************************

    @Test
    public void fragmentPresenterInterface_getMeetings() {
        List<Meeting> actualList = this.mFragmentPresenter.getMeetings();
        List<Meeting> expectedList = DummyGenerator.generatorOfDummyMeetings();

        assertThat(actualList, containsInAnyOrder(expectedList.toArray()));
    }

    @Test
    public void fragmentPresenterInterface_deleteMeeting() {
        Meeting meeting = this.mFragmentPresenter.getMeetings().get(0);
        this.mFragmentPresenter.deleteMeeting(meeting);

        assertFalse(this.mFragmentPresenter.getMeetings().contains(meeting));
    }

    @Test
    public void fragmentPresenterInterface_addMeeting() {
        assertFalse(this.mFragmentPresenter.getMeetings().contains(this.mMeeting));

        this.mFragmentPresenter.addMeeting(this.mJsonFormat);

        assertTrue(this.mFragmentPresenter.getMeetings().contains(this.mMeeting));
    }

    @Test
    public void fragmentPresenterInterface_createNewMeetingToString() {
        assertFalse(this.mFragmentPresenter.getMeetings().contains(this.mMeeting));

        final String actualString = this.mFragmentPresenter.createNewMeetingToString("Meeting E", "14:00", "Peach", "yann@lamzone.com");

        assertEquals(actualString, this.mJsonFormat);
    }

    // ROOMS ***************************************************************************************

    @Test
    public void fragmentPresenterInterface_getRoomsName() {
        List<String> expectedList = Arrays.asList("Peach","Mario","Luigi");

        List<String> actualList = this.mFragmentPresenter.getRoomsName();

        assertThat(actualList, containsInAnyOrder(expectedList.toArray()));
    }

    // MEMBERS *************************************************************************************

    @Test
    public void fragmentPresenterInterface_getMembers() {
        List<Member> actualList = this.mFragmentPresenter.getMembers();
        List<Member> expectedList = DummyGenerator.generatorOfDummyMembers();

        assertThat(actualList, containsInAnyOrder(expectedList.toArray()));
    }

    @Test
    public void fragmentPresenterInterface_AddOrDeleteMember() {
        // ADD
        assertTrue(this.mFragmentPresenter.AddOrDeleteMember(this.mMembers.get(0)));

        // DELETE
        assertFalse(this.mFragmentPresenter.AddOrDeleteMember(this.mMembers.get(0)));
    }

    @Test
    public void fragmentPresenterInterface_getSelectedMembers() {
        // maxime@lamzone.com, paul@lamzone.com
        final String expectedString = this.mMembers.get(0).getEmail() + ", " + this.mMembers.get(1).getEmail();

        // ADD
        this.mFragmentPresenter.AddOrDeleteMember(this.mMembers.get(0));
        this.mFragmentPresenter.AddOrDeleteMember(this.mMembers.get(1));
        this.mFragmentPresenter.AddOrDeleteMember(this.mMembers.get(2));

        // DELETE
        this.mFragmentPresenter.AddOrDeleteMember(this.mMembers.get(2));

        assertEquals(this.mFragmentPresenter.getSelectedMembers(), expectedString);
    }

    // FILTERS *************************************************************************************

    @Test
    public void fragmentPresenterInterface_filterPerRoom() {
        final String roomFilter = "Peach";
        final int expectedPositionOfList = 0;

        int i = 0;

        // Only the position 0 is equal to roomFilter
        for (Meeting meeting : this.mFragmentPresenter.getMeetings()) {
            if (i++ == expectedPositionOfList) {
                assertTrue(meeting.getRoom().equals(roomFilter));
            }
            else {
                assertFalse(meeting.getRoom().equals(roomFilter));
            }
        }

        final List<Meeting> expectedList = Collections.singletonList(this.mFragmentPresenter.getMeetings().get(expectedPositionOfList));
        final List<Meeting> actualList = this.mFragmentPresenter.filterPerRoom(roomFilter);

        assertThat(actualList, containsInAnyOrder(expectedList.toArray()));
    }

    @Test
    public void fragmentPresenterInterface_filterPerRoom_No_Data() {
        final String roomFilter = "Frodon";

        // None of position good
        for (Meeting meeting : this.mFragmentPresenter.getMeetings()) {
            assertFalse(meeting.getRoom().equals(roomFilter));
        }

        final List<Meeting> actualList = this.mFragmentPresenter.filterPerRoom(roomFilter);

        assertEquals(0, actualList.size());
    }

    @Test
    public void fragmentPresenterInterface_filterPerHours() throws Exception {
        final String minHourFilter = "08:00";
        final String maxHourFilter = "19:30";

        // Retrieves all the List of Meeting
        final List<Meeting> expectedList = this.mFragmentPresenter.getMeetings();

        // Deletes the meeting at position 3
        Meeting meetingToDelete = this.mFragmentPresenter.getMeetings().get(3);
        expectedList.remove(meetingToDelete);

        final List<Meeting> actualList = this.mFragmentPresenter.filterPerHours(minHourFilter, maxHourFilter);

        assertThat(actualList, containsInAnyOrder(expectedList.toArray()));
    }

    @Test
    public void fragmentPresenterInterface_filterPerHours_No_Data() throws Exception {
        final String minHourFilter = "08:00";
        final String maxHourFilter = "10:00";

        final List<Meeting> actualList = this.mFragmentPresenter.filterPerHours(minHourFilter, maxHourFilter);

        assertEquals(0, actualList.size());
    }

    @Test(expected = Exception.class)
    public void fragmentPresenterInterface_filterPerHours_Error_Inverse_Order() throws Exception {
        final String minHourFilter = "16:00";
        final String maxHourFilter = "08:00";

        final List<Meeting> actualList = this.mFragmentPresenter.filterPerHours(minHourFilter, maxHourFilter);
    }
}
