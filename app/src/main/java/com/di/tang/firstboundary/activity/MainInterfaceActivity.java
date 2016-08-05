package com.di.tang.firstboundary.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.di.tang.data.DataList;
import com.di.tang.dialog.AddressFragment;
import com.di.tang.firstboundary.fragment.IndivListFragment;
import com.di.tang.firstboundary.fragment.WaringFragment;
import com.di.tang.privateproject.R;
import com.di.tang.tools.TimeTool;

import java.util.Date;

/**
 * Created by tangdi on 2016/7/29.
 */
public class MainInterfaceActivity extends FragmentActivity implements
        AddressFragment.NotifyChange{

    private WaringFragment mWaringFragment;
    private ViewPager mViewPage;
    private FragmentManager mFragmentManager;
    private ImageButton top_bn01, top_bn02, bottom_deatil, bottom_mony,
            bottom_circle, bottom_myself;

    private FragmentStatePagerAdapter mFragmentStatePagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimeTool.setNowDate(new Date());

        top_bn01 = (ImageButton)findViewById(R.id.activity_main_return);
        top_bn02 = (ImageButton)findViewById(R.id.activity_main_landing);
        bottom_deatil = (ImageButton)findViewById(R.id.activity_main_detail);
        bottom_mony = (ImageButton)findViewById(R.id.activity_main_mony);
        bottom_circle = (ImageButton)findViewById(R.id.activity_main_circle);
        bottom_myself = (ImageButton)findViewById(R.id.activity_main_myself);
        mWaringFragment = new WaringFragment();
        mViewPage = (ViewPager)findViewById(R.id.activity_main_pageview);
        mFragmentManager = getSupportFragmentManager();

        mFragmentStatePagerAdapter = new FragmentStatePagerAdapter(mFragmentManager) {
            @Override
            public Fragment getItem(int position) {
                if(DataList.getmDetailInformations().isEmpty()){
                    mWaringFragment.setFlag(0);
                    return mWaringFragment;
                }else{
                    return new IndivListFragment();
                }
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public int getCount() {
                return 1;
            }
        };

        mViewPage.setAdapter(mFragmentStatePagerAdapter);
        /*if(DataList.getmDetailInformations().isEmpty()){
            mViewPage.setCurrentItem(1);
        }*/


    }

    private void buttonClick(int id){
        switch(id){
            case R.id.activity_main_return:
                break;
            case R.id.activity_main_landing:
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
        }
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
}
