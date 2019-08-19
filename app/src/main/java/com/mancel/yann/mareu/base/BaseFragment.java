package com.mancel.yann.mareu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancel.yann.mareu.model.Meeting;
import com.mancel.yann.mareu.presenter.FragmentPresenter;
import com.mancel.yann.mareu.ui.View.FragmentView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Yann MANCEL on 16/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.base
 *
 * A {@link Fragment} subclass which implements {@link FragmentView}.
 */
public abstract class BaseFragment extends Fragment implements FragmentView {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface FragmentListener {
        /**
         * Shows a message thanks to a {@link String} in argument
         * @param message a {@link String} that contains the message
         */
        void showMessageFromFragment(String message);

        /**
         * Retrieve a {@link String} in argument
         * @param message a {@link String} that contains the message
         */
        void onClickFromFragment(String message);

        /**
         * Retrieve several messages in argument
         * @param messageA a {@link String} that contains the message A
         * @param messageB a {@link String} that contains the message B
         */
        void onClickFromFragment(String messageA, String messageB);
    }

    // FIELDS --------------------------------------------------------------------------------------

    protected FragmentListener mCallback;
    protected FragmentPresenter mFragmentPresenter;

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

        // Configures the Presenter
        this.configurePresenter();

        // Configures the design
        this.configureDesign();

        return view;
    }

    @Override
    public void onDetach() {
        // To prevent memory leaks
        this.mCallback = null;
        this.mFragmentPresenter.onDetach();

        super.onDetach();
    }

    // INTERFACE FRAGMENT VIEW *********************************************************************

    @Override
    public void UpdateDataOfRecyclerView(List<Meeting> meetings) {}

    @Override
    public void setTextViewById(int id, String time) {}

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

    // PRESENTER ***********************************************************************************

    /**
     * Configures the Presenter
     */
    private void configurePresenter() {
        this.mFragmentPresenter = new FragmentPresenter(this);
    }
}
