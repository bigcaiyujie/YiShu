package com.example.cc.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cc.myapp.R;
import com.example.cc.myapp.activity.SearchActivity;
import com.example.cc.myapp.activity.WriteActivity;
import com.example.cc.myapp.adapter.TopNaviAdapter;

import java.util.ArrayList;

/**
 * Created by cc on 2016/4/7.
 */
public class Discover_top_tab_fragment extends Fragment {
    private ViewPager viewPager;
    private TopNaviAdapter topNaviAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles;
    private View rootView;
    private ImageButton search,up;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.discover_top_tab, container, false);
            initData(rootView);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.discover_toolbar,menu);
    }

    public void initData(View view) {
        TabLayout tabLayout;
        search = (ImageButton)view.findViewById(R.id.discover_top_search);
        up = (ImageButton)view.findViewById(R.id.discover_top_up);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_findfragment_title);
        viewPager = (ViewPager) view.findViewById(R.id.vp_findfragment_content);
        fragments.add(new Discover_one_fragment());
        fragments.add(new Discover_one_fragment());
        fragments.add(new Discover_one_fragment());
        fragments.add(new Discover_one_fragment());
        fragments.add(new Discover_one_fragment());

        titles = getActivity().getResources().getStringArray(R.array.top_tab_title);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(titles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[1]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[2]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[3]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[4]));
        topNaviAdapter = new TopNaviAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(topNaviAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(topNaviAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
