package com.di.tang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.data.DetailLPinformation;
import com.di.tang.privateproject.R;
import com.di.tang.tools.DataSaveFile;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by tangdi on 2016/8/3.
 */
public class AddressFragment extends DialogFragment{
    public interface NotifyChange{
        void notifyChange();
    }

    public interface IsNoMilk{
        void setIsHave();
    }
    public static final String _INFORTMATION = "islp";
    public static final String _LPNUMBER = "index";
    private NotifyChange notifyChange;
    private EditText mAddress;
    private Button bn01;
    private DetailInformation mDetailInformation;
    private DetailLPinformation mDetailLPinformation;
    private boolean isLp;
    private DataSaveFile dataSaveFile;
    private static final String TAG = "AddressFragment";
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceBundle){
        Bundle bundle = getArguments();

        dataSaveFile = DataSaveFile.getInstance(getActivity());

        if(bundle == null){
            isLp = false;
        }else{
            isLp = bundle.getBoolean(_INFORTMATION);
        }
        View v = getActivity().getLayoutInflater().inflate(R.layout.address_dialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.address_dialog_title)
                .setView(v)
                .create();
        mAddress = (EditText)v.findViewById(R.id.address_dialog_edit);
        bn01 = (Button)v.findViewById(R.id.address_dialog_button);
        bn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(isLp){
                    mDetailLPinformation = new DetailLPinformation();
                    mDetailLPinformation.setEmpty(true);
                    mDetailLPinformation.setNumber(getArguments().getInt(_LPNUMBER));
                    mDetailLPinformation.setAddress(mAddress.getText().toString());
                    DataList.getmDetailLPinformation().add(mDetailLPinformation);
                    IsNoMilk isNoMilk = (IsNoMilk)getTargetFragment();
                    isNoMilk.setIsHave();
                    try{
                        dataSaveFile.SaveDataDP();
                        dataSaveFile.SvaeDataLP();
                        Log.d(TAG, "onPause: save File Success");
                    }catch(Exception e){
                        Log.e(TAG, "onPause: " + "Save File Failure" + e.toString());
                    }
                }else{
                    mDetailInformation = new DetailInformation(mAddress.getText().toString());
                    mDetailInformation.setEmpty(true);
                    DataList.getmDetailInformations().add(mDetailInformation);
                    try{
                        dataSaveFile.SaveDataDP();
                        dataSaveFile.SvaeDataLP();
                        Log.d(TAG, "onPause: save File Success");
                    }catch(Exception e){
                        Log.e(TAG, "onPause: " + "Save File Failure" + e.toString());
                    }
                }
                notifyChange.notifyChange();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(AddressFragment.this)
                        .commit();
                //insert to data base
            }
        });
        mAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    bn01.setEnabled(false);
                }else{
                    bn01.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    bn01.setEnabled(false);
                }else{
                    bn01.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return alertDialog;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        notifyChange = (NotifyChange)context;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        notifyChange = null;
    }

    public static AddressFragment getInstance(Bundle bundle){
        AddressFragment mAddressFragment = new AddressFragment();
        mAddressFragment.setArguments(bundle);
        return mAddressFragment;
    }

}
