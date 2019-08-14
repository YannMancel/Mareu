package com.mancel.yann.mareu.base;

import android.content.Context;
import android.support.v4.app.DialogFragment;

import com.mancel.yann.mareu.ui.dialogFragments.FilterFragmentListener;

/**
 * Created by Yann MANCEL on 14/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.base
 *
 * A simple {@link DialogFragment} subclass
 */
public abstract class BaseDialogFragment extends DialogFragment {

    // FIELDS --------------------------------------------------------------------------------------

    protected FilterFragmentListener mCallback;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public BaseDialogFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Configures the callback to the parent activity
        this.configureCallbackToParentActivity(context);
    }

    @Override
    public void onDetach() {
        // To prevent memory leaks
        this.mCallback = null;

        super.onDetach();
    }

    // CALLBACK OF ACTIVITY ************************************************************************

    /**
     * Configures {@link FilterFragmentListener}(callbacks) to the parent activity
     * @param context a {@link Context} which contains the {@link DialogFragment}
     */
    private void configureCallbackToParentActivity(Context context) {
        // Initializes the callback field
        try {
            this.mCallback = (FilterFragmentListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement FilterFragmentListener");
        }
    }
}
