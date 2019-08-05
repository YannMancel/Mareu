package com.mancel.yann.mareu.presenter;

import com.mancel.yann.mareu.di.DI;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.service.MeetingApiService;

import java.util.List;

/**
 * Created by Yann MANCEL on 05/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.presenter
 *
 * A class which implements {@link Presenter.FragmentPresenter}.
 */
public class MeetingFragmentPresenter implements Presenter.FragmentPresenter {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface FragmentView {
        /**
         * Updates the data
         */
        void UpdateDataOfRecyclerView();
    }

    // FIELDS --------------------------------------------------------------------------------------

    private MeetingApiService mService;
    private FragmentView mView;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor with an argument {@link FragmentView}
     * @param view a {@link FragmentView} interface that contains the View
     */
    public MeetingFragmentPresenter(FragmentView view) {
        this.mService = DI.getMeetingApiService();
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
    public void onDetach() {
        // To prevent memory leaks
        this.mView = null;
    }
}
