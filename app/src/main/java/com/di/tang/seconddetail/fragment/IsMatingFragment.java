package com.di.tang.seconddetail.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.di.tang.data.DetailInformation;
import com.di.tang.data.FindDataInterface;
import com.di.tang.dialog.DateDialogFragment;
import com.di.tang.privateproject.R;
import com.di.tang.tools.TimeTool;

import java.util.Date;

/**
 * Created by tangdi on 2016/8/5.
 */
public class IsMatingFragment extends Fragment {
    private EditText mEditText;
    private Button mButton;
    private TextView textView;
    private DetailInformation mDetailInformation;
    private DateDialogFragment mDateDialogFragment;
    public static final int QUEST_DATA = 0;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.ishas_fragment, parent, false);
        mEditText = (EditText)view.findViewById(R.id.ishas_fragment_edit);
        mButton = (Button)view.findViewById(R.id.ishas_fragment_button);
        textView = (TextView)view.findViewById(R.id.ishas_fragment_title);
        textView.setText(getActivity().getResources().getText(R.string.ismating));
        mEditText.setText(String.valueOf(mDetailInformation.getMatingTimes()));

        if(mDetailInformation.getHasDate() == null){
            mButton.setText(TimeTool.DateToYYMMDD(new Date()));
        }else{
            mButton.setText(TimeTool.DateToYYMMDD(mDetailInformation.getMatingDate()));
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDetailInformation.getHasDate() == null){
                    mDateDialogFragment = DateDialogFragment.getInstance(new Date());
                }else{
                    mDateDialogFragment = DateDialogFragment.getInstance(mDetailInformation.getMatingDate());
                }
                mDateDialogFragment.show(getActivity().getSupportFragmentManager(),
                        DateDialogFragment.TAG);
                mDateDialogFragment.setTargetFragment(IsMatingFragment.this, QUEST_DATA);
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    mDetailInformation.setNumber(0);
                }else{
                    mDetailInformation.setNumber(Integer.valueOf(editable.toString()));
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mDetailInformation = ((FindDataInterface<DetailInformation>)context).getData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != AppCompatActivity.RESULT_OK){
            return;
        }
        if(requestCode == QUEST_DATA){
            Date date = (Date)data.getSerializableExtra(DateDialogFragment.RETURN_INFORMATION);
            mDetailInformation.setMatingDate(date);
            mButton.setText(TimeTool.DateToYYMMDD(mDetailInformation.getMatingDate()));
        }
    }
}
