package com.example.cc.myapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cc.myapp.R;
import com.example.cc.myapp.activity.MyfragInforActivity;
import com.example.cc.myapp.activity.PersonInforActivity;
import com.example.cc.myapp.bean.Article;

/**
 * Created by cc on 2016/4/7.
 */
public class MyFragment extends android.support.v4.app.Fragment {
    private RelativeLayout openarticle, secert, collect,concern, like, history, advice,me;
    private TextView username,count_open,count_secret,count_like,count_collect,count_concern;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        init(view);
        initOnClick();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userInfor", Context.MODE_PRIVATE);
        username.setText(sharedPreferences.getString("username", ""));
        count_concern.setText(""+sharedPreferences.getInt("concerncount", 0));
        count_collect.setText(""+sharedPreferences.getInt("collectcount", 0));
        count_like.setText(""+sharedPreferences.getInt("likecount",0));
        return view;
    }

    public void init(View view) {
        count_concern = (TextView)view.findViewById(R.id.concern_count);
        count_like = (TextView)view.findViewById(R.id.myfragment_like_count);
        count_open = (TextView)view.findViewById(R.id.myfragment_open_count);
        count_collect = (TextView)view.findViewById(R.id.myfragment_collect_count);
        count_secret = (TextView)view.findViewById(R.id.myfragment_secret_count);
        username = (TextView) view.findViewById(R.id.myfragment_username);
        openarticle = (RelativeLayout) view.findViewById(R.id.myinfor_openarticle);
        secert = (RelativeLayout) view.findViewById(R.id.myfragment_secert_article);
        collect = (RelativeLayout) view.findViewById(R.id.myfragment_collect);
        concern = (RelativeLayout)view.findViewById(R.id.myfragment_concern);
        like = (RelativeLayout) view.findViewById(R.id.myfragment_like);
        history = (RelativeLayout) view.findViewById(R.id.myfragment_history);
        advice = (RelativeLayout) view.findViewById(R.id.myfragment_advice);
        me = (RelativeLayout) view.findViewById(R.id.wodetouxiang);
    }
    public void initOnClick(){
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonInforActivity.class);
                startActivity(intent);
            }
        });
        openarticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyfragInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key","open");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        secert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyfragInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key","secret");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyfragInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key","collect");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyfragInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key","like");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        concern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyfragInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key","concern");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
