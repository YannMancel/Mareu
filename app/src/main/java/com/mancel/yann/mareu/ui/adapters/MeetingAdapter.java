package com.mancel.yann.mareu.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancel.yann.mareu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yann MANCEL on 22/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.adapters
 *
 * A {@link RecyclerView.Adapter<MeetingViewHolder>} subclass.
 */
public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    // INTERFACE -----------------------------------------------------------------------------------

    public interface MeetingAdapterListener {
        void onClickDeleteButton(int position);
    }

    // FIELDS --------------------------------------------------------------------------------------

    private MeetingAdapterListener mCallback;
    private List<String> mMeetings;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param callback a {@link MeetingAdapterListener} interface for the callback
     */
    public MeetingAdapter(MeetingAdapterListener callback) {
        this.mMeetings = new ArrayList<>();
        this.mCallback = callback;
    }

    // METHODS -------------------------------------------------------------------------------------

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Creates a Context to the LayoutInflater
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        // Creates the View thanks to the inflater
        View view = layoutInflater.inflate(R.layout.item_meeting, viewGroup, false);

        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder meetingViewHolder, int i) {
        meetingViewHolder.updateMeeting(this.mMeetings.get(i), this.mCallback);
    }

    @Override
    public int getItemCount() {
        return this.mMeetings.size();
    }

    // MEETINGS ************************************************************************************

    /**
     * Returns the meeting at the i position
     *
     * @param position a integer that corresponds to the i position of the {@link List}
     * @return a {@link String} at the i position of the {@link List}
     */
    private String getMeeting(final int position) {
        return this.mMeetings.get(position);
    }

    /**
     * Updates the {@link List} of {@link String} and displays it
     * @param newMeetings a {@link List} of {@link String} that corresponds to the new data
     */
    public void updateData(final List<String> newMeetings) {
        this.mMeetings = newMeetings;

        // Refreshes the RecyclerView
        notifyDataSetChanged();
    }
}
