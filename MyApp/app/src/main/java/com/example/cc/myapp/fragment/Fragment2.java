package com.example.cc.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import com.example.cc.myapp.R;
import com.example.cc.myapp.bean.MyScrollview;

/**
 * Created by cc on 2016/3/30.
 */
public class Fragment2 extends Fragment implements MyScrollview.OnScrollListener{
    private MyScrollview myScrollview;
    private LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_activity, container, false);
       // myScrollview = (MyScrollview)view.findViewById(R.id.myscro);
        linearLayout =(LinearLayout)view.findViewById(R.id.article_top);
        myScrollview.setOnScrollListener(this);
        return view;
    }

    @Override
    public void onHide() {
        linearLayout.animate().translationY(-linearLayout.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    @Override
    public void onShow() {
        linearLayout.animate().translationY(0).setInterpolator(new AccelerateInterpolator(2));

    }
}
