package com.mancel.yann.mareu.ui.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.presenter.MeetingFragmentPresenter;
import com.mancel.yann.mareu.ui.adapters.MeetingAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass which implements
 * {@link MeetingFragmentPresenter.FragmentView} and {@link MeetingAdapter.MeetingAdapterListener}.
 */
public class MeetingFragment extends BaseFragment implements MeetingFragmentPresenter.FragmentView,
                                                             MeetingAdapter.MeetingAdapterListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_meeting_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_meeting_fab)
    FloatingActionButton mFab;

    private MeetingAdapter mMeetingAdapter;
    private MeetingFragmentPresenter mPresenter;

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

    // CALLBACKS OF RECYCLER VIEW ******************************************************************

    @Override
    public void onClickDeleteButton(int position) {
        this.mCallback.showMessageFromFragment("Position " + position);
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
        this.mCallback.onClickFAB();
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
        this.mPresenter = new MeetingFragmentPresenter(this);
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
}