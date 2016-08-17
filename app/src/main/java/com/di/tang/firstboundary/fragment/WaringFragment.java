package com.di.tang.firstboundary.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.dialog.AddressFragment;
import com.di.tang.logger.Logger;
import com.di.tang.privateproject.R;

/**
 * Created by tangdi on 2016/8/2.
 */
public class WaringFragment extends Fragment {

    private static final String TAG = "WaringFragment";

    private static int flag;

    private Button button;

    private FragmentManager mFragmentManager;

    private AddressFragment mAddressFragment;

    private Bundle mBundle;
    @Override
    public void onCreate(Bundle saveInstanceBundel){
        super.onCreate(saveInstanceBundel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_warning, parent, false);
        button = (Button)view.findViewById(R.id.fragment_warning_button);
        if(getArguments().getInt(ConstantInformation.WARING_FLAG) == 1){
            button.setText(R.string.waring_no);
            button.setEnabled(false);
            return view;
        }
        mFragmentManager = getActivity().getSupportFragmentManager();
        mAddressFragment = new AddressFragment();
        mBundle = getArguments();
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    //Create a dialogFragment for insert data to mDetailInformations;
                    Logger.d(TAG, "flag = " + flag);
                    mAddressFragment.show(mFragmentManager, ConstantInformation.ADDRESS_DIALOG);
                }
            }
        });
        return view;
    }

    public static WaringFragment getInstacne(int key){
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantInformation.WARING_FLAG, key);
        WaringFragment mWaringFragment = new WaringFragment();
        mWaringFragment.setArguments(bundle);
        return mWaringFragment;
    }

}
