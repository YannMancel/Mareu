package com.mancel.yann.mareu.ui.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mancel.yann.mareu.R;
import com.mancel.yann.mareu.base.BaseFragment;
import com.mancel.yann.mareu.ui.dialogFragments.TimePickerFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yann MANCEL on 16/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.ui.fragments
 *
 * A simple {@link BaseFragment} subclass.
 */
public class HoursFilterFragment extends BaseFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.dialog_filter_date_tv_hour_minimal)
    TextView mMinHour;
    @BindView(R.id.dialog_filter_date_button_search_hour_minimal)
    ImageButton mSearchMinHour;
    @BindView(R.id.dialog_filter_date_tv_hour_maximal)
    TextView mMaxHour;
    @BindView(R.id.dialog_filter_date_button_search_hour_maximal)
    ImageButton mSearchMaxHour;
    @BindView(R.id.dialog_filter_date_button_filter_by_hour)
    Button mFilterButton;

    public final int ID_MINIMAL_HOUR = 1;
    public final int ID_MAXIMAL_HOUR = 2;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public HoursFilterFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_filter_hours;
    }

    @Override
    protected void configureDesign() {}

    // INTERFACE FRAGMENT VIEW *********************************************************************

    @Override
    public void setTextViewById(int id, String time) {
        switch (id) {
            // TIME PICKER: MINIMAL HOURS
            case ID_MINIMAL_HOUR: {
                this.mMinHour.setText(time);
                break;
            }
            // TIME PICKER: MAXIMAL HOURS
            case ID_MAXIMAL_HOUR: {
                this.mMaxHour.setText(time);
                break;
            }
        }
    }

    // ACTIONS *************************************************************************************

    @OnClick({R.id.dialog_filter_date_button_search_hour_minimal,
              R.id.dialog_filter_date_button_search_hour_maximal,
              R.id.dialog_filter_date_button_filter_by_hour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // SEARCH BUTTON: MINIMAL HOURS
            case R.id.dialog_filter_date_button_search_hour_minimal: {
                TimePickerFragment.newInstance(ID_MINIMAL_HOUR)
                                  .show(getActivity().getSupportFragmentManager(), "TIME PICKER");
                break;
            }
            // SEARCH BUTTON: MAXIMAL HOURS
            case R.id.dialog_filter_date_button_search_hour_maximal: {
                TimePickerFragment.newInstance(ID_MAXIMAL_HOUR)
                                  .show(getActivity().getSupportFragmentManager(), "TIME PICKER");
                break;
            }
            // FILTER BUTTON
            case R.id.dialog_filter_date_button_filter_by_hour: {
                this.mCallback.onClickFromFragment(this.mMinHour.getText().toString(),
                                                   this.mMaxHour.getText().toString());
                break;
            }
        }
    }

    // INSTANCES ***********************************************************************************

    /**
     * Returns a {@link HoursFilterFragment}
     * @return a {@link HoursFilterFragment}
     */
    public static HoursFilterFragment newInstance() {
        return new HoursFilterFragment();
    }
}
