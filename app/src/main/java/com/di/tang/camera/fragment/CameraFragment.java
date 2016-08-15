package com.di.tang.camera.fragment;

import android.graphics.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.di.tang.privateproject.R;

/**
 * Created by TD on 2016/8/10.
 */
public class CameraFragment extends Fragment {
    private Camera mCamera;
    private SurfaceView mSurfaceView;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.camera_layout, parent, false);
        return view;
    }
}
