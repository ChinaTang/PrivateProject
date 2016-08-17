package com.di.tang.firstboundary.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.privateproject.R;
import com.di.tang.seconddetail.activity.DetailActivity;
import com.di.tang.tools.TimeTool;
import com.di.tang.tools.ToolAdapter;

import java.util.Date;

/**
 * Created by tangdi on 2016/8/1.
 */
public class IndivListFragment extends ListFragment{

    private ToolAdapter<DetailInformation> mToolAdapter;

    private ToolAdapter.SelfId mSelfId = new ToolAdapter.SelfId() {
        @Override
        public long getSelfId(int i) {
            return DataList.getmDetailInformations().get(i).getUuid().getMostSignificantBits();
        }
    };
    private ToolAdapter.SelfView mView = new ToolAdapter.SelfView() {
        @Override
        public View selfView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                view = getActivity().getLayoutInflater().inflate(R.layout.detail_list_item, null);
            }
            DetailInformation detailInformation = DataList.getmDetailInformations().get(i);
            TextView addressText = (TextView)view.findViewById(R.id.detail_list_address);
            TextView titleText1 = (TextView)view.findViewById(R.id.detail_item_title1);
            TextView numberText = (TextView)view.findViewById(R.id.detail_list_numbre);
            TextView titleText2 = (TextView)view.findViewById(R.id.detail_item_title2);
            TextView inforText = (TextView)view.findViewById(R.id.detail_item_infor);
            TextView infordetail = (TextView)view.findViewById(R.id.detail_list_infor);

            addressText.setText(detailInformation.getAddress());
            titleText1.setText(R.string.detail_item_address);
            if(detailInformation.ishas()){
                titleText2.setText(R.string.detail_item_number);
                numberText.setText(String.valueOf(detailInformation.getNumber()));
                inforText.setText(R.string.detail_item_prompt);
                inforText.setText(String.valueOf(TimeTool.getDays(TimeTool.getNowDate(),
                        detailInformation.getHasDate())));
            }else if(detailInformation.isMating()){
                titleText2.setText(R.string.detail_item_mating);
                numberText.setText(String.valueOf(detailInformation.getMatingTimes()));
                inforText.setText(R.string.detail_item_mating_days);
                inforText.setText(String.valueOf(TimeTool.getDays(TimeTool.getNowDate(),
                        detailInformation.getMatingDate())));
            }else if(detailInformation.isPregnant()){
                titleText2.setText(R.string.detail_item_pregnant);
                numberText.setText(detailInformation.getPregnantDate().toString());
                inforText.setText(R.string.detail_item_pregnant_days);
                inforText.setText(String.valueOf(TimeTool.getDays(TimeTool.getNowDate(),
                        detailInformation.getPregnantDate())));
            }
            return view;
        }
    };

    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        mToolAdapter = new ToolAdapter<DetailInformation>(mSelfId,
                DataList.getmDetailInformations(), mView);
        setListAdapter(mToolAdapter);
        if(getArguments() != null){
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(ConstantInformation.LPORBFLAG, 0);
        intent.putExtra(ConstantInformation.DATA_UUID, position);
        getActivity().startActivity(intent);
    }

    public static IndivListFragment getInstance(Bundle bundle){
        IndivListFragment indivListFragment = new IndivListFragment();
        indivListFragment.setArguments(bundle);
        return indivListFragment;
    }

    @Override
    public void onResume(){
        super.onResume();
        TimeTool.setNowDate(new Date());
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
