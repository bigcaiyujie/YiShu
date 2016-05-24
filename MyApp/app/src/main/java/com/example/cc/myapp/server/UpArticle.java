package com.example.cc.myapp.server;

import android.util.Log;

import com.example.cc.myapp.bean.Article;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by caiyujie on 2016/4/25.
 */
public class UpArticle extends Thread {
    private Article article;
    private String  url;

    public UpArticle(Article article,String url){
        this.article = article;
        this.url = url;
    }

    public String doPost(){
        try {
            URL httpUrl = new URL(url);
            Log.d("dddddd", article.getTitle());
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            ObjectOutputStream oos = new ObjectOutputStream(conn.getOutputStream());
            String content = "name="+article.getTitle();
            oos.writeObject(article);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb =  new StringBuffer();
            String str;
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            return new String(sb);

        }catch (IOException e){
            e.printStackTrace();
        }
       return null;
    }
    @Override
    public void run() {
        String a = doPost();
    }

}
