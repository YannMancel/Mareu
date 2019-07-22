package com.mancel.yann.mareu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mancel.yann.mareu.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 22/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.adapters
 *
 * A {@link RecyclerView.ViewHolder} subclass.
 */
public class MeetingViewHolder extends RecyclerView.ViewHolder {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.item_meeting_iv_image)
    ImageView mImage;
    @BindView(R.id.item_meeting_tv_topic_hour_room)
    TextView mTopicHourRoom;
    @BindView(R.id.item_meeting_tv_participants)
    TextView mParticipants;
    @BindView(R.id.item_meeting_iv_delete)
    ImageButton mDeleteButton;

    private WeakReference<MeetingAdapter.MeetingAdapterListener> mListenerWeakReference;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public MeetingViewHolder(@NonNull View itemView) {
        super(itemView);

        // Using the ButterKnife library
        ButterKnife.bind(this, itemView);
    }

    // METHODS -------------------------------------------------------------------------------------

    @OnClick(R.id.item_meeting_iv_delete)
    public void onDeleteButtonClicked() {
        // Calls the related Listener method (callback)
        MeetingAdapter.MeetingAdapterListener callback = this.mListenerWeakReference.get();

        if (callback != null) {
            callback.onClickDeleteButton(getAdapterPosition());
        }
    }

    // UI ******************************************************************************************

    /**
     * Updates the {@link android.support.v7.widget.RecyclerView.ViewHolder}
     * @param test a {@link String} that contains all of data to the update
     * @param callback a {@link MeetingAdapter.MeetingAdapterListener} for the callback methods
     */
    public void updateMeeting(String test, MeetingAdapter.MeetingAdapterListener callback) {
        // IMAGE
        // TODO: 22/07/2019 update the ImageView

        // TEXT VIEW
        // TODO: 22/07/2019 update the TextViews

        // LISTENER
        this.mListenerWeakReference = new WeakReference<>(callback);
    }
}
