package com.mancel.yann.mareu.ui.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.di.DI;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.service.MeetingApiService;
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
    @BindView(R.id.fragment_meeting_fab)
    FloatingActionButton mFab;

    private MeetingAdapter mMeetingAdapter;
    private MeetingApiService mService;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public MeetingFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_meeting;
    }

    @Override
    protected void configureDesign() {
        // Configures the service
        this.configureService();

        // Configures the RecyclerView
        this.configureRecyclerView();

        // Updates the list of the RecyclerView
        this.UpdateListOfRecyclerView();
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.fragment_meeting_fab)
    public void onFABClicked() {
        this.mCallback.onClickFAB();
    }

    // CALLBACKS OF RECYCLER VIEW ******************************************************************

    @Override
    public void onClickDeleteButton(int position) {
        this.mCallback.showMessageFromFragment("Position " + position);
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link MeetingFragment}
     * @return a {@link MeetingFragment}
     */
    public static MeetingFragment newInstance() {
        return new MeetingFragment();
    }

    // SERVICES ************************************************************************************

    /**
     * Configures the service
     */
    private void configureService() {
        this.mService = DI.getMeetingApiService();
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

    // UPDATE OF ITEM IN THE RECYCLER VIEW *********************************************************

    /**
     * Updates the list of the {@link RecyclerView}
     */
    private void UpdateListOfRecyclerView() {
        List<Meeting> meetings = this.mService.getMeetings();
        this.mMeetingAdapter.updateData(meetings);
    }
}