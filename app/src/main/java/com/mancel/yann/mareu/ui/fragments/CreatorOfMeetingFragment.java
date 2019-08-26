package com.mancel.yann.mareu.ui.fragments;

import android.app.AlertDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.adapters.MemberAdapter;
import com.mancel.yann.mareu.ui.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragment;
import com.mancel.yann.mareu.utils.ShowMessage;

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

    @BindView(R.id.fragment_creator_of_meeting_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.fragment_creator_of_meeting_text_input_layout)
    TextInputLayout mTextInputTopic;
    @BindView(R.id.fragment_creator_of_meeting_b_hour)
    Button mHours;
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
        this.configureEditTextFromTextInputLayout();
        this.configureRoomSpinner();
        this.configureRecyclerView();
    }

    // INTERFACE FRAGMENT VIEW *********************************************************************

    @Override
    public void setTextById(int id, String time) {
        if (id == ID_SEARCH_HOUR) {
            this.mHours.setText(time);
        }
    }

    // INTERFACE MEMBER ADAPTER LISTENER (CALLBACKS OF RECYCLER VIEW) ******************************

    @Override
    public void onClickCheckBox(int position) {
        this.mFragmentPresenter.AddOrDeleteSelectedMember(this.mAdapter.getMember(position));
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.fragment_creator_of_meeting_b_hour)
    public void onHourButtonClicked() {
        TimePickerFragment.newInstance(ID_SEARCH_HOUR)
                .show(getActivity().getSupportFragmentManager(), "TIME PICKER");
    }

    @OnClick(R.id.fragment_creator_of_meeting_fab)
    public void onFABClicked() {
        // TEXT INPUT EDIT TEXT: Empty
        if (this.mTextInputTopic.getEditText().getText().toString().equals("")) {
            this.mTextInputTopic.setError(getString(R.string.error_text_input_layout));
            this.configureAndShowErrorMessage(getString(R.string.error_topic_meeting_creation));
        }
        // RECYCLER VIEW: No selected member
        else if (this.mFragmentPresenter.getSelectedMembers().size() == 0) {
            this.configureAndShowErrorMessage(getString(R.string.error_member_meeting_creation));
        }
        else {
            this.configureAndShowAlertDialog();
        }
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
     * Configures the room {@link Spinner}
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
     * Configures the {@link RecyclerView} with its {@link MemberAdapter}
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

    // TEXT INPUT EDIT TEXT ************************************************************************

    /**
     * Configures the {@link android.widget.EditText} from the {@link TextInputLayout}
     */
    private void configureEditTextFromTextInputLayout() {
        this.mTextInputTopic.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextInputTopic.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // ALERT DIALOG ********************************************************************************

    /**
     * Configures and show the {@link AlertDialog}
     */
    private void configureAndShowAlertDialog() {
        // Creates Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Update Alert Dialog
        builder.setTitle(getString(R.string.creation_of_meeting))
               .setMessage(getString(R.string.question_for_creation_of_meeting,
                                     this.mTextInputTopic.getEditText().getText().toString()))
               .setPositiveButton(getString(R.string.yes),
                        (dialog, which) -> {
                            final String topic = this.mFragmentPresenter.addMeeting(this.mTextInputTopic.getEditText().getText().toString(),
                                                                                    this.mHours.getText().toString(),
                                                                                    (String) this.mRoomSpinner.getSelectedItem(),
                                                                                    this.mFragmentPresenter.getSelectedMembersToString());

                            this.mCallback.onClickFromFragment(topic);
                        })
                .setNegativeButton(getString(R.string.no),
                        (dialog, which) -> {});

        // Creates and shows the AlertDialog widget
        builder.create().show();
    }

    // ERROR MESSAGES ******************************************************************************

    /**
     * Configures and show the error message
     */
    private void configureAndShowErrorMessage(final String message) {
        // IDENTIFIER W600dp
        if (getResources().getConfiguration().screenWidthDp >= getResources().getInteger(R.integer.identifier_w600dp)) {
            this.mCallback.showMessageFromFragment(message);
        }
        else {
            ShowMessage.showMessageWithSnackbar(this.mCoordinatorLayout,
                                                message);
        }
    }
}