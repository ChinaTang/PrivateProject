package com.di.tang.tools;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by tangdi on 2016/8/1.
 */
public class ToolAdapter<T> extends BaseAdapter{
    private ArrayList<T> mArrayList;
    public interface SelfId{
        long getSelfId(int i);
    }
    public interface SelfView{
        View selfView(int i, View view, ViewGroup viewGroup);
    }
    private SelfId mSelfId;
    private SelfView mSelfView;
    public ToolAdapter(SelfId selfId, ArrayList<T> arrayList, SelfView selfView){
        mSelfId = selfId;
        mArrayList = arrayList;
        mSelfView = selfView;
    }
    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return mArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mSelfId.getSelfId(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mSelfView.selfView(i, view, viewGroup);
        return view;
    }
}
