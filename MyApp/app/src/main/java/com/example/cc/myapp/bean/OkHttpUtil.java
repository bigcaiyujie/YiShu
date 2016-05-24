package com.example.cc.myapp.bean;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


/**
 * Created by caiyujie on 2016/5/20.
 */
public class OkHttpUtil {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static Response execute(Request request) throws IOException{
        return okHttpClient.newCall(request).execute();
    }

    /*
     * 开启异步访问
     * @param url
     * @param callback
     */
    public static void enqueue(String url ,Callback callback){
        Request request = new Request.Builder().url(url).build();
         okHttpClient.newCall(request).enqueue(callback);
    }

    /*
     *开启一部访问，不在意结果
     * @param url
     */
    public static  void  enqueue(String url){
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    public static String getStringFromServer(String url) throws  IOException{
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if(response.isSuccessful()){
            return response.body().string();
        }else {
            throw new IOException("Unexpected code " + response);
        }
    }

}
