package com.di.tang.firstboundary.fragment;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.di.tang.asynctask.SaveDPTask;
import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.data.HaveLp;
import com.di.tang.firstboundary.activity.MainInterfaceActivity;
import com.di.tang.privateproject.R;
import com.di.tang.seconddetail.activity.DetailActivity;
import com.di.tang.tools.TimeTool;
import com.di.tang.tools.ToolAdapter;

import java.util.Date;

/**
 * Created by tangdi on 2016/8/1.
 */
public class IndivListFragment extends ListFragment implements
        MainInterfaceActivity.NotifyAcapterChange{

    private ToolAdapter<DetailInformation> mToolAdapter;
    public static boolean flag = false;

    private SaveDPTask saveDPTask;

    private ToolAdapter.SelfId mSelfId = new ToolAdapter.SelfId() {
        @Override
        public long getSelfId(int i) {
            return DataList.getmDetailInformations().get(i).getUuid().getMostSignificantBits();
        }
    };
    private ToolAdapter.SelfView mView = new ToolAdapter.SelfView() {
        @Override
        public View selfView(final int position, View view, ViewGroup viewGroup) {
            if(view == null){
                view = getActivity().getLayoutInflater().inflate(R.layout.detail_list_item, null);
            }
            DetailInformation detailInformation = DataList.getmDetailInformations().get(position);
            TextView addressText = (TextView)view.findViewById(R.id.detail_list_address);
            TextView titleText1 = (TextView)view.findViewById(R.id.detail_item_title1);
            TextView numberText = (TextView)view.findViewById(R.id.detail_list_numbre);
            TextView titleText2 = (TextView)view.findViewById(R.id.detail_item_title2);
            TextView inforText = (TextView)view.findViewById(R.id.detail_item_infor);
            TextView infordetail = (TextView)view.findViewById(R.id.detail_list_infor);
            ImageButton remove = (ImageButton)view.findViewById(R.id.list_remove);
            if(flag){
                remove.setVisibility(View.VISIBLE);
            }
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataList.getmDetailInformations().remove(position);
                    saveDPTask = new SaveDPTask(getActivity());
                    saveDPTask.execute();
                    ((ToolAdapter)getListAdapter()).notifyDataSetChanged();
                }
            });
            addressText.setText(detailInformation.getAddress());
            titleText1.setText(R.string.detail_item_address);
            if(detailInformation.isHave()){
                HaveLp haveLp = detailInformation.getmDetailLPinformation().
                        get(detailInformation.getmDetailLPinformation().size() - 1);
                titleText2.setText(R.string.detail_item_number);
                numberText.setText(String.valueOf(detailInformation.getmDetailLPinformation().
                        get(detailInformation.getmDetailLPinformation().size() - 1).getNumber()));
                inforText.setText(R.string.detail_item_prompt);
                infordetail.setText(String.valueOf(TimeTool.getDays(haveLp.getHasDate(),
                        TimeTool.getNowDate())));
            }else if(detailInformation.isMating()){
                titleText2.setText(R.string.detail_item_mating);
                numberText.setText(String.valueOf(detailInformation.getMatingTimes()));
                inforText.setText(R.string.detail_item_mating_days);
                infordetail.setText(String.valueOf(TimeTool.getDays(detailInformation.getMatingDate(),
                        TimeTool.getNowDate())));
            }else if(detailInformation.isPregnant()){
                titleText2.setText(R.string.detail_item_pregnant);
                numberText.setText(detailInformation.getPregnantDate().toString());
                inforText.setText(R.string.detail_item_pregnant_days);
                infordetail.setText(String.valueOf(TimeTool.getDays(detailInformation.getPregnantDate(),
                        TimeTool.getNowDate())));
            }
            return view;
        }
    };

    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        if(getArguments() != null){
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mToolAdapter = new ToolAdapter<DetailInformation>(mSelfId,
                DataList.getmDetailInformations(), mView);
        setListAdapter(mToolAdapter);
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

    @Override
    public void flushList() {
        ((ToolAdapter)getListAdapter()).notifyDataSetChanged();
    }
}
