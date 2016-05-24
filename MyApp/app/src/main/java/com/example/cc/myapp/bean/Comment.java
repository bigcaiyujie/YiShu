package com.example.cc.myapp.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by caiyujie on 2016/5/15.
 */
public class Comment implements Serializable {
    private String author;
    private int  storey;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStorey() {
        return storey;
    }

    public void setStorey(int storey) {
        this.storey = storey;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Timestamp date;
    private String content;
    private int id;

    public Comment(String author,int storey,Timestamp time,String content){
        this.author = author;
        this.storey =storey;
        this.date = time;
        this.content = content;
    }

    public Comment(String author,int  storey,String content){
        this.author = author;
        this.storey =storey;
        Timestamp date = new Timestamp(new java.util.Date().getTime());
        this.date = date;
        this.content = content;
    }
}
