package com.mancel.yann.mareu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.base
 *
 * A {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface FragmentListener {
        void showMessageFromFragment(final String message);
        void onClickFAB();
    }

    // FIELDS --------------------------------------------------------------------------------------

    protected FragmentListener mCallback;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public BaseFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    protected abstract int getFragmentLayout();
    protected abstract void configureDesign();

    // FRAGMENT ************************************************************************************

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Configures the callback to the parent activity
        this.configureCallbackToParentActivity(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(this.getFragmentLayout(), container, false);

        // Using the ButterKnife library
        ButterKnife.bind(this, view);

        // Configures the design
        this.configureDesign();

        return view;
    }

    @Override
    public void onDetach() {
        // To prevent memory leaks
        this.mCallback = null;

        super.onDetach();
    }

    // CALLBACK OF ACTIVITY ************************************************************************

    /**
     * Configures {@link FragmentListener}(callbacks) to the parent activity
     * @param context a {@link Context} which contains the {@link Fragment}
     */
    private void configureCallbackToParentActivity(Context context) {
        // Initializes the callback field
        try {
            this.mCallback = (FragmentListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement FragmentListener");
        }
    }
}
