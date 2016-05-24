package com.example.cc.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cc.myapp.R;
import com.example.cc.myapp.bean.Article;

import java.util.ArrayList;

/**
 * Created by cc on 2016/4/11.
 */
public class RecViewAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String type;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Article> datas;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(View view, int positon);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecViewAdapter1(Context context, ArrayList<Article> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = inflater.inflate(R.layout.myfrag_infor_item, viewGroup, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.title.setText(datas.get(position).getTitle());
            myViewHolder.head.setText(datas.get(position).getDate().toString());
            myViewHolder.count.setText("ÆÀÂÛ"+datas.get(position).getCommentcount()+"¡¤Ï²»¶¡¤"+datas.get(position).getLikecount()+"×ÖÊý"+datas.get(position).getWordcount());
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(ArrayList<Article> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void updateData(ArrayList<Article> listdata){
        this.datas = listdata;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView head,title,count;
        public MyViewHolder(View itemView) {
            super(itemView);
            head = (TextView) itemView.findViewById(R.id.open_time);
            count = (TextView) itemView.findViewById(R.id.open_count);
            title = (TextView) itemView.findViewById(R.id.open_title);
        }
    }
}


