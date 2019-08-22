package com.mancel.yann.mareu.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancel.yann.mareu.model.Meeting;

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
        /**
         * Retrieves the position of the {@link List}
         * @param position an integer that contains the position value
         */
        void onClickDeleteButton(int position);

        /**
         * Displays something when the {@link List} is empty
         * @param isEmpty a boolean
         */
        void EmptyList(boolean isEmpty);
    }

    // FIELDS --------------------------------------------------------------------------------------

    private MeetingAdapterListener mCallback;
    private List<Meeting> mMeetings;
    private boolean mIsNormalMode;

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
        View view = layoutInflater.inflate(MeetingViewHolder.getLayout(), viewGroup, false);

        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder meetingViewHolder, int i) {
        meetingViewHolder.updateMeeting(this.mMeetings.get(i), this.mCallback, this.mIsNormalMode);
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
     * @return a {@link Meeting} at the i position of the {@link List}
     */
    public Meeting getMeeting(final int position) {
        return this.mMeetings.get(position);
    }

    // DATA ****************************************************************************************

    /**
     * Updates the {@link List} of {@link Meeting} and displays it
     * @param newMeetings a {@link List} of {@link Meeting} that corresponds to the new data
     * @param isNormalMode a boolean [True: Normal mode] and [False: Filter mode]
     */
    public void updateData(final List<Meeting> newMeetings, boolean isNormalMode) {
        this.mMeetings = newMeetings;
        this.mIsNormalMode = isNormalMode;

        // Refreshes the RecyclerView
        notifyDataSetChanged();

        // Checks if the List of Meeting is empty (callback method)
        this.mCallback.EmptyList(this.mMeetings.isEmpty());
    }
}
