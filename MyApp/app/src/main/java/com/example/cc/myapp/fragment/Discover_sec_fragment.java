package com.example.cc.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cc.myapp.R;
import com.example.cc.myapp.activity.WriteActivity;
import com.example.cc.myapp.adapter.RecViewAdapter;
import com.example.cc.myapp.bean.Article;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2016/3/30.
 */
public class Discover_sec_fragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Article> datas;
    private RecViewAdapter madapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int lastitem;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fargment, container, false);
        initData();
        init(view);

        madapter.setOnItemClickListener(new RecViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "加载了一个item", Toast.LENGTH_LONG).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastitem + 1 == madapter.getItemCount()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String datas = "dfadf";
                           // madapter.addData(datas);
                            Toast.makeText(getActivity(), "加载了一个item", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastitem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        return view;
    }

    public void init(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        madapter = new RecViewAdapter(getActivity(), datas);
        recyclerView.setAdapter(madapter);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void initData() {
        Log.d("fsffa","123123123123123123123");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("fsffa","runrunurnurnurnur");
                datas = updateArticle_doPost("http://192.168.1.101:8080/MyApp/servlet/UpDateArticleServlet");
            }
        }).start();
    }
    public ArrayList<Article> updateArticle_doPost(String url){
        ArrayList<Article> list = new ArrayList<>();
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            ObjectInputStream objectInputStream = new ObjectInputStream(conn.getInputStream());
            Object object = objectInputStream.readObject();
            list =(ArrayList<Article>)object;
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }


}


