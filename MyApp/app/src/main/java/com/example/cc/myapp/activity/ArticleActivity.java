package com.example.cc.myapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.example.cc.myapp.R;
import com.example.cc.myapp.adapter.CommentAdapter;
import com.example.cc.myapp.bean.Article;
import com.example.cc.myapp.bean.Comment;
import com.example.cc.myapp.bean.Constant;
import com.example.cc.myapp.bean.MyScrollview;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ArticleActivity extends Activity{
    private String ip = Constant.IP;
    private AlertDialog.Builder dialogbuilder;
    private Dialog dialog;
    private LinearLayout linearLayout,writecom,bottom;
    private ImageButton back,more;
    private TextView author,time,title,wordcount,content;
    private Button like,collect,concern;
    private String authorname,hostname,titleString;
    private RecyclerView recyclerView ;
    private int article_id;
    private CommentAdapter commentAdapter;
    private EditText comment_editText;
    private ArrayList<Comment> comments = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleActivity.this.finish();
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String url = "http://"+ip+":8080/MyApp/servlet/ArticleLikeServlet?host_user=" + hostname+"&article_id=" + String.valueOf(article_id);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        do_post(url);
                    }
                }).start();
            }
        });

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "http://"+ip+":8080/MyApp/servlet/CollectServlet?host_user=" + hostname+"&article_id=" + String.valueOf(article_id);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        do_post(url);
                    }
                }).start();
            }
        });

        concern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        do_post("http://"+ip+":8080/MyApp/servlet/ConcernUserServlet?host_user="+hostname+"&ccd_user="+authorname);
                    }
                }).start();

                concern.setText("已关注");
            }
        });

        writecom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(ArticleActivity.this);
                RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.comment_dialog, null);

                dialogbuilder = new AlertDialog.Builder(ArticleActivity.this);
                dialog = dialogbuilder.create();
                dialog.show();
                dialog.getWindow().setContentView(relativeLayout);
                Button button = (Button) relativeLayout.findViewById(R.id.comment_dialog_btn);
                comment_editText = (EditText) relativeLayout.findViewById(R.id.comment_dialog_edit);
                comment_editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        comment_editText.setTextColor(getResources().getColor(R.color.black));
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (comment_editText.getText().toString() == null || comment_editText.getText().toString().equals("")) {
                            comment_editText.setTextColor(getResources().getColor(R.color.red));
                            comment_editText.setText("你还没有输入内容呢！");
                        } else {
                            new UpComment().execute("http://" + ip + ":8080/MyApp/servlet/UpCommentServlet?username=" + author.getText() + "&title=" + title.getText());
                        }
                    }
                });
            }
        });
    }

    public void init(){
        comments.add(new Comment("ads",1,"fafda"));
        comments.add(new Comment("ads",2,"fafda"));
        commentAdapter = new CommentAdapter(this,comments);
        recyclerView = (RecyclerView)this.findViewById(R.id.article_comment);
        recyclerView.setAdapter(commentAdapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        bottom = (LinearLayout)this.findViewById(R.id.article_bottom);
        linearLayout =(LinearLayout)this.findViewById(R.id.article_top);
        writecom =(LinearLayout)this.findViewById(R.id.article_writecomment);
        back=(ImageButton)this.findViewById(R.id.articleactivity_back);
        concern=(Button)this.findViewById(R.id.article_concern);
        like=(Button)this.findViewById(R.id.article_like);
        collect=(Button)this.findViewById(R.id.article_collect);
        more = (ImageButton)this.findViewById(R.id.articleactivity_more);
        author = (TextView)this.findViewById(R.id.articleactivity_author);
        time =(TextView)this.findViewById(R.id.articleactivity_time);
        title = (TextView)this.findViewById(R.id.articleactivity_title);
        content = (TextView)this.findViewById(R.id.articleactivity_word_count);
        Article article = (Article)getIntent().getExtras().getSerializable("article");
        author.setText(article.getAuthor());
        time.setText(article.getDate().toString());
        title.setText(article.getTitle());
        content.setText(article.getContent());
        authorname = article.getAuthor();
        titleString = article.getTitle();
        article_id = article.getId();
        hostname = getSharedPreferences("userInfor",MODE_PRIVATE).getString("username", "");
    }


    public void do_post(String url){
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }catch (Exception e){
            e.printStackTrace();
    }
    }

    class UpComment extends AsyncTask<String,Void,Comment>{
        @Override
        protected Comment doInBackground(String... params) {
            Comment comment = new Comment(getSharedPreferences("userInfor",MODE_PRIVATE).getString("username", null),commentAdapter.getItemCount()+1,comment_editText.getText().toString());
            try {
                URL httpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setReadTimeout(5000);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(conn.getOutputStream());
                objectOutputStream.writeObject(comment);
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }catch (Exception e){
                e.printStackTrace();
            }
            return comment;
        }

        @Override
        protected void onPostExecute(Comment comment) {
            SVProgressHUD.showWithStatus(ArticleActivity.this, "评论成功");
            SVProgressHUD.dismiss(ArticleActivity.this);
            commentAdapter.addData(comment);
        }

        @Override
        protected void onPreExecute() {
            dialog.dismiss();
            SVProgressHUD.showWithStatus(ArticleActivity.this, "发布中");
        }
    }
}
