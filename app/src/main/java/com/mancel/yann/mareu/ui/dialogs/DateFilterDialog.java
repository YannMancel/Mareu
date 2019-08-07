package com.mancel.yann.mareu.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;

import com.mancel.yann.mareu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yann MANCEL on 06/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogs
 */
public class DateFilterDialog extends Dialog {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.dialog_filter_date_button_no)
    Button mButtonNo;
    @BindView(R.id.dialog_filter_date_button_yes)
    Button mButtonYes;

    private List<String> mHours;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param context a {@link Context} of {@link DateFilterDialog}
     * @param themeResId a integer that corresponds to the used theme
     * @param hours a {@link List} of {@link String}
     */
    public DateFilterDialog(Context context, int themeResId, List<String> hours) {
        super(context, themeResId);
        setContentView(R.layout.dialog_filter_date);

        this.mHours = hours;

        // Using the ButterKnife library
        ButterKnife.bind(this);
    }

    // METHODS -------------------------------------------------------------------------------------

    public Button getButtonNo() {
        return this.mButtonNo;
    }
    public Button getButtonYes() {
        return this.mButtonYes;
    }
}
