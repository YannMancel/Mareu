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
            public void updateRecyclerView(boolean isFilter) {}

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
    public void fragmentPresenterInterface_deleteMeeting_meeting() {
        Meeting meeting = this.mFragmentPresenter.getMeetings().get(0);
        this.mFragmentPresenter.deleteMeeting(meeting, false);

        assertFalse(this.mFragmentPresenter.getMeetings().contains(meeting));
    }

    @Test
    public void fragmentPresenterInterface_deleteMeeting_meetingAndFilteredMeeting() {
        // ROOM FILTER: Should have only 1 meeting
        this.mFragmentPresenter.filterPerRoom("Peach");

        assertEquals(1, this.mFragmentPresenter.getFilteredMeetings().size());

        // MEETING: Room equal to "Peach"
        Meeting meeting = this.mFragmentPresenter.getMeetings().get(0);

        // DELETE MEETING AND ALSO FILTERED MEETING
        this.mFragmentPresenter.deleteMeeting(meeting, true);

        assertFalse(this.mFragmentPresenter.getFilteredMeetings().contains(meeting));
        assertFalse(this.mFragmentPresenter.getMeetings().contains(meeting));
    }

    @Test
    public void fragmentPresenterInterface_addMeeting() {
        assertFalse(this.mFragmentPresenter.getMeetings().contains(this.mMeeting));

        final String topic = this.mFragmentPresenter.addMeeting(this.mMeeting.getTopic(),
                                                                this.mMeeting.getHour(),
                                                                this.mMeeting.getRoom(),
                                                                this.mMeeting.getMember());

        assertTrue(this.mFragmentPresenter.getMeetings().contains(this.mMeeting));
        assertEquals(this.mMeeting.getTopic(), topic);
    }

    @Test
    public void fragmentPresenterInterface_getFilteredMeeting() throws Exception {
        // ROOM FILTER: Should have NONE of meeting
        this.mFragmentPresenter.filterPerRoom("Frodon");

        assertEquals(0, this.mFragmentPresenter.getFilteredMeetings().size());

        // ROOM FILTER: Should have only 1 meeting
        this.mFragmentPresenter.filterPerRoom("Peach");

        assertEquals(1, this.mFragmentPresenter.getFilteredMeetings().size());

        // HOURS FILTER: Should have only 2 meetings
        this.mFragmentPresenter.filterPerHours("08:00", "17:00");

        assertEquals(2, this.mFragmentPresenter.getFilteredMeetings().size());
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
    public void fragmentPresenterInterface_getSelectedMembers() {
        List<Member> actualList = this.mFragmentPresenter.getSelectedMembers();
        assertEquals(0, actualList.size());
    }

    @Test
    public void fragmentPresenterInterface_getSelectedMembersToString() {
        // maxime@lamzone.com, paul@lamzone.com
        final String expectedString = this.mMembers.get(0).getEmail() + ", " + this.mMembers.get(1).getEmail();

        // ADD
        this.mFragmentPresenter.AddOrDeleteSelectedMember(this.mMembers.get(0));
        this.mFragmentPresenter.AddOrDeleteSelectedMember(this.mMembers.get(1));
        this.mFragmentPresenter.AddOrDeleteSelectedMember(this.mMembers.get(2));

        // DELETE
        this.mFragmentPresenter.AddOrDeleteSelectedMember(this.mMembers.get(2));

        assertEquals(this.mFragmentPresenter.getSelectedMembersToString(), expectedString);
    }

    @Test
    public void fragmentPresenterInterface_AddOrDeleteSelectedMember() {
        // ADD
        assertTrue(this.mFragmentPresenter.AddOrDeleteSelectedMember(this.mMembers.get(0)));

        // DELETE
        assertFalse(this.mFragmentPresenter.AddOrDeleteSelectedMember(this.mMembers.get(0)));
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