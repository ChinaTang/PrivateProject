package com.di.tang.seconddetail.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.privateproject.R;
import com.di.tang.seconddetail.activity.DetailActivity;

/**
 * Created by tangdi on 2016/8/4.
 */
public class EditFragment extends Fragment{

    public interface EditFragmentChange{
        void EditChangeButton();
    }

    public interface ChangeActivityTitle{
        void changeTitle();
    }
    private EditFragmentChange mEditFragmentChange;
    private EditText mEditText;
    private ViewPager mViewPage;
    private DetailInformation mDetailInformation;
    private FragmentManager mFragmentManager;
    private Bundle bundle;
    private ChangeActivityTitle changeActivityTitle;

    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        mDetailInformation = DataList.getmDetailInformations().
                get(getArguments().getInt(DetailActivity.INFORMATION));
        bundle = new Bundle();
        bundle.putInt(DetailActivity.INFORMATION, getArguments().getInt(DetailActivity.INFORMATION));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.second_detail_edit, parent, false);
        mEditFragmentChange.EditChangeButton();
        mEditText = (EditText)view.findViewById(R.id.second_detail_edit_input);
        mViewPage = (ViewPager)view.findViewById(R.id.second_detail_deit_viewpager);
        mFragmentManager = getActivity().getSupportFragmentManager();
        mEditText.setText(mDetailInformation.getAddress());
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    mDetailInformation.setAddress("");
                }else{
                    mDetailInformation.setAddress(editable.toString());
                }
                changeActivityTitle.changeTitle();
            }
        });
        mViewPage.setAdapter(new FragmentStatePagerAdapter(mFragmentManager) {
            @Override
            public Fragment getItem(int position) {

                if(position == 0){
                    return IsHasFragment.getInstance(bundle);
                }else if(position == 1){
                    return IsMatingFragment.getInstance(bundle);
                }else{
                    return IsPregnantFragment.getInstance(bundle);
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        return view;
    }

    public static EditFragment getInstance(Bundle bundle){
        EditFragment editFragment = new EditFragment();
        editFragment.setArguments(bundle);
        return editFragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mEditFragmentChange = (EditFragmentChange)context;
        changeActivityTitle = (ChangeActivityTitle)context;
    }
}
