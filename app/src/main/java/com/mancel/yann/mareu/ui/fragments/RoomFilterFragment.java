package com.mancel.yann.mareu.ui.fragments;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 19/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass.
 */
public class RoomFilterFragment extends BaseFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_filter_room_spinner)
    Spinner mRoomSpinner;
    @BindView(R.id.fragment_filter_room_button)
    Button mFilterButton;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public RoomFilterFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_filter_room;
    }

    @Override
    protected void configureDesign() {
        this.configureRoomSpinner();
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.fragment_filter_room_button)
    public void onViewClicked(View view) {
        this.mCallback.onClickFromFragment(this.getCurrentRoomOfSpinner());
    }

    // SPINNERS ************************************************************************************

    /**
     * Configures the room spinner
     */
    private void configureRoomSpinner() {
        // Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                                          android.R.layout.simple_spinner_item,
                                                          this.mFragmentPresenter.getRoomsName());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner
        this.mRoomSpinner.setAdapter(adapter);
    }

    /**
     * Returns the current room of {@link Spinner}
     * @return a {@link String} that contains the current room of {@link Spinner}
     */
    private String getCurrentRoomOfSpinner() {
        return (String) this.mRoomSpinner.getSelectedItem();
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link RoomFilterFragment}
     * @return a {@link RoomFilterFragment}
     */
    public static RoomFilterFragment newInstance() {
        return new RoomFilterFragment();
    }
}
