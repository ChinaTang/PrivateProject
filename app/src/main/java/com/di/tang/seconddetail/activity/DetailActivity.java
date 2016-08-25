package com.di.tang.seconddetail.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.data.FindDataInterface;
import com.di.tang.dialog.AddressFragment;
import com.di.tang.firstboundary.fragment.IndivListLPFragment;
import com.di.tang.privateproject.R;
import com.di.tang.seconddetail.fragment.DisplayFragment;
import com.di.tang.seconddetail.fragment.EditFragment;
import com.di.tang.seconddetail.lpfragment.DisplayLPFragment;
import com.di.tang.tools.DataSaveFile;

/**
 * Created by tangdi on 2016/8/3.
 */
public class DetailActivity extends AppCompatActivity
        implements EditFragment.EditFragmentChange, DisplayFragment.DisplayFragmentChange,
        FindDataInterface<DetailInformation>, AddressFragment.NotifyChange,
        EditFragment.ChangeActivityTitle{
    private static final String TAG = "DetailActivity";

    private ImageButton mBnForRetur, mBnForEdit;
    private Button saveButton;
    private TextView mTextViewForTitle;
    Fragment mFragment, mEditFragment;
    private FragmentManager mFragmentManager;
    private DetailInformation mDetailInformation;
    private static final String SHOW_FRAGMENT = "showfragment";
    private static final String EDIT_FRAGMENT = "deitfragment";
    public static final String INFORMATION = "information";
    public static final String ISLP = "isLP";
    private DataSaveFile dataSaveFile;
    @Override
    protected void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        setContentView(R.layout.second_detail_list_item);
        dataSaveFile = DataSaveFile.getInstance(DetailActivity.this);
        mDetailInformation = DataList.getmDetailInformations().
                get(getIntent().getIntExtra(ConstantInformation.DATA_UUID, 0));
        mBnForRetur = (ImageButton) findViewById(R.id.second_detail_list_item_return);
        mBnForEdit = (ImageButton) findViewById(R.id.second_detail_list_item_editebutton);
        mTextViewForTitle = (TextView) findViewById(R.id.second_detail_list_item_address);
        saveButton = (Button)findViewById(R.id.second_detail_list_item_savebutton);
        mTextViewForTitle.setText(mDetailInformation.getAddress());
        mFragmentManager = getSupportFragmentManager();
        mFragment = mFragmentManager.findFragmentById(R.id.seconde_detail_list_item_fragment);
        if(mFragment == null){
            Bundle bundle = new Bundle();
            bundle.putInt(INFORMATION, getIntent().getIntExtra(ConstantInformation.DATA_UUID, 0));
            if(getIntent().getIntExtra(ConstantInformation.LPORBFLAG, 0) == 0){
                mFragment = DisplayFragment.getInstance(bundle);
                mFragmentManager.beginTransaction()
                        .add(R.id.seconde_detail_list_item_fragment, mFragment, SHOW_FRAGMENT)
                        .commit();
            }else{
                mFragment = DisplayLPFragment.getInstance(bundle);
                mFragmentManager.beginTransaction()
                        .add(R.id.seconde_detail_list_item_fragment, mFragment, SHOW_FRAGMENT)
                        .commit();
            }
        }
        mBnForEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = mFragmentManager.
                        findFragmentById(R.id.seconde_detail_list_item_fragment);
                if(fragment instanceof DisplayFragment){
                    Bundle bundle = new Bundle();
                    bundle.putInt(INFORMATION, getIntent().getIntExtra(ConstantInformation.DATA_UUID, 0));
                    mEditFragment = EditFragment.getInstance(bundle);
                    mFragmentManager.beginTransaction()
                            .remove(mFragment)
                            .add(R.id.seconde_detail_list_item_fragment, mEditFragment)
                            .commit();
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putInt(INFORMATION, getIntent().getIntExtra(ConstantInformation.DATA_UUID, 0));
                    mEditFragment = EditFragment.getInstance(bundle);
                    mFragmentManager.beginTransaction()
                            .remove(mFragment)
                            .add(R.id.seconde_detail_list_item_fragment, mEditFragment)
                            .commit();
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    dataSaveFile.SaveDataDP();
                }catch (Exception e){
                    Log.e(TAG, "onClick: " + e.toString());
                }
                DetailActivity.this.finish();
            }
        });
    }

    @Override
    public void DisplayChangeButton() {
        //change Activity Title EditButton to SaveButton;
    }

    @Override
    public void EditChangeButton() {
        mBnForEdit.setVisibility(View.GONE);
        saveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public DetailInformation getData() {
        return mDetailInformation;
    }

    @Override
    public void notifyChange() {
        //TODO
    }

    @Override
    public void changeTitle() {
        mTextViewForTitle.setText(mDetailInformation.getAddress());
    }
}
