package com.example.cc.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.cc.myapp.R;

/**
 * Created by caiyujie on 2016/5/11.
 */
public class NotLoginActivity extends ActionBarActivity{
    private LinearLayout login,regist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_login_activity);
        login = (LinearLayout)this.findViewById(R.id.nologin_login);
        regist = (LinearLayout)this.findViewById(R.id.nologin_regist);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotLoginActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotLoginActivity.this,RegistActivity.class);
                startActivity(intent);
            }
        });
    }
}
