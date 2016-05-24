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
import com.example.cc.myapp.bean.Comment;

import java.util.ArrayList;

/**
 * Created by cc on 2016/4/11.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String type;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Comment> datas;

    public CommentAdapter(Context context, ArrayList<Comment> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.article_comment_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.author.setText(datas.get(position).getAuthor());
        myViewHolder.time.setText(datas.get(position).getStorey() + "Â¥¡¤" + datas.get(position).getDate().toString());
        myViewHolder.content.setText(datas.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(Comment data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    public void updateData(ArrayList<Comment> listdata){
        this.datas = listdata;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView author,time,content;
        public MyViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.article_comment_author);
            time = (TextView) itemView.findViewById(R.id.article_comment_time);
            content = (TextView) itemView.findViewById(R.id.article_comment_content);
        }
    }
}


