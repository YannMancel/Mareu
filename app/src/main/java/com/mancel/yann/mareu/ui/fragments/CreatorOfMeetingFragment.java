package com.mancel.yann.mareu.ui.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.adapters.MemberAdapter;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 18/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass.
 */
public class CreatorOfMeetingFragment extends BaseFragment implements MemberAdapter.MemberAdapterListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_creator_of_meeting_tiet_topic)
    TextInputEditText mTopic;
    @BindView(R.id.fragment_creator_of_meeting_tv_hour)
    TextView mHour;
    @BindView(R.id.fragment_creator_of_meeting_button_search_hour)
    ImageButton mSearchHour;
    @BindView(R.id.fragment_creator_of_meeting_spinner_room)
    Spinner mRoomSpinner;
    @BindView(R.id.fragment_creator_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_creator_of_meeting_fab)
    FloatingActionButton mFab;

    private MemberAdapter mAdapter;

    public final int ID_SEARCH_HOUR = 1;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public CreatorOfMeetingFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_creator_of_meeting;
    }

    @Override
    protected void configureDesign() {
        // Configures the room spinner
        this.configureRoomSpinner();

        // Configures the RecyclerView
        this.configureRecyclerView();
    }

    // INTERFACE FRAGMENT VIEW *********************************************************************

    @Override
    public void setTextViewById(int id, String time) {
        if (id == ID_SEARCH_HOUR) {
            this.mHour.setText(time);
        }
    }

    // INTERFACE MEMBER ADAPTER LISTENER (CALLBACKS OF RECYCLER VIEW) ******************************

    @Override
    public void onClickCheckBox(int position) {
        this.mFragmentPresenter.AddOrDeleteMember(this.mAdapter.getMember(position));
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.fragment_creator_of_meeting_button_search_hour)
    public void onHourButtonClicked() {
        TimePickerFragment.newInstance(ID_SEARCH_HOUR)
                .show(getActivity().getSupportFragmentManager(), "TIME PICKER");
    }

    @OnClick(R.id.fragment_creator_of_meeting_fab)
    public void onFABClicked() {
        final String json = this.mFragmentPresenter.createNewMeetingToString(this.mTopic.getText().toString(),
                                                                             this.mHour.getText().toString(),
                                                                             (String) this.mRoomSpinner.getSelectedItem(),
                                                                             this.mFragmentPresenter.getSelectedMembers());

        this.mCallback.onClickFromFragment(json);
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link CreatorOfMeetingFragment}
     * @return a {@link CreatorOfMeetingFragment}
     */
    public static CreatorOfMeetingFragment newInstance() {
        return new CreatorOfMeetingFragment();
    }

    // SPINNERS ************************************************************************************

    /**
     * Configures the room spinner
     */
    private void configureRoomSpinner() {
        // Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                                          android.R.layout.simple_spinner_item,
                                                          this.mFragmentPresenter.getRoomsName());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner
        this.mRoomSpinner.setAdapter(adapter);
    }

    // RECYCLER VIEW *******************************************************************************

    /**
     * Configures {@link RecyclerView} with its {@link MemberAdapter}
     */
    private void configureRecyclerView() {
        // Adapter
        this.mAdapter = new MemberAdapter(this);

        // RecyclerView
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initializes the RecyclerView
        this.mAdapter.updateData(this.mFragmentPresenter.getMembers());
    }
}
