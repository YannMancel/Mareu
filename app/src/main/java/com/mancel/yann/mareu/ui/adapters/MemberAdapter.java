package com.mancel.yann.mareu.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancel.yann.mareu.model.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yann MANCEL on 20/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.adapters
 *
 * A {@link RecyclerView.Adapter<MemberViewHolder>} subclass.
 */
public class MemberAdapter extends RecyclerView.Adapter<MemberViewHolder> {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface MemberAdapterListener {
        /**
         * Retrieves the position of the {@link List} and if is checked
         * @param position an integer that contains the position value
         */
        void onClickCheckBox(int position);
    }

    // FIELDS --------------------------------------------------------------------------------------

    private List<Member> mMembers;
    private MemberAdapterListener mCallback;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param callback a {@link MemberAdapterListener} interface for the callback
     */
    public MemberAdapter(MemberAdapterListener callback) {
        this.mMembers = new ArrayList<>();
        this.mCallback = callback;
    }

    // METHODS -------------------------------------------------------------------------------------

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Creates a Context to the LayoutInflater
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        // Creates the View thanks to the inflater
        View view = layoutInflater.inflate(MemberViewHolder.getLayout(), viewGroup, false);

        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder memberViewHolder, int i) {
        memberViewHolder.updateMember(this.mMembers.get(i), this.mCallback);
    }

    @Override
    public int getItemCount() {
        return this.mMembers.size();
    }

    // MEMBERS *************************************************************************************

    /**
     * Returns the member at the i position
     *
     * @param position a integer that corresponds to the i position of the {@link List}
     * @return a {@link Member} at the i position of the {@link List}
     */
    public Member getMember(final int position) {
        return this.mMembers.get(position);
    }

    // DATA ****************************************************************************************

    /**
     * Updates the {@link List} of {@link Member} and displays it
     * @param newMembers a {@link List} of {@link Member} that corresponds to the new data
     */
    public void updateData(final List<Member> newMembers) {
        this.mMembers = newMembers;

        // Refreshes the RecyclerView
        notifyDataSetChanged();
    }
}
