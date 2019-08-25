package com.mancel.yann.mareu.presenter;

import android.support.annotation.VisibleForTesting;

import com.mancel.yann.mareu.di.DI;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;
import com.mancel.yann.mareu.service.ApiService;
import com.mancel.yann.mareu.ui.View;
import com.mancel.yann.mareu.utils.TimeTools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yann MANCEL on 05/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.presenter
 *
 * A class which implements {@link Presenter.FragmentPresenterInterface}.
 */
public class FragmentPresenter implements Presenter.FragmentPresenterInterface {

    // FIELDS --------------------------------------------------------------------------------------

    private ApiService mService;
    private View.FragmentView mView;
    private List<Member> mSelectedMembers;

    private static List<Meeting> mFilteredMeetings = new ArrayList<>();

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param view a {@link View.FragmentView} interface that contains the View
     */
    public FragmentPresenter(View.FragmentView view) {
        this.mService = DI.getApiService();
        this.mView = view;
        this.mSelectedMembers = new ArrayList<>();
    }

    /**
     * Constructor
     * Useful for tests, so we ensure the context is clean.
     * @param view a {@link View.FragmentView} interface that contains the View
     * @param apiService  a {@link ApiService}
     */
    @VisibleForTesting
    public FragmentPresenter(View.FragmentView view, ApiService apiService) {
        this.mService = apiService;
        this.mView = view;
        this.mSelectedMembers = new ArrayList<>();
    }

    // METHODS -------------------------------------------------------------------------------------

    // MEETINGS ************************************************************************************

    @Override
    public List<Meeting> getMeetings() {
        return this.mService.getMeetings();
    }

    @Override
    public void deleteMeeting(Meeting meeting, boolean isFilter) {
        if (isFilter) {
            mFilteredMeetings.remove(meeting);
        }

        this.mService.deleteMeeting(meeting);

        // Callback to the View (Normal Mode)
        this.mView.updateRecyclerView(isFilter);
    }

    @Override
    public String addMeeting(String topic, String hour, String room, String member) {
        Meeting meeting = new Meeting(this.mService.getMeetings().size() + 1,
                                       topic,
                                       hour,
                                       room,
                                       member);

        this.mService.addMeeting(meeting);

        return topic;
    }

    @Override
    public List<Meeting> getFilteredMeetings() {
        return mFilteredMeetings;
    }

    // ROOMS ***************************************************************************************

    @Override
    public List<String> getRoomsName() {
        List<String> nameOfRooms = new ArrayList<>();

        for (Room room : this.mService.getRooms()) {
            nameOfRooms.add(room.getName());
        }

        return nameOfRooms;
    }

    // MEMBERS *************************************************************************************

    @Override
    public List<Member> getMembers() {
        return this.mService.getMembers();
    }

    @Override
    public List<Member> getSelectedMembers() {
        return this.mSelectedMembers;
    }

    @Override
    public String getSelectedMembersToString() {
        StringBuilder sb = new StringBuilder();

        int i = 0;

        for (Member member : this.mSelectedMembers) {
            sb.append(member.getEmail());

            if (++i != this.mSelectedMembers.size()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    @Override
    public boolean AddOrDeleteSelectedMember(Member member) {
        if (this.mSelectedMembers.contains(member)) {
            this.mSelectedMembers.remove(member);
            return false;
        }
        else {
            this.mSelectedMembers.add(member);
            return true;
        }
    }

    // MEMORY LEAKS ********************************************************************************

    @Override
    public void onDetach() {
        // To prevent memory leaks
        this.mView = null;
    }

    // FILTERS *************************************************************************************

    @Override
    public List<Meeting> filterPerRoom(String roomName) {
        List<Meeting> filteredMeetings = new ArrayList<>();

        for (Meeting meeting : this.mService.getMeetings()) {
            if (meeting.getRoom().equals(roomName)) {
                filteredMeetings.add(meeting);
            }
        }

        mFilteredMeetings = filteredMeetings;

        return filteredMeetings;
    }

    @Override
    public List<Meeting> filterPerHours(String minDateInString, String maxDateInString) throws Exception {
        List<Meeting> filteredMeetings = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat(TimeTools.PATTERN_FORMAT);

        Date minDate, maxDate, meetingDate;

        try {
            minDate = dateFormat.parse(minDateInString);
            maxDate = dateFormat.parse(maxDateInString);

            // Error between the dates (inverse order)
            if (minDate.after(maxDate)) throw new Exception("Error: Bad order in the arguments");

            for (Meeting meeting : this.mService.getMeetings()) {
                // Retrieves the hour of meeting
                meetingDate = dateFormat.parse(meeting.getHour());

                if (meetingDate.equals(minDate) ||
                    meetingDate.equals(maxDate) ||
                    (meetingDate.after(minDate) && meetingDate.before(maxDate))) {
                    filteredMeetings.add(meeting);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mFilteredMeetings = filteredMeetings;

        return filteredMeetings;
    }
}
