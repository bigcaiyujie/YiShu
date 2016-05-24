package com.example.cc.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cc.myapp.R;
import com.example.cc.myapp.activity.WriteActivity;


/**
 * Created by cc on 2016/3/30.
 */
public class Bottom_tab_fragment extends Fragment {
    private FragmentTabHost tabHost;
    private String[] texts ;
    private int tabimgs[]={R.drawable.discover, R.drawable.guanzhu,R.drawable.xiaoxi,R.drawable.wode};
    private Class fragments[]={Discover_top_tab_fragment.class,Fragment2.class, MyFragment.class,information_fragment.class};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_tab,container,false);
        texts=getActivity().getResources().getStringArray(R.array.bottom_tab_text);
        tabHost=(FragmentTabHost)view.findViewById(R.id.bottom_tabhost);
        tabHost.setup(getActivity(), getChildFragmentManager(), R.id.tabcontent);
        int count = fragments.length;
            tabHost.addTab(tabHost.newTabSpec(""+0).setIndicator(getTabView(0)), fragments[0], null);
            tabHost.addTab(tabHost.newTabSpec(""+1).setIndicator(getTabView(1)), fragments[1], null);
            tabHost.addTab(tabHost.newTabSpec(""+2).setIndicator(getTabView(10)), fragments[2], null);
            tabHost.addTab(tabHost.newTabSpec(""+2).setIndicator(getTabView(2)), fragments[3], null);
            tabHost.addTab(tabHost.newTabSpec(""+3).setIndicator(getTabView(3)), fragments[2], null);
            //tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.btn_star);

        tabHost.getTabWidget().getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



    public View getTabView(int pos){
        if(pos==10){
            View view   = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_write_tab_item,null);
            ImageView tab_img = (ImageView) view.findViewById(R.id.bottom_write_tab);
            tab_img.setImageResource(R.drawable.icon_write);
            return view;
        }else {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_tab_item_view,null);
            ImageView tab_img = (ImageView) view.findViewById(R.id.tab_item_img);
            TextView tab_text = (TextView) view.findViewById(R.id.tab_item_text);
            tab_text.setText(texts[pos]);
            tab_img.setImageResource(tabimgs[pos]);
            return view;
        }
    }
}
