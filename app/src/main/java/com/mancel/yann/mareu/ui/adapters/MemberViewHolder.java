package com.mancel.yann.mareu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.model.Member;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 20/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.adapters
 *
 * A {@link RecyclerView.ViewHolder} subclass.
 */
public class MemberViewHolder extends RecyclerView.ViewHolder {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.item_member_iv_image)
    ImageView mImage;
    @BindView(R.id.item_member_name)
    TextView mName;
    @BindView(R.id.item_member_tv_email)
    TextView mEmail;
    @BindView(R.id.item_member_cb_check)
    CheckBox mCheckBox;

    private WeakReference<MemberAdapter.MemberAdapterListener> mListenerWeakReference;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param itemView a {@link View}
     */
    public MemberViewHolder(@NonNull View itemView) {
        super(itemView);

        // Using the ButterKnife library
        ButterKnife.bind(this, itemView);
    }

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Returns the layout value
     * @return an integer that contains the layout value
     */
    public static int getLayout() {
        return R.layout.item_member;
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.item_member_cb_check)
    public void onCheckBoxClicked() {
        // Calls the related Listener method (callback)
        MemberAdapter.MemberAdapterListener callback = this.mListenerWeakReference.get();

        if (callback != null) {
            callback.onClickCheckBox(getAdapterPosition());
        }
    }

    // UI ******************************************************************************************

    /**
     * Updates the {@link android.support.v7.widget.RecyclerView.ViewHolder}
     * @param member a {@link Member} that contains all of data to the update
     * @param callback a {@link MemberAdapter.MemberAdapterListener} for the callback methods
     */
    public void updateMember(Member member, MemberAdapter.MemberAdapterListener callback) {
        // IMAGE
        // TODO: 20/08/2019 update ImageView

        // FIRST AND LAST NAMES
        final String fullName = member.getFirstName() + " " + member.getLastName();
        this.mName.setText(fullName);

        // EMAIL
        this.mEmail.setText(member.getEmail());

        // LISTENER
        this.mListenerWeakReference = new WeakReference<>(callback);
    }
}
