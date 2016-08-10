package com.di.tang.seconddetail.lpfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.di.tang.data.DataList;
import com.di.tang.data.DetailLPinformation;
import com.di.tang.privateproject.R;
import com.di.tang.seconddetail.activity.DetailActivity;
import com.di.tang.tools.TimeTool;

/**
 * Created by tangdi on 2016/8/10.
 */
public class DisplayLPFragment extends Fragment {

    private TextView mTextView;
    private ImageView imageView;
    private DetailLPinformation mDetailLPinformation;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        mDetailLPinformation = DataList.getmDetailLPinformation().
                get(getArguments().getInt(DetailActivity.INFORMATION));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.second_detail_show_lp, parent, false);
        mTextView = (TextView)view.findViewById(R.id.second_detail_show_lp_text);
        imageView = (ImageView)view.findViewById(R.id.second_detail_show_lp_image);
        mTextView.setText(textShow());
        return view;
    }

    private String textShow(){
        StringBuilder mStringBuilder = new StringBuilder();
        mStringBuilder.append(TimeTool.DateToYYMMDD(mDetailLPinformation.getNoMilkDay()));
        mStringBuilder.append(getActivity().getResources().getString(R.string.nomilkdetail));
        if(mDetailLPinformation.isCastrate()){
            mStringBuilder.append(TimeTool.DateToYYMMDD(mDetailLPinformation.getCastratedDay()));
            mStringBuilder.append(getActivity().getResources().getString(R.string.castratedetail));
        }else{
            mStringBuilder.append(getActivity().getResources().getString(R.string.nocastratedetail));
        }
        return mStringBuilder.toString();
    }

}
