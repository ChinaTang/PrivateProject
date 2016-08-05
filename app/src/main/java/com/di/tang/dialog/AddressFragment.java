package com.di.tang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.privateproject.R;

/**
 * Created by tangdi on 2016/8/3.
 */
public class AddressFragment extends DialogFragment{
    public interface NotifyChange{
        void notifyChange();
    }
    private NotifyChange notifyChange;
    private EditText mAddress;
    private Button bn01;
    private DetailInformation mDetailInformation;
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceBundle){
        View v = getActivity().getLayoutInflater().inflate(R.layout.address_dialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.address_dialog_title)
                .setView(v)
                .create();
        mAddress = (EditText)v.findViewById(R.id.address_dialog_edit);
        bn01 = (Button)v.findViewById(R.id.address_dialog_button);
        bn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDetailInformation = new DetailInformation(mAddress.getText().toString());
                mDetailInformation.setEmpty(true);
                DataList.getmDetailInformations().add(mDetailInformation);
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
    public void onDestroy(){
        super.onDestroy();
        notifyChange = null;
    }
}
