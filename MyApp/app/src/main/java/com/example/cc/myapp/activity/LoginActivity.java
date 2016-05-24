package com.example.cc.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cc.myapp.R;
import com.example.cc.myapp.bean.Constant;
import com.example.cc.myapp.bean.MyHttpUtil;
import com.example.cc.myapp.bean.User;


/**
 * Created by caiyujie on 2016/5/10.
 */
public class LoginActivity extends Activity {
    String ip = Constant.IP;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private ImageView cancel;
    private Button login, regist;
    private EditText username, password;
    private TextInputLayout namedd;
    private TextView hint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        init();
    }

    public void init() {
        cancel = (ImageView) this.findViewById(R.id.login_cancel);
        login = (Button) this.findViewById(R.id.login_login);
        regist = (Button) this.findViewById(R.id.login_regist);
        username = (EditText) this.findViewById(R.id.login_username);
        password = (EditText) this.findViewById(R.id.login_password);
        hint = (TextView) this.findViewById(R.id.login_hint);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, NotLoginActivity.class);
                startActivity(intent);
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint.setVisibility(View.GONE);
                String url = "http://" + ip + ":8080/MyApp/servlet/LoginServlet?username=" + username.getText().toString() + "&password=" + password.getText().toString();
                MyHttpUtil.handler(url, new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        User user = (User) msg.obj;
                        if (user != null) {
                            Toast.makeText(LoginActivity.this, "登入成功！", Toast.LENGTH_SHORT).show();
                            preferences = getSharedPreferences("userInfor", MODE_PRIVATE);
                            editor = preferences.edit();
                            editor.putString("username", username.getText().toString());
                            editor.putInt("collect", user.getCollectcount());
                            editor.putInt("concerncount", user.getConcerncount());
                            editor.putInt("likecount", user.getLikecount());
                            editor.putString("personweb", user.getPersonweb());
                            editor.putString("character", user.getCharacter());
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            hint.setVisibility(View.VISIBLE);
                        }
                    }
                });
//                OkHttpUtil.enqueue(url, new Callback() {
//                    @Override
//                    public void onFailure(Request request, IOException e) {
//                        android.util.Log.e("IOException", e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(Response response) throws IOException {
//                        final String result = response.body().string();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (result.equals("true")) {
//                                    Toast.makeText(LoginActivity.this, "登入成功！", Toast.LENGTH_SHORT).show();
//                                    preferences = getSharedPreferences("userInfor", MODE_PRIVATE);
//                                    editor = preferences.edit();
//                                    editor.putString("username", username.getText().toString());
//                                    editor.putString("password", password.getText().toString());
//                                    editor.commit();
//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(intent);
//                                } else {
//                                    hint.setVisibility(View.VISIBLE);
//                                }
//                            }
//                        });
//                    }
//                });
            }
        });
    }
}
