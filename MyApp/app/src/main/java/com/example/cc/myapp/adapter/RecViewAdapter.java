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
public class RecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private String type = "";
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Article> datas;
    private OnItemClickListener onItemClickListener;
    private int [] imgs={R.drawable.pp0,R.drawable.pp1,R.drawable.pp2,R.drawable.pp3,R.drawable.pp4,R.drawable.pp5,R.drawable.pp6,R.drawable.pp7,R.drawable.pp8,R.drawable.pp9};
    public interface OnItemClickListener {
        void onItemClick(View view, int positon);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecViewAdapter(Context context, ArrayList<Article> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    public RecViewAdapter(Context context, ArrayList<Article> datas,String type) {
        this.context = context;
        this.datas = datas;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if(type.equals("myfrag")){
            return TYPE_ITEM;
        }else
        if (position + 1 ==getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.discover_recview_item, viewGroup, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        } else if (viewType == TYPE_FOOTER&&!type.equals("myfrag")) {
             View footView = inflater.inflate(R.layout.discover_recview_footer_item,viewGroup,false);
            FooterHolder footerHolder = new FooterHolder(footView);
            return footerHolder;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        if(position!=datas.size()) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            if(type.equals("myfrag")){
                myViewHolder.title.setText(datas.get(position).getTitle());
                myViewHolder.author.setText(datas.get(position).getAuthor());
                myViewHolder.date.setText(datas.get(position).getDate().toString().substring(0,9));
                myViewHolder.userimg.setVisibility(View.GONE);
                myViewHolder.article_type.setVisibility(View.GONE);
                myViewHolder.readcount.setText(" ’≤ÿ"+datas.get(position).getCollectcount()+"°§∆¿¬€"+datas.get(position).getCommentcount()+"°§œ≤ª∂"+datas.get(position).getLikecount());
            }else {
                myViewHolder.title.setText(datas.get(position).getTitle());
                myViewHolder.author.setText(datas.get(position).getAuthor());
                myViewHolder.date.setText(datas.get(position).getDate().toString());
                myViewHolder.readcount.setText(" ’≤ÿ"+datas.get(position).getCollectcount()+"°§∆¿¬€"+datas.get(position).getCommentcount()+"°§œ≤ª∂"+datas.get(position).getLikecount());
                myViewHolder.articleimg.setImageResource(imgs[(int) (Math.random() * 9)]);
            }
        }
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
        if(!type.equals("myfrag")) {
            return datas.size() + 1;
        }else{
            return datas.size();
        }
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
        TextView author,title,date,readcount;
        ImageView articleimg,userimg,article_type;
        public MyViewHolder(View itemView) {
            super(itemView);
            readcount = (TextView)itemView.findViewById(R.id.discover_article_readcount);
            article_type = (ImageView)itemView.findViewById(R.id.discover_article_type);
            userimg = (ImageView)itemView.findViewById(R.id.discover_article_user_header);
            articleimg = (ImageView)itemView.findViewById(R.id.discover_article_img);
            author = (TextView) itemView.findViewById(R.id.discover_article_author);
            date = (TextView) itemView.findViewById(R.id.discover_article_time);
            title = (TextView) itemView.findViewById(R.id.discover_article_title);
        }
    }
    class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
}


