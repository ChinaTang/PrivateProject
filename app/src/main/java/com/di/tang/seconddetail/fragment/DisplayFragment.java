package com.di.tang.seconddetail.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.privateproject.R;
import com.di.tang.seconddetail.activity.DetailActivity;
import com.di.tang.tools.TimeTool;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tangdi on 2016/8/4.
 */
public class DisplayFragment extends Fragment {

    public interface DisplayFragmentChange{
        public void DisplayChangeButton();
    }

    private DisplayFragmentChange mDisplayFragmentChange;
    private TextView mTextView;
    private ImageView imageView;
    private DetailInformation mDetailInformation;
    private Resources mResource;
    private  StringBuilder mStringBuilder;
    private Activity mActivity;
    private Button mNoMillkBn;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        mStringBuilder = new StringBuilder();

        mDetailInformation = DataList.getmDetailInformations().
                get(getArguments().getInt(DetailActivity.INFORMATION));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.second_detail_show, parent, false);
        mTextView = (TextView)view.findViewById(R.id.second_detail_show_text);
        imageView = (ImageView)view.findViewById(R.id.second_detail_show_image);
        mNoMillkBn = (Button)view.findViewById(R.id.second_detail_show_bn);
        mNoMillkBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
        mTextView.setText(TextShow(mStringBuilder));
        //TODO imageView
        return view;
    }

    private String TextShow(StringBuilder mStringBuilder){
        mStringBuilder.delete(0, mStringBuilder.length());
        if(mDetailInformation.ishas()){
            mStringBuilder.append(TimeTool.DateToYYMMDD(mDetailInformation.getHasDate()))
                    .append(mActivity.getResources().getString(R.string.ishas_true_time))
                    .append(mDetailInformation.getNumber())
                    .append(mActivity.getResources().getString(R.string.ishas_true_number))
                    .append(ConstantInformation.MILK -
                            TimeTool.getDays(new Date(), mDetailInformation.getHasDate()))
                    .append(mActivity.getResources().getString(R.string.ishas_true_nomilk_time));
            mNoMillkBn.setVisibility(View.VISIBLE);
        }else if(mDetailInformation.isMating()){
            mStringBuilder.append(TimeTool.DateToYYMMDD(mDetailInformation.getMatingDate()))
                    .append(mActivity.getResources().getString(R.string.ismating_true_time))
                    .append(ConstantInformation.MATING -
                    TimeTool.getDays(new Date(), mDetailInformation.getMatingDate()))
                    .append(mActivity.getResources().getString(R.string.ismating_true_befor))
                    .append(mDetailInformation.getMatingTimes())
                    .append(mActivity.getResources().getString(R.string.ismating_true_times));
            mNoMillkBn.setVisibility(View.INVISIBLE);
        }else if(mDetailInformation.isPregnant()){
            mStringBuilder.append(TimeTool.DateToYYMMDD(mDetailInformation.getPregnantDate()))
                    .append(mActivity.getResources().getString(R.string.ispregnant_true_time))
                    .append(ConstantInformation.BURNDAY -
                    TimeTool.getDays(new Date(), mDetailInformation.getPregnantDate()))
                    .append(mActivity.getResources().getString(R.string.isPregnant_true_forecast));
            mNoMillkBn.setVisibility(View.INVISIBLE);
        }else{
            mStringBuilder.append(mActivity.getResources().getString(R.string.data_empty));
            mNoMillkBn.setVisibility(View.INVISIBLE);
        }
        return mStringBuilder.toString();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mActivity = (Activity)context;
        mDisplayFragmentChange.DisplayChangeButton();
    }

    public static DisplayFragment getInstance(Bundle bundle){
        DisplayFragment displayFragment = new DisplayFragment();
        displayFragment.setArguments(bundle);
        return displayFragment;
    }
}