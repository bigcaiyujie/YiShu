package com.example.cc.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cc.myapp.R;
import com.example.cc.myapp.activity.ArticleActivity;
import com.example.cc.myapp.adapter.RecViewAdapter;
import com.example.cc.myapp.bean.Article;
import com.example.cc.myapp.bean.Constant;
import com.example.cc.myapp.bean.MyHttpUtil;
import com.example.cc.myapp.bean.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by cc on 2016/3/30.
 */
public class Discover_one_fragment extends Fragment implements ViewPager.OnPageChangeListener {
    private RecyclerView recyclerView;
    private String ip = Constant.IP;
    private ArrayList<Article> datas = new ArrayList<>();
    private RecViewAdapter madapter;
    private int[] vp_imgs;
    private ViewPager viewPager;
    private ViewGroup viewGroup;
    private ImageView[] tips;
    private ImageView[] imageViews;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int lastitem;
    private LinearLayoutManager linearLayoutManager;
    private String search = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null && bundle.containsKey("searchkey")) {
            search = bundle.getString("searchkey");
        }

        View view = inflater.inflate(R.layout.discover_fargment, container, false);
        initViewPager(view);
        initData();
        init(view);
        madapter.setOnItemClickListener(new RecViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                final TextView author = (TextView) view.findViewById(R.id.discover_article_author);
                final TextView title = (TextView) view.findViewById(R.id.discover_article_title);
                MyHttpUtil.handler("http://" + ip + ":8080/MyApp/servlet/GetArticleServlet?title=" + title.getText().toString() + "&&author=" + author.getText().toString(),new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        Article article = (Article) msg.obj;
                        Intent intent = new Intent(getActivity(), ArticleActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("article", article);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                search = "";
                MyHttpUtil.<ArrayList<Article>>handler("http://" + ip + ":8080/MyApp/servlet/UpDateArticleServlet", new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        datas = (ArrayList<Article>) msg.obj;
                        madapter.updateData(datas);
                    }
                });
                Snackbar.make(container, "刷新成功" + madapter.getItemCount(), Snackbar.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastitem + 1 == madapter.getItemCount()) {
                    MyHttpUtil.<ArrayList<Article>>handler("http://" + ip + ":8080/MyApp/servlet/LoadMoreServlet?searchkey=" + search, new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            datas = (ArrayList<Article>) msg.obj;
                            if (datas != null) {
                                madapter.addData(datas);
                                Snackbar.make(container, "加载完成", Snackbar.LENGTH_SHORT).show();
                            } else {
                                Snackbar.make(container, "已加载全部", Snackbar.LENGTH_SHORT).show();
                            }
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
        MyHttpUtil.handler("http://" + ip + ":8080/MyApp/servlet/UpDateArticleServlet?searchkey=" + search,new Handler(){
            @Override
            public void handleMessage(Message msg) {
                datas = (ArrayList<Article>) msg.obj;
                madapter.updateData(datas);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setImageBack(position % imageViews.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void initViewPager(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewGroup = (ViewGroup) view.findViewById(R.id.viewGroup);
        vp_imgs = new int[]{R.drawable.vp1, R.drawable.vp2, R.drawable.vp3, R.drawable.vp4,};
        tips = new ImageView[vp_imgs.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.dot_sel);
            } else {
                tips[i].setBackgroundResource(R.drawable.dot_nor);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            viewGroup.addView(tips[i], layoutParams);
        }

        //将图片装载到数组中
        imageViews = new ImageView[vp_imgs.length];
        for (int i = 0; i < imageViews.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageViews[i] = imageView;
            imageView.setBackgroundResource(vp_imgs[i]);
        }

        viewPager.setAdapter(new MyViewPagerAdapter());
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(0);

    }

    class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return vp_imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews[position % imageViews.length], 0);
            return imageViews[position % imageViews.length];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView(imageViews[position % imageViews.length]);
        }
    }

    public void setImageBack(int pos) {
        for (int i = 0; i < tips.length; i++) {
            if (i == pos) {
                tips[i].setBackgroundResource(R.drawable.dot_sel);
            } else {
                tips[i].setBackgroundResource(R.drawable.dot_nor);
            }
        }
    }
}


