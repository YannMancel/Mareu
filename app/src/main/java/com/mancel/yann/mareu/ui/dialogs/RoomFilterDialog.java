package com.mancel.yann.mareu.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mancel.yann.mareu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yann MANCEL on 06/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogs
 *
 * A simple {@link Dialog} subclass
 */
public class RoomFilterDialog extends Dialog {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.dialog_filter_room_spinner)
    Spinner mRoomSpinner;
    @BindView(R.id.dialog_filter_room_button_no)
    Button mButtonNo;
    @BindView(R.id.dialog_filter_room_button_yes)
    Button mButtonYes;

    private List<String> mRoomsName;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param context a {@link Context} of {@link RoomFilterDialog}
     * @param themeResId a integer that corresponds to the used theme
     * @param roomsName a {@link List} of {@link String}
     */
    public RoomFilterDialog(Context context, int themeResId, List<String> roomsName) {
        super(context, themeResId);
        setContentView(R.layout.dialog_filter_room);

        this.mRoomsName = roomsName;

        // Using the ButterKnife library
        ButterKnife.bind(this);

        // Configures the room spinner
        this.configureRoomSpinner();
    }

    // METHODS -------------------------------------------------------------------------------------

    public String getCurrentRoomOfSpinner() {
        return (String) this.mRoomSpinner.getSelectedItem();
    }
    public Button getButtonNo() {
        return this.mButtonNo;
    }
    public Button getButtonYes() {
        return this.mButtonYes;
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
}
