package com.example.cc.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cc.myapp.R;
import com.example.cc.myapp.bean.Article;
import com.example.cc.myapp.bean.User;
import com.example.cc.myapp.fragment.Bottom_tab_fragment;

import java.util.ArrayList;

/**
 * Created by cc on 2016/4/11.
 */
public class ConcernAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String type;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<User> datas;
    private int btn_count = 1;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(View view, int positon);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ConcernAdapter(Context context, ArrayList<User> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.myfrag_infor_concern_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.author.setText(datas.get(position).getUsername());
//        myViewHolder.count.setText(datas.get(position).getWordcount());
        myViewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((btn_count%2)==1) {
                    myViewHolder.btn.setBackground(context.getResources().getDrawable(R.drawable.concern_nor));
                    myViewHolder.btn.setText("¹Ø×¢");
                }else {
                    myViewHolder.btn.setBackground(context.getResources().getDrawable(R.drawable.concern_press));
                    myViewHolder.btn.setText("ÒÑ¹Ø×¢");
                }
                btn_count++;
            }
        });
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

    public void addData(ArrayList<User> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void updateData(ArrayList<User> listdata){
        this.datas = listdata;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView author,count;
        ImageView head;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            head = (ImageView)itemView.findViewById(R.id.myfrag_concern_head);
            count  =(TextView)itemView.findViewById(R.id.myfrag_concern_infor);
            author = (TextView)itemView.findViewById(R.id.myfrag_concern_author);
            btn = (Button)itemView.findViewById(R.id.myfrag_concern_btn);
        }
    }
}


