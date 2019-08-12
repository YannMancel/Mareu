package com.mancel.yann.mareu.ui.dialogFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.ui.adapters.MeetingAdapter;
import com.mancel.yann.mareu.utils.JsonTools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yann MANCEL on 06/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogFragments
 *
 * A simple {@link BottomSheetDialogFragment} subclass
 */
public class FilterModalFragment extends BottomSheetDialogFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.modal_fragment_filter_recycler_view)
    RecyclerView mRecyclerView;

    private MeetingAdapter mMeetingAdapter;
    private List<Meeting> mMeetings;

    private static final String BUNDLE_KEY_MEETINGS = "BUNDLE_KEY_MEETINGS";

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public FilterModalFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.modal_fragment_filter, container, false);

        // Using the ButterKnife library
        ButterKnife.bind(this, view);

        // Retrieves the argument
        this.retrieveArgument();

        // Configures the design
        this.configureAndShowRecyclerView();

        return view;
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link FilterModalFragment}
     * @param meetings a {@link List} of {@link Meeting}
     * @return a {@link FilterModalFragment}
     */
    public static FilterModalFragment newInstance(List<Meeting> meetings) {
        FilterModalFragment filterModalFragment = new FilterModalFragment();

        // Converts List to Json
        final String json = JsonTools.convertJavaToJson(meetings);

        // Bundle
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_MEETINGS, json);

        filterModalFragment.setArguments(bundle);

        return filterModalFragment;
    }

    // ARGUMENTS ***********************************************************************************

    /**
     * Retrieves the argument
     */
    private void retrieveArgument() {
        final String json = getArguments().getString(BUNDLE_KEY_MEETINGS);

        // Converts Json to List
        this.mMeetings = JsonTools.convertJsonToJavaList(json, Meeting.class);
    }

    // UI ******************************************************************************************

    /**
     * Configures {@link RecyclerView} with its {@link MeetingAdapter}
     */
    private void configureAndShowRecyclerView() {
        // Adapter
        this.mMeetingAdapter = new MeetingAdapter(null);

        // RecyclerView
        this.mRecyclerView.setAdapter(this.mMeetingAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Update Data
        this.mMeetingAdapter.updateData(this.mMeetings);
    }
}
