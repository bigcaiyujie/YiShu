package com.example.cc.myapp.bean;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by caiyujie on 2016/5/20.
 */
public class MyHttpUtil  {
    /*
     * 从网络中获得对象
     */
    public static  <T>T getObjectFromServer(String url){
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            ObjectInputStream objectInputStream = new ObjectInputStream(conn.getInputStream());
            Object object = objectInputStream.readObject();
            return  (T)object;
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     *上传对象
     */
    public static  <T>void  upObjectFromServer(T t,String url) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            ObjectOutputStream oos = new ObjectOutputStream(conn.getOutputStream());
            oos.writeObject(t);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  void handler(final String url, final Handler myhandler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                Log.d("3444","fss");
                msg.obj = MyHttpUtil.getObjectFromServer(url);
                myhandler.sendMessage(msg);
            }
        }).start();
    }

}
