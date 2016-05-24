package com.example.cc.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cc.myapp.R;
import com.example.cc.myapp.adapter.ConcernAdapter;
import com.example.cc.myapp.adapter.RecViewAdapter;
import com.example.cc.myapp.adapter.RecViewAdapter1;
import com.example.cc.myapp.bean.Article;
import com.example.cc.myapp.bean.Constant;
import com.example.cc.myapp.bean.MyHttpUtil;
import com.example.cc.myapp.bean.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by caiyujie on 2016/5/13.
 */
public class MyfragInforActivity extends Activity {
    private String ip = Constant.IP;
    private String username;
    private String type;
    private String url;
    RecViewAdapter1 madapter1;
    RecViewAdapter madapter;
    ConcernAdapter concernAdapter;
    private ArrayList<User> datas_user = new ArrayList<>();
    private ArrayList<Article> datas = new ArrayList<>();
    private ImageButton back;
    private TextView headtext;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfrag_open_activity);
        type = getIntent().getExtras().getString("key");
        init();
        initData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyfragInforActivity.this.finish();
            }
        });
    }

    public void init() {
        back = (ImageButton) this.findViewById(R.id.myfragment_infor_back);
        headtext = (TextView) this.findViewById(R.id.myfragment_infor_headtext);
        recyclerView = (RecyclerView) this.findViewById(R.id.myfragment_infor_rclview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        headtext.setText(type);
        username = getSharedPreferences("userInfor", MODE_PRIVATE).getString("username", "");
        if (type.equals("like")) {
            url = "http://" + ip + ":8080/MyApp/servlet/GetUserLikeArticle?user=" + username + "&type=0";
        } else if (type.equals("open")) {
            url = "http://" + ip + ":8080/MyApp/servlet/GetUserLikeArticle?user=" + username + "&type=2";
        } else if (type.equals("secret")) {
            url = "http://" + ip + ":8080/MyApp/servlet/GetUserLikeArticle?user=" + username + "&type=1";
        } else if (type.equals("collect")) {
            url = "http://" + ip + ":8080/MyApp/servlet/GetUserCollectServlet?user=" + username;
        } else if (type.equals("concern")) {
            url = "http://" + ip + ":8080/MyApp/servlet/GetUserConcernServlet?user=" + username;
        }
    }

    public void initData() {
        MyHttpUtil.handler(url,new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.obj!=null) {
                    datas = (ArrayList<Article>) msg.obj;
                    if (type.equals("open") || type.equals("secret")) {
                        datas = (ArrayList<Article>) msg.obj;
                        Log.d("33334444444", "fss");
                        madapter1 = new RecViewAdapter1(MyfragInforActivity.this, datas);
                        recyclerView.setAdapter(madapter1);
                    }
                    if (type.equals("like") || type.equals("collect")) {
                        datas = (ArrayList<Article>) msg.obj;
                        madapter = new RecViewAdapter(MyfragInforActivity.this, datas, "myfrag");
                        recyclerView.setAdapter(madapter);
                    }
                    if (type.equals("concern")) {
                        datas_user = (ArrayList<User>) msg.obj;
                        concernAdapter = new ConcernAdapter(MyfragInforActivity.this, datas_user);
                        recyclerView.setAdapter(concernAdapter);
                    }
                }
            }
        });
    }
}
