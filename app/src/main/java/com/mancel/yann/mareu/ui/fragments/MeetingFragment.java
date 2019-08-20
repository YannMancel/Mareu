package com.mancel.yann.mareu.ui.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.ui.adapters.MeetingAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass which implements {@link MeetingAdapter.MeetingAdapterListener}.
 */
public class MeetingFragment extends BaseFragment implements MeetingAdapter.MeetingAdapterListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_meeting_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_meeting_fab_add)
    FloatingActionButton mAddFab;
    @BindView(R.id.fragment_meeting_fab_filter)
    FloatingActionButton mFilterFab;
    @BindView(R.id.fragment_meeting_tv_no_data)
    TextView mTextForNoData;

    private MeetingAdapter mMeetingAdapter;
    private boolean mIsFilter;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public MeetingFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_meeting;
    }

    @Override
    protected void configureDesign() {
        // Configures the RecyclerView
        this.configureRecyclerView();

        // Updates the list of the RecyclerView
        this.updateDataOfRecyclerView(this.mFragmentPresenter.getMeetings(), false);
    }

    // INTERFACE FRAGMENT VIEW *********************************************************************

    @Override
    public void updateDataOfRecyclerView(List<Meeting> meetings, boolean isFilter) {
        // RECYCLER VIEW
        this.mMeetingAdapter.updateData(meetings);

        // FILTER FAB
        this.setVisibilityOfFilterFAB(isFilter);

        // FILTER
        this.mIsFilter = isFilter;
    }

    // CALLBACKS OF RECYCLER VIEW ******************************************************************

    @Override
    public void onClickDeleteButton(int position) {
        // Displays message
        this.mCallback.showMessageFromFragment("Delete " + this.mMeetingAdapter.getMeeting(position).getTopic());

        this.mFragmentPresenter.deleteMeeting(this.mMeetingAdapter.getMeeting(position));
    }

    @Override
    public void EmptyList(boolean isEmpty) {
        this.mTextForNoData.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }

    // ACTIONS *************************************************************************************

    @OnClick({R.id.fragment_meeting_fab_add,
              R.id.fragment_meeting_fab_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // PHONE MODE
            case R.id.fragment_meeting_fab_add: {
                if (this.mIsFilter) {
                    this.updateDataOfRecyclerView(this.mFragmentPresenter.getMeetings(), false);
                } else {
                    this.mCallback.onClickFromFragment(null);
                }
                break;
            }
            // TABLET MODE
            case R.id.fragment_meeting_fab_filter: {
                this.updateDataOfRecyclerView(this.mFragmentPresenter.getMeetings(), false);
                break;
            }
        }
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link MeetingFragment}
     *
     * @return a {@link MeetingFragment}
     */
    public static MeetingFragment newInstance() {
        return new MeetingFragment();
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
     * Filter per hours
     *
     * @param minHour a {@link String} that contains the minimal hour
     * @param maxHour a {@link String} that contains the maximal hour
     */
    public void filterPerHours(String minHour, String maxHour) {
        this.mFragmentPresenter.filterPerHours(minHour, maxHour);
    }

    /**
     * Filter per room
     *
     * @param roomName a {@link String} tht corresponds to the room filter
     */
    public void filterPerRoom(String roomName) {
        this.mFragmentPresenter.filterPerRoom(roomName);
    }

    // NEW MEETINGS ********************************************************************************

    /**
     * Adds a new {@link Meeting}
     *
     * @param meetingFromString a {@link String} that contains the {@link Meeting}
     */
    public void addMeeting(String meetingFromString) {
        this.mFragmentPresenter.addMeeting(meetingFromString);
    }

    // FLOATING ACTION BUTTON **********************************************************************

    /**
     * Sets the visibility of the Add {@link FloatingActionButton} thanks to the boolean in argument
     * @param isVisible a boolean
     */
    public void setVisibilityOfAddFAB(boolean isVisible) {
        if (isVisible) {
            this.mAddFab.show();
        } else {
            this.mAddFab.hide();
        }
    }

    /**
     * Sets the visibility of the Filter {@link FloatingActionButton} thanks to the boolean in argument
     * @param isVisible a boolean
     */
    public void setVisibilityOfFilterFAB(boolean isVisible) {
        if (isVisible) {
            this.mFilterFab.show();
        } else {
            this.mFilterFab.hide();
        }
    }
}