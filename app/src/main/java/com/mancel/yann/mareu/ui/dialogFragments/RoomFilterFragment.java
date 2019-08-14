package com.mancel.yann.mareu.ui.dialogFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yann MANCEL on 14/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogFragments
 *
 * A simple {@link BaseDialogFragment} subclass
 */
public class RoomFilterFragment extends BaseDialogFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.dialog_filter_room_spinner)
    Spinner mRoomSpinner;

    private List<String> mRoomsName;

    private static final String BUNDLE_KEY_ROOMS_NAME = "BUNDLE_KEY_ROOMS_NAME";

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public RoomFilterFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Creates the View
        View view = getActivity().getLayoutInflater()
                                 .inflate(R.layout.dialog_filter_room, null);

        // Using the ButterKnife library
        ButterKnife.bind(this, view);

        // Retrieves the argument
        this.retrieveArgument();

        // Configures the room spinner
        this.configureRoomSpinner();

        // Creates Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Modifies the title
        builder.setView(view)
               .setTitle(getString(R.string.filter_room))
               .setPositiveButton(getString(R.string.yes),
                        (dialog, which) -> {this.mCallback.selectRoomForFilter(getCurrentRoomOfSpinner());})
               .setNegativeButton(getString(R.string.no),
                        (dialog, which) -> {});

        return builder.create();
    }

    // GETTERS *************************************************************************************

    private String getCurrentRoomOfSpinner() {
        return (String) this.mRoomSpinner.getSelectedItem();
    }

    // SPINNERS ************************************************************************************

    /**
     * Configures the room spinner
     */
    private void configureRoomSpinner() {
        // Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                                          android.R.layout.simple_spinner_item,
                                                          this.mRoomsName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner
        this.mRoomSpinner.setAdapter(adapter);
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link RoomFilterFragment}
     * @param roomsName a {@link List} of {@link String}
     * @return a {@link RoomFilterFragment}
     */
    public static RoomFilterFragment newInstance(List<String> roomsName) {
        RoomFilterFragment roomFilterFragment = new RoomFilterFragment();

        // Bundle
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(BUNDLE_KEY_ROOMS_NAME, new ArrayList<>(roomsName));

        roomFilterFragment.setArguments(bundle);

        return roomFilterFragment;
    }

    // ARGUMENTS ***********************************************************************************

    /**
     * Retrieves the argument
     */
    private void retrieveArgument() {
        this.mRoomsName = getArguments().getStringArrayList(BUNDLE_KEY_ROOMS_NAME);
    }
}
