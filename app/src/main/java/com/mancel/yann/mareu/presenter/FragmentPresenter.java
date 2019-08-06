package com.mancel.yann.mareu.presenter;

import com.mancel.yann.mareu.di.DI;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.model.Member;
import com.mancel.yann.mareu.model.Room;
import com.mancel.yann.mareu.service.ApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yann MANCEL on 05/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.presenter
 *
 * A class which implements {@link Presenter.FragmentPresenterInterface}.
 */
public class FragmentPresenter implements Presenter.FragmentPresenterInterface {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface FragmentView {
        /**
         * Updates the data
         */
        void UpdateDataOfRecyclerView();
    }

    // FIELDS --------------------------------------------------------------------------------------

    private ApiService mService;
    private FragmentView mView;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor with an argument {@link FragmentView}
     * @param view a {@link FragmentView} interface that contains the View
     */
    public FragmentPresenter(FragmentView view) {
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

    @Override
    public void onDetach() {
        // To prevent memory leaks
        this.mView = null;
    }
}
