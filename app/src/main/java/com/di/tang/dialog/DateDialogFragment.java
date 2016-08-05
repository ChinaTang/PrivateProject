package com.di.tang.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.di.tang.privateproject.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by tangdi on 2016/8/5.
 */
public class DateDialogFragment extends DialogFragment {
    public static final String GET_INFORMATION = "information";
    public static final String RETURN_INFORMATION = "return_information";
    public static final String TAG = "DateDialogFragment";
    private Date date;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        date = (Date)getArguments().getSerializable(GET_INFORMATION);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        View v = getActivity().getLayoutInflater().inflate(R.layout.date_dialog_fragment, null);
        DatePicker datePicker = (DatePicker)v.findViewById(R.id.date_dialog_fragment_datepicker);
        Button mEnsure = (Button)v.findViewById(R.id.date_dialog_fragment_bn);
        mEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResult(AppCompatActivity.RESULT_OK);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(DateDialogFragment.this)
                        .commit();
            }
        });
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                date = new GregorianCalendar(i, i1, i2).getTime();
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();

    }

    public static DateDialogFragment getInstance(Date date){
        Bundle bundle = new Bundle();
        bundle.putSerializable(GET_INFORMATION, date);
        DateDialogFragment mFragment = new DateDialogFragment();
        mFragment.setArguments(bundle);
        return mFragment;
    }

    private void sendResult(int resultCode){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(RETURN_INFORMATION, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
