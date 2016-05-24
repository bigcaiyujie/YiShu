package com.example.cc.myapp.bean;

import java.io.Serializable;

/**
 * Created by cc on 2016/4/10.
 */
public class User implements Serializable{
    static final long serialVersionUID =Constant.USER_SERIAL_VERSION_UID;
    private String character;
    private int wordcount;
    private String personweb;
    private int concerncount;
    private int concernedcount;
    private int   collectcount;
    private int   myopencount;
    private int   mysecretcount;


    private int likecount;
    private String username;
    private String password;
    private int id;

    public User (int id,String useranme,String character,String personweb,int concerncount,int coucernedcount,int wordcount,int collect,int open,int secret,int likecount){
        this.id = id;
        this.username = useranme;
        this.character = character;
        this.personweb = personweb;
        this.concerncount = concerncount;
        this.concernedcount = concernedcount;
        this.wordcount =wordcount;
        this.collectcount =collect;
        this.myopencount = open;
        this.mysecretcount = secret;
        this.likecount = likecount;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getMysecretcount() {
        return mysecretcount;
    }

    public void setMysecretcount(int mysecretcount) {
        this.mysecretcount = mysecretcount;
    }

    public int getMyopencount() {
        return myopencount;
    }

    public void setMyopencount(int myopencount) {
        this.myopencount = myopencount;
    }

    public int getCollectcount() {
        return collectcount;
    }

    public void setCollectcount(int collectcount) {
        this.collectcount = collectcount;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getWordcount() {
        return wordcount;
    }

    public void setWordcount(int wordcount) {
        this.wordcount = wordcount;
    }

    public String getPersonweb() {
        return personweb;
    }

    public void setPersonweb(String personweb) {
        this.personweb = personweb;
    }

    public int getConcerncount() {
        return concerncount;
    }

    public void setConcerncount(int concerncount) {
        this.concerncount = concerncount;
    }

    public int getConcernedcount() {
        return concernedcount;
    }

    public void setConcernedcount(int concernedcount) {
        this.concernedcount = concernedcount;
    }

}
