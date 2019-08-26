package com.mancel.yann.mareu.ui.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.ui.adapters.MeetingAdapter;
import com.mancel.yann.mareu.utils.ShowMessage;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass which implements
 * {@link MeetingAdapter.MeetingAdapterListener}.
 */
public class MeetingFragment extends BaseFragment implements MeetingAdapter.MeetingAdapterListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_meeting_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.fragment_meeting_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_meeting_fab_add)
    FloatingActionButton mAddFab;
    @BindView(R.id.fragment_meeting_fab_filter)
    FloatingActionButton mFilterFab;
    @BindView(R.id.fragment_meeting_tv_no_data)
    TextView mTextForNoData;

    private MeetingAdapter mAdapter;
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
        this.updateRecyclerView(false);
    }

    // INTERFACE FRAGMENT VIEW *********************************************************************

    @Override
    public void updateRecyclerView(boolean isFilter) {
        // RECYCLER VIEW
        this.mAdapter.updateData(isFilter ? this.mFragmentPresenter.getFilteredMeetings() :
                                            this.mFragmentPresenter.getMeetings());

        // FILTER FAB
        this.setVisibilityOfFilterFAB(isFilter);

        // FILTER
        this.mIsFilter = isFilter;
    }

    // INTERFACE MEETING ADAPTER LISTENER (CALLBACKS OF RECYCLER VIEW) *****************************

    @Override
    public void onClickDeleteButton(int position) {
        final String message = getString(R.string.information_delete_meeting,
                                         this.mAdapter.getMeeting(position).getTopic());
        this.configureAndShowErrorMessage(message);

        this.mFragmentPresenter.deleteMeeting(this.mAdapter.getMeeting(position), this.mIsFilter);
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
                    // FILTER MODE
                    this.updateRecyclerView(false);
                }
                else {
                    // NORMAL MODE
                    this.mCallback.onClickFromFragment(null);
                }
                break;
            }
            // TABLET MODE
            case R.id.fragment_meeting_fab_filter: {
                this.updateRecyclerView(false);
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
        this.mAdapter = new MeetingAdapter(this);

        // RecyclerView
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

    // ERROR MESSAGES ******************************************************************************

    /**
     * Configures and show the error message
     */
    private void configureAndShowErrorMessage(final String message) {
        // IDENTIFIER W600dp
        if (getResources().getConfiguration().screenWidthDp >= getResources().getInteger(R.integer.identifier_w600dp)) {
            this.mCallback.showMessageFromFragment(message);
        } else {
            ShowMessage.showMessageWithSnackbar(this.mCoordinatorLayout,
                    message);
        }
    }
}