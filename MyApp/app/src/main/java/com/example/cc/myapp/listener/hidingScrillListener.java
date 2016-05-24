package com.example.cc.myapp.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.cc.myapp.R;

import java.util.zip.Inflater;

/**
 * Created by cc on 2016/3/31.
 */
public abstract class hidingScrillListener extends RecyclerView.OnScrollListener {
    private static  final float HIDE_SIZE = 10;
    private static  final float SHOW_SIZE = 70;
    private boolean controlVisible = true;
    private int mToolbarOffset=0;
    private int mToolbarHeight = 0;
    public hidingScrillListener(int mToolbarHeight){
       this.mToolbarHeight =mToolbarHeight;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        cliptoolbarOffset();
        onMoved(mToolbarOffset);
        if(mToolbarOffset<mToolbarHeight&&dy>0||(mToolbarOffset>0&&dy<0)){
            mToolbarOffset+=dy;
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        Log.d("fda","fa");
        if(newState ==RecyclerView.SCROLL_STATE_IDLE){
            if(controlVisible ==true){
                if(mToolbarOffset>HIDE_SIZE){
                    setInvisible();
                }else{
                    setVisible();
                }
            }else if((mToolbarHeight - mToolbarOffset)>SHOW_SIZE){
                setVisible();
            }else{
                setInvisible();
            }
        }
    }

    private void setVisible(){
        if(mToolbarOffset>0){
            onShow();
            mToolbarOffset=0;
        }
        controlVisible =true;
    }

    private void setInvisible(){
        if(mToolbarOffset<mToolbarHeight){
            Log.d("faaaaaa","faaaaaaaaaaaaa");
            onHide();
            mToolbarOffset = mToolbarHeight;
        }
        controlVisible = false;
    }


    private void cliptoolbarOffset(){
        if (mToolbarOffset>mToolbarHeight){
            mToolbarOffset = mToolbarHeight;
        }else if (mToolbarOffset<0){
            mToolbarOffset=0;
        }
    }

    public abstract  void onMoved(int distace);
    public abstract  void onShow();
    public abstract  void onHide();
}
