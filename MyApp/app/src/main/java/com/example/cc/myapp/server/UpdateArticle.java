package com.example.cc.myapp.server;

import com.example.cc.myapp.bean.Article;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by caiyujie on 2016/5/5.
 */
public class UpdateArticle extends Thread {
    private String url;
    public UpdateArticle(String url) {
        this.url=url;
    }
    public ArrayList<Article> doPost(){
        ArrayList<Article> list = new ArrayList<>();
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            ObjectInputStream objectInputStream = new ObjectInputStream(conn.getInputStream());
            Object object = objectInputStream.readObject();
            list =(ArrayList<Article>)object;
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void run() {
        doPost();
    }
}
