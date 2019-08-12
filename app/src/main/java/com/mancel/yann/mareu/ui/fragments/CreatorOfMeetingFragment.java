package com.mancel.yann.mareu.ui.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.presenter.FragmentPresenter;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 18/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass which implement {@link FragmentPresenter.FragmentView}
 */
public class CreatorOfMeetingFragment extends BaseFragment implements FragmentPresenter.FragmentView {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_creator_of_meeting_tiet_topic)
    TextInputEditText mTopic;
    @BindView(R.id.fragment_creator_of_meeting_tv_hour)
    TextView mHour;
    @BindView(R.id.fragment_creator_of_meeting_button_search_hour)
    ImageButton mSearchHour;
    @BindView(R.id.fragment_creator_of_meeting_spinner_room)
    Spinner mRoomSpinner;
    @BindView(R.id.fragment_creator_of_meeting_fab)
    FloatingActionButton mFab;

    private FragmentPresenter mPresenter;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public CreatorOfMeetingFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_creator_of_meeting;
    }

    @Override
    protected void configureDesign() {
        // Configures the Presenter
        this.configurePresenter();

        // Configures the room spinner
        this.configureRoomSpinner();
    }

    // INTERFACE VIEW ******************************************************************************

    @Override
    public void UpdateDataOfRecyclerView() {}

    @Override
    public void configureAndShowBottomSheet(List<Meeting> meetings) {}

    @Override
    public void updateHourOfTextView(String time) {
        this.mHour.setText(time);
    }

    // FRAGMENT ************************************************************************************

    @Override
    public void onDetach() {
        // To prevent memory leaks
        this.mPresenter.onDetach();

        super.onDetach();
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.fragment_creator_of_meeting_button_search_hour)
    public void onHourButtonClicked() {
        TimePickerFragment.newInstance()
                .show(getActivity().getSupportFragmentManager(), "TIME PICKER");
    }

    @OnClick(R.id.fragment_creator_of_meeting_fab)
    public void onFABClicked() {
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link CreatorOfMeetingFragment}
     * @return a {@link CreatorOfMeetingFragment}
     */
    public static CreatorOfMeetingFragment newInstance() {
        return new CreatorOfMeetingFragment();
    }

    // PRESENTER ***********************************************************************************

    /**
     * Configures the Presenter
     */
    private void configurePresenter() {
        this.mPresenter = new FragmentPresenter(this);
    }

    // SPINNERS ************************************************************************************

    /**
     * Configures the room spinner
     */
    private void configureRoomSpinner() {
        // Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                                          android.R.layout.simple_spinner_item,
                                                          this.mPresenter.getRoomsName());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner
        this.mRoomSpinner.setAdapter(adapter);
    }
}
