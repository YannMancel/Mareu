package com.mancel.yann.mareu.ui.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 * <p>
 * A simple {@link BaseFragment} subclass.
 */
public class MeetingFragment extends BaseFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_meeting_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_meeting_fab)
    FloatingActionButton mFab;

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

    // UI ******************************************************************************************

    /**
     * Configures {@link RecyclerView}
     */
    private void configureRecyclerView() {

    }
}