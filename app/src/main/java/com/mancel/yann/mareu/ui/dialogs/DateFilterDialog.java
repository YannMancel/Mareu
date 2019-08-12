package com.mancel.yann.mareu.ui.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.mancel.yann.mareu.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 06/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.dialogs
 *
 * A simple {@link Dialog} subclass
 */
public class DateFilterDialog extends Dialog implements TimePickerDialog.OnTimeSetListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.dialog_filter_date_tv_hour_minimal)
    TextView mMinHour;
    @BindView(R.id.dialog_filter_date_button_search_hour_minimal)
    ImageButton mButtonMinHour;
    @BindView(R.id.dialog_filter_date_tv_hour_maximal)
    TextView mMaxHour;
    @BindView(R.id.dialog_filter_date_button_search_hour_maximal)
    ImageButton mButtonMaxHour;
    @BindView(R.id.dialog_filter_date_button_no)
    Button mButtonNo;
    @BindView(R.id.dialog_filter_date_button_yes)
    Button mButtonYes;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param context    a {@link Context} of {@link DateFilterDialog}
     * @param themeResId a integer that corresponds to the used theme
     */
    public DateFilterDialog(Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_filter_date);

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

    // ACTIONS *************************************************************************************

    @OnClick({R.id.dialog_filter_date_button_search_hour_minimal,
              R.id.dialog_filter_date_button_search_hour_maximal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_filter_date_button_search_hour_minimal:
                break;
            case R.id.dialog_filter_date_button_search_hour_maximal:
                break;
        }

//        TimePickerFragment.newInstance()
//                .show(getActivity().getSupportFragmentManager(), "TIME PICKER");

        Calendar calendar = Calendar.getInstance();

        final int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        new TimePickerDialog(getContext(),
                (TimePickerDialog.OnTimeSetListener) this,
                hourOfDay,
                minute,
                DateFormat.is24HourFormat(getContext()));
    }

    // INTERFACE OF ON TIME SET LISTENER ***********************************************************

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.e("TAG", "onTimeSet: ");
    }
}
