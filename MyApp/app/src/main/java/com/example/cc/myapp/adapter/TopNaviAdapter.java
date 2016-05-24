package com.example.cc.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2016/4/7.
 */
public class TopNaviAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private String[] titles;
    public TopNaviAdapter(FragmentManager fm,ArrayList<Fragment> list_fragment,String[] list_Title) {
        super(fm);
        this.fragments = list_fragment;
        this.titles = list_Title;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position%titles.length];
    }

    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
