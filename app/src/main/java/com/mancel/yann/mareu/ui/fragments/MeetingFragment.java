package com.mancel.yann.mareu.ui.fragments;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.presenter.FragmentPresenter;
import com.mancel.yann.mareu.ui.View;
import com.mancel.yann.mareu.ui.adapters.MeetingAdapter;
import com.mancel.yann.mareu.ui.dialogFragments.FilterModalFragment;
import com.mancel.yann.mareu.ui.dialogs.DateFilterDialog;
import com.mancel.yann.mareu.ui.dialogs.RoomFilterDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass which implements
 * {@link View.FragmentView} and {@link MeetingAdapter.MeetingAdapterListener}.
 */
public class MeetingFragment extends BaseFragment implements View.FragmentView,
                                                             MeetingAdapter.MeetingAdapterListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_meeting_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_meeting_fab)
    FloatingActionButton mFab;

    private MeetingAdapter mMeetingAdapter;
    private FragmentPresenter mPresenter;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public MeetingFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_meeting;
    }

    @Override
    protected void configureDesign() {
        // Configures the Presenter
        this.configurePresenter();

        // Configures the RecyclerView
        this.configureRecyclerView();

        // Updates the list of the RecyclerView
        this.UpdateDataOfRecyclerView();
    }

    // INTERFACE VIEW ******************************************************************************

    @Override
    public void UpdateDataOfRecyclerView() {
        this.mMeetingAdapter.updateData(this.mPresenter.getMeetings());
    }

    @Override
    public void configureAndShowBottomSheet(List<Meeting> meetings) {
        FilterModalFragment.newInstance(meetings)
                           .show(getActivity().getSupportFragmentManager(), "MODAL");
    }

    @Override
    public void updateHourOfTextView(String time) {}

    // CALLBACKS OF RECYCLER VIEW ******************************************************************

    @Override
    public void onClickDeleteButton(int position) {
        // Displays message
        this.mCallback.showMessageFromFragment("Delete " + this.mMeetingAdapter.getMeeting(position).getTopic());

        this.mPresenter.deleteMeeting(this.mMeetingAdapter.getMeeting(position));
    }

    // FRAGMENT ************************************************************************************

    @Override
    public void onDetach() {
        // To prevent memory leaks
        this.mPresenter.onDetach();

        super.onDetach();
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.fragment_meeting_fab)
    public void onFABClicked() {
        this.mCallback.onClickFromFragment(null);
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link MeetingFragment}
     * @return a {@link MeetingFragment}
     */
    public static MeetingFragment newInstance() {
        return new MeetingFragment();
    }

    // PRESENTER ***********************************************************************************

    /**
     * Configures the Presenter
     */
    private void configurePresenter() {
        this.mPresenter = new FragmentPresenter(this);
    }

    // UI ******************************************************************************************

    /**
     * Configures {@link RecyclerView} with its {@link MeetingAdapter}
     */
    private void configureRecyclerView() {
        // Adapter
        this.mMeetingAdapter = new MeetingAdapter(this);

        // RecyclerView
        this.mRecyclerView.setAdapter(this.mMeetingAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    // FILTERS *************************************************************************************

    /**
     * Filter per date
     */
    public void filterPerDate() {
        DateFilterDialog filterDialog = new DateFilterDialog(getContext(),
                                                             R.style.Theme_AppCompat_DayNight);

        // Button NO: Closes dialog
        filterDialog.getButtonNo().setOnClickListener((v) -> {filterDialog.dismiss();});

        // Button Yes: Retrieves the date range and closes dialog
        filterDialog.getButtonYes().setOnClickListener((v) -> {this.mPresenter.filterPerHours("08:00", "09:30");
                                                               filterDialog.dismiss();});

        // Creates and shows
        filterDialog.show();
    }

    /**
     * Filter per room
     */
    public void filterPerRoom() {
        RoomFilterDialog filterDialog = new RoomFilterDialog(getContext(),
                                                             R.style.Theme_AppCompat_DayNight,
                                                             this.mPresenter.getRoomsName());

        // Button NO: Closes dialog
        filterDialog.getButtonNo().setOnClickListener((v) -> {filterDialog.dismiss();});

        // Button Yes: Retrieves the current room and closes dialog
        filterDialog.getButtonYes().setOnClickListener((v) -> {this.mPresenter.filterPerRoom(filterDialog.getCurrentRoomOfSpinner());
                                                               filterDialog.dismiss();});

        // Creates and shows
        filterDialog.show();
    }

    // NEW MEETINGS ********************************************************************************

    /**
     * Adds a new {@link Meeting}
     * @param meetingFromString a {@link String} that contains the {@link Meeting}
     */
    public void addMeeting(String meetingFromString) {
        this.mPresenter.addMeeting(meetingFromString);
    }

    // FLOATING ACTION BUTTON **********************************************************************

    /**
     * Sets the visibility of {@link FloatingActionButton} thanks to the boolean in argument
     * @param isVisible a boolean
     */
    public void setVisibilityOfFAB(boolean isVisible) {
        if (isVisible) {
            this.mFab.show();
        } else {
            this.mFab.hide();
        }
    }
}