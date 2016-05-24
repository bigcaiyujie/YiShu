package com.example.cc.myapp.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by cc on 2016/4/10.
 */
public class Article implements Serializable {
    private static final long serialVersionUID =Constant.ARTICLE_SERIAL_VERSION_UID;
    private String title;
    private String author;
    private Timestamp date;
    private String content;
    private int id;
    private int commentcount;
    private int likecount;
    private int status;
    private int wordcount;
    private int collectcount;
    public Article(){};
    public Article(String title, String content, String authuor,int status) {
        this.title = title;
        this.content = content;
        this.author = authuor;
        Timestamp date = new Timestamp(new java.util.Date().getTime());
        this.date = date;
        this.status=status;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public int getCollectcount() {
        return collectcount;
    }

    public void setCollectcount(int collectcount) {
        this.collectcount = collectcount;
    }
    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getWordcount() {
        return wordcount;
    }

    public void setWordcount(int wordcount) {
        this.wordcount = wordcount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return status;
    }

    public void setType(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
