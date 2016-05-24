package com.example.cc.myapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cc.myapp.R;
import com.example.cc.myapp.bean.Article;
import com.example.cc.myapp.bean.Constant;
import com.example.cc.myapp.bean.MyHttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class WriteActivity extends ActionBarActivity {
    private String ip = Constant.IP;
    private EditText title, content;
    private Button close,publish,secert,open;
    private static final int TYPE_SECRET = 1;
    private static final int TYPE_OPEN = 2;
    int status =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_activity);
        init();
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, ArrayList<Article>>() {
                    @Override
                    protected ArrayList<Article> doInBackground(Void... params) {
                        publishArticle();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(ArrayList<Article> articles) {
                        Intent intent = new Intent(WriteActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    protected void onPreExecute() {
                        Toast toast = new Toast(WriteActivity.this);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        ImageView imageView = new ImageView(WriteActivity.this);
                        imageView.setImageResource(R.drawable.ok1);
                        LinearLayout linearLayout = new LinearLayout(WriteActivity.this);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        imageView.setBackgroundColor(Color.TRANSPARENT);
                        linearLayout.addView(imageView);
                        toast.setView(linearLayout);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }.execute();
            }
        });

        secert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = TYPE_SECRET;
                open.setTextColor(getResources().getColor(R.color.gray));
                secert.setTextColor(getResources().getColor(R.color.white));
                secert.setBackground(getResources().getDrawable(R.drawable.write_type_bac));
                open.setBackground(getResources().getDrawable(R.drawable.write_type));
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = TYPE_OPEN;
                secert.setTextColor(getResources().getColor(R.color.gray));
                open.setTextColor(getResources().getColor(R.color.white));
                open.setBackground(getResources().getDrawable(R.drawable.write_type_bac));
                secert.setBackground(getResources().getDrawable(R.drawable.write_type));
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteActivity.this.finish();
            }
        });
    }

    public void init() {
        title = (EditText) this.findViewById(R.id.uparticle_edit_titile);
        content = (EditText) this.findViewById(R.id.uparticle_edit_content);
        close = (Button) this.findViewById(R.id.uparticle_close);
        publish = (Button) this.findViewById(R.id.uparticle_publish);
        open = (Button) this.findViewById(R.id.type_open);
        secert = (Button) this.findViewById(R.id.type_secret);
    }

    public void publishArticle() {
        String titleString, contentString;
        titleString = title.getText().toString();
        contentString = content.getText().toString();
        SharedPreferences sharedPreferences =getSharedPreferences("userInfor",MODE_PRIVATE);
        Log.d("342342343242",(new String()).valueOf(status));
        Article article = new Article(titleString, contentString, sharedPreferences.getString("username",""),status);
        MyHttpUtil.upObjectFromServer(article, "http://" + ip + ":8080/MyApp/servlet/UpArticleServlet");
    }
}