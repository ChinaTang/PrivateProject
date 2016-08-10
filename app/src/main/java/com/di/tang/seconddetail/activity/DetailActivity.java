package com.di.tang.seconddetail.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.data.FindDataInterface;
import com.di.tang.firstboundary.fragment.IndivListLPFragment;
import com.di.tang.privateproject.R;
import com.di.tang.seconddetail.fragment.DisplayFragment;
import com.di.tang.seconddetail.fragment.EditFragment;

/**
 * Created by tangdi on 2016/8/3.
 */
public class DetailActivity extends AppCompatActivity
        implements EditFragment.EditFragmentChange, DisplayFragment.DisplayFragmentChange,
        FindDataInterface<DetailInformation>{
    private ImageButton mBnForRetur, mBnForEdit;
    private TextView mTextViewForTitle;
    Fragment mFragment, mEditFragment;
    private FragmentManager mFragmentManager;
    private DetailInformation mDetailInformation;
    private static final String SHOW_FRAGMENT = "showfragment";
    private static final String EDIT_FRAGMENT = "deitfragment";
    public static final String INFORMATION = "information";
    public static final String ISLP = "isLP";
    @Override
    protected void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        setContentView(R.layout.second_detail_list_item);
        mDetailInformation = DataList.getmDetailInformations().
                get(getIntent().getIntExtra(ConstantInformation.DATA_UUID, 0));
        mBnForRetur = (ImageButton) findViewById(R.id.second_detail_list_item_return);
        mBnForEdit = (ImageButton) findViewById(R.id.second_detail_list_item_editebutton);
        mTextViewForTitle = (TextView) findViewById(R.id.second_detail_list_item_address);
        mTextViewForTitle.setText(mDetailInformation.getAddress());
        mFragmentManager = getSupportFragmentManager();
        mFragment = mFragmentManager.findFragmentById(R.id.seconde_detail_list_item_fragment);
        if(mFragment == null){
            Bundle bundle = new Bundle();
            bundle.putInt(INFORMATION, getIntent().getIntExtra(ConstantInformation.DATA_UUID, 0));
            if(getIntent().getBooleanExtra(IndivListLPFragment.ISLP, false)){

            }else{
                mFragment = DisplayFragment.getInstance(bundle);
                mFragmentManager.beginTransaction()
                        .add(R.id.seconde_detail_list_item_fragment, mFragment, SHOW_FRAGMENT)
                        .commit();
            }
        }
        mBnForEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(INFORMATION, getIntent().getIntExtra(ConstantInformation.DATA_UUID, 0));
                mEditFragment = EditFragment.getInstance(bundle);
                mFragmentManager.beginTransaction()
                        .remove(mFragment)
                        .add(R.id.seconde_detail_list_item_fragment, mEditFragment)
                        .commit();
            }
        });
    }

    @Override
    public void DisplayChangeButton() {
        //change Activity Title EditButton to SaveButton;
    }

    @Override
    public void EditChangeButton() {
        //change Activity Title SaveButton to EditButton
    }

    @Override
    public DetailInformation getData() {
        return mDetailInformation;
    }
}
