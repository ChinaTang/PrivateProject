package com.di.tang.firstboundary.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.dialog.AddressFragment;
import com.di.tang.firstboundary.fragment.IndivListFragment;
import com.di.tang.firstboundary.fragment.IndivListLPFragment;
import com.di.tang.firstboundary.fragment.WaringFragment;
import com.di.tang.privateproject.R;
import com.di.tang.tools.ReadDataToDisk;
import com.di.tang.tools.TimeTool;

import java.util.Date;

/**
 * Created by tangdi on 2016/7/29.
 */
public class MainInterfaceActivity extends FragmentActivity implements
        AddressFragment.NotifyChange, View.OnClickListener{

    public interface NotifyAcapterChange{
        public void flushList();
    }

    private static final String TAG = "MainInterfaceActivity";

    private ViewPager mViewPage;
    private FragmentManager mFragmentManager;
    private ImageButton top_bn01, top_bn02, bottom_deatil, bottom_mony,
            bottom_circle, bottom_myself;
    private Button ton_bnfinsh;

    private ReadDataToDisk readDataToDisk;

    private FragmentStatePagerAdapter mFragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimeTool.setNowDate(new Date());
        readDataToDisk = ReadDataToDisk.getInstance(MainInterfaceActivity.this);

        top_bn01 = (ImageButton)findViewById(R.id.activity_main_return);
        top_bn02 = (ImageButton)findViewById(R.id.activity_main_landing);
        bottom_deatil = (ImageButton)findViewById(R.id.activity_main_detail);
        bottom_mony = (ImageButton)findViewById(R.id.activity_main_mony);
        bottom_circle = (ImageButton)findViewById(R.id.activity_main_circle);
        bottom_myself = (ImageButton)findViewById(R.id.activity_main_myself);
        ton_bnfinsh = (Button)findViewById(R.id.activity_main_finish);
        mViewPage = (ViewPager)findViewById(R.id.activity_main_pageview);
        mFragmentManager = getSupportFragmentManager();

        top_bn02.setOnClickListener(this);
        ton_bnfinsh.setOnClickListener(this);

        mFragmentStatePagerAdapter = new FragmentStatePagerAdapter(mFragmentManager) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    if(DataList.getmDetailInformations().isEmpty()){
                        return WaringFragment.getInstacne(0);
                    }else{
                        return new IndivListFragment();
                    }
                }else if(position == 1){
                    if(DataList.getmDetailLPinformation().isEmpty()){
                        return WaringFragment.getInstacne(1);
                    }else{
                        return new IndivListLPFragment();
                    }
                }else{
                    return WaringFragment.getInstacne(1);
                }
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public int getCount() {
                return 2;
            }
        };

        mViewPage.setAdapter(mFragmentStatePagerAdapter);
        /*if(DataList.getmDetailInformations().isEmpty()){
            mViewPage.setCurrentItem(1);
        }*/


    }


    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        TimeTool.setNowDate(new Date());
    }

    @Override
    public void notifyChange() {
        int page = mViewPage.getCurrentItem();
        //mViewPage.setAdapter(mFragmentStatePagerAdapter);
        mFragmentStatePagerAdapter.notifyDataSetChanged();
    }

    private void clickOnTop_bn02(){
        final PopupWindow popupWindow = new PopupWindow();
        View view = MainInterfaceActivity.this.getLayoutInflater()
                .inflate(R.layout.popup_window, null);
        popupWindow.setHeight(300);
        popupWindow.setWidth(200);
        popupWindow.setFocusable(true);
        popupWindow.setContentView(view);
        if(!popupWindow.isShowing()){
            popupWindow.showAsDropDown(top_bn02, 0, 35);
        }else{
            popupWindow.dismiss();
        }
        Button bn_edite = (Button)view.findViewById(R.id.popup_edit);
        Button bn_add = (Button)view.findViewById(R.id.popup_add);
        bn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressFragment mAddressFragment = new AddressFragment();
                mAddressFragment.show(mFragmentManager, ConstantInformation.ADDRESS_DIALOG);
                popupWindow.dismiss();
            }
        });
        bn_edite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IndivListFragment.flag = true;
                mFragmentStatePagerAdapter.notifyDataSetChanged();
                popupWindow.dismiss();
                ton_bnfinsh.setVisibility(View.VISIBLE);
                top_bn02.setVisibility(View.GONE);
            }
        });
    }

    private void clickOnTon_bnfinsh(){
        ton_bnfinsh.setVisibility(View.GONE);
        top_bn02.setVisibility(View.VISIBLE);
        IndivListFragment.flag = false;
        int i =  mViewPage.getCurrentItem();
        //NotifyAcapterChange listFragment = (NotifyAcapterChange)mFragmentStatePagerAdapter.getItem(i);
        //listFragment.flushList();
        mFragmentStatePagerAdapter.notifyDataSetChanged();
        mViewPage.setCurrentItem(i);
    }

    @Override
    public void onClick(View view) {

            switch(view.getId()){
                case R.id.activity_main_return:
                    break;
                case R.id.activity_main_landing:
                    clickOnTop_bn02();
                    break;
                case R.id.activity_main_detail:
                    mViewPage.setCurrentItem(0);
                    break;
                case R.id.activity_main_mony:
                    mViewPage.setCurrentItem(1);
                    break;
                case R.id.activity_main_circle:
                    mViewPage.setCurrentItem(2);
                    break;
                case R.id.activity_main_myself:
                    mViewPage.setCurrentItem(3);
                    break;
                case R.id.activity_main_finish:
                    clickOnTon_bnfinsh();
                    break;
            }

    }
}
