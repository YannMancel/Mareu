package com.mancel.yann.mareu.ui.fragments;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseFragment;

/**
 * Created by Yann MANCEL on 18/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass.
 */
public class CreatorOfMeetingFragment extends BaseFragment {

    // FIELDS --------------------------------------------------------------------------------------


    // CONSTRUCTORS --------------------------------------------------------------------------------

    public CreatorOfMeetingFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_creator_of_meeting;
    }

    @Override
    protected void configureDesign() {

    }

    // ACTIONS *************************************************************************************



    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link CreatorOfMeetingFragment}
     * @return a {@link CreatorOfMeetingFragment}
     */
    public static CreatorOfMeetingFragment newInstance() {
        return new CreatorOfMeetingFragment();
    }
}
