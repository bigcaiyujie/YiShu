package com.example.cc.myapp.bean;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * Created by caiyujie on 2016/4/20.
 */
public class MyScrollview extends ScrollView {

    private static final int HIDE_SIZE = 20;
    private int scrollDistance = 0;
    private boolean visible = true;
    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public interface OnScrollListener {
        public void onHide();

        public void onShow();
    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollListener != null) {
            if (scrollDistance > HIDE_SIZE && visible) {
                onScrollListener.onHide();
                visible = false;
                scrollDistance = 0;
            } else if (scrollDistance < -HIDE_SIZE && !visible) {
                onScrollListener.onShow();
                visible = true;
                scrollDistance = 0;
            }

            if ((visible && (y - oldy) > 0) || (!visible && (y - oldy) < 0)) {
                scrollDistance += (y - oldy);
            }

            Log.d("scrolldistance",scrollDistance+"");
        }
    }

    @Override
    protected int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public MyScrollview(Context context) {
        super(context);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
