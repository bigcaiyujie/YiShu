package com.example.cc.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.cc.myapp.R;
import com.example.cc.myapp.adapter.PersonAdapter;

import java.util.HashMap;

/**
 * Created by caiyujie on 2016/5/16.
 */
public class PersonInforActivity extends ActionBarActivity {
    private Button back,ok;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private LinearLayoutManager linearLayoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personinforactivity);
        init();
        personAdapter = new PersonAdapter(this);
        recyclerView.setAdapter(personAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonInforActivity.this.finish();
            }
        });
    }

    public void init(){
        back = (Button)this.findViewById(R.id.personinfor_cancel);
        ok = (Button)this.findViewById(R.id.personinfor_ok);
        recyclerView = (RecyclerView)this.findViewById(R.id.personinfor_listview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
