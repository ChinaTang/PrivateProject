package com.di.tang.firstboundary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.data.DetailLPinformation;
import com.di.tang.privateproject.R;
import com.di.tang.seconddetail.activity.DetailActivity;
import com.di.tang.tools.TimeTool;
import com.di.tang.tools.ToolAdapter;

/**
 * Created by tangdi on 2016/8/10.
 */
public class IndivListLPFragment extends ListFragment {
    private ToolAdapter<DetailLPinformation> mToolAdater;

    private ToolAdapter.SelfId mSelfId = new ToolAdapter.SelfId() {
        @Override
        public long getSelfId(int i) {
            return DataList.getmDetailLPinformation().get(i).getUuid().getMostSignificantBits();
        }
    };

    private ToolAdapter.SelfView mSelfView = new ToolAdapter.SelfView() {
        @Override
        public View selfView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                view = getActivity().getLayoutInflater().inflate(R.layout.detail_list_item_lp, null);
            }
            DetailLPinformation detailLPinformation = DataList.getmDetailLPinformation().get(i);
            TextView addressText = (TextView)view.findViewById(R.id.detail_list_address);
            TextView titleText1 = (TextView)view.findViewById(R.id.detail_item_title1);
            TextView noMilkDay = (TextView)view.findViewById(R.id.detail_list_numbre);
            TextView titleText2 = (TextView)view.findViewById(R.id.detail_item_title2);
            TextView inforText = (TextView)view.findViewById(R.id.detail_item_infor);
            TextView infordetail = (TextView)view.findViewById(R.id.detail_list_infor);
            addressText.setText(detailLPinformation.getAddress());
            titleText2.setText(R.string.nomilkDay);
            noMilkDay.setText(TimeTool.DateToYYMMDD(detailLPinformation.getNoMilkDay()));
            inforText.setText(R.string.castrateDay);
            if(detailLPinformation.isCastrate()){
                infordetail.setText(R.string.nocastrate);
            }else{
                infordetail.setText(TimeTool.DateToYYMMDD(detailLPinformation.getCastratedDay()));
            }
            return view;
        }
    };
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        mToolAdater = new ToolAdapter<DetailLPinformation>(mSelfId,
                DataList.getmDetailLPinformation(), mSelfView);
        setListAdapter(mToolAdater);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(ConstantInformation.DATA_UUID, position);
        intent.putExtra(ConstantInformation.LPORBFLAG, 1);
        getActivity().startActivity(intent);
    }
}
