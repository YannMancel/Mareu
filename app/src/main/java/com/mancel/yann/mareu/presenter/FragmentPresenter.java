package com.mancel.yann.mareu.presenter;

import android.util.Log;

import com.mancel.yann.mareu.di.DI;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;
import com.mancel.yann.mareu.service.ApiService;
import com.mancel.yann.mareu.ui.View;
import com.mancel.yann.mareu.utils.JsonTools;

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

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor with an argument {@link View.FragmentView}
     * @param view a {@link View.FragmentView} interface that contains the View
     */
    public FragmentPresenter(View.FragmentView view) {
        this.mService = DI.getApiService();
        this.mView = view;
    }

    // METHODS -------------------------------------------------------------------------------------

    @Override
    public List<Meeting> getMeetings() {
        return this.mService.getMeetings();
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        this.mService.deleteMeeting(meeting);

        // Callback to the View
        this.mView.UpdateDataOfRecyclerView();
    }

    @Override
    public void addMeeting(String meetingFromString) {
        Meeting meeting = JsonTools.convertJsonToJava(meetingFromString, Meeting.class);

        this.mService.addMeeting(meeting);

        // Callback to the View
        this.mView.UpdateDataOfRecyclerView();
    }

    @Override
    public String createNewMeetingToString(String topic, String hour, String room, String member) {
        Meeting meeting = new Meeting(this.mService.getMeetings().size() + 1,
                                       topic,
                                       hour,
                                       room,
                                       member);

        return JsonTools.convertJavaToJson(meeting);
    }

    @Override
    public List<Room> getRooms() {
        return this.mService.getRooms();
    }

    @Override
    public List<String> getRoomsName() {
        List<String> nameOfRooms = new ArrayList<>();

        for (Room room : this.mService.getRooms()) {
            nameOfRooms.add(room.getName());
        }

        return nameOfRooms;
    }

    @Override
    public List<Member> getMembers() {
        return this.mService.getMembers();
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

        // Callback to the View
        this.mView.configureAndShowBottomSheet(filteredMeetings);

        return filteredMeetings;
    }

    @Override
    public List<Meeting> filterPerHours(String minDateInString, String maxDateInString) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");

        Date minDate;
        Date maxDate;

        try {
            minDate = dateFormat.parse(minDateInString);
            maxDate = dateFormat.parse(maxDateInString);

            Log.e("TAG", "filterPerHours: " + minDate.toString() + " " + maxDate.toString() );

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }
}
