package com.example.cc.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cc.myapp.R;

/**
 * Created by cc on 2016/4/9.
 */
public class Dis_ViewPager_Fragment extends Fragment implements ViewPager.OnPageChangeListener {

    private int[] imgs;
    private ViewPager viewPager;
    private ViewGroup viewGroup;
    private ImageView[] tips;
    private ImageView[] imageViews;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_viewpager, container, false);
        init(view);
        return view;
    }

    public void init(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewGroup = (ViewGroup) view.findViewById(R.id.viewGroup);
        imgs = new int[]{R.drawable.vp1, R.drawable.vp2, R.drawable.vp3, R.drawable.vp4,};
        tips = new ImageView[imgs.length];
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
        imageViews = new ImageView[imgs.length];
        for (int i = 0; i < imageViews.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageViews[i] = imageView;
            imageView.setBackgroundResource(imgs[i]);
        }

        viewPager.setAdapter(new MyViewPagerAdapter());
        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem(0);


    }


    class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.length;
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

