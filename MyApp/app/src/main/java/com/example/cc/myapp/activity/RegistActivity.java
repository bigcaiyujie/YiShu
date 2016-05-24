package com.example.cc.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cc.myapp.R;
import com.example.cc.myapp.bean.Constant;
import com.example.cc.myapp.bean.OkHttpUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by caiyujie on 2016/5/10.
 */
public class RegistActivity extends Activity {
    private String ip = Constant.IP;
    private TextView namehint, psdhint;
    private ImageView cancel;
    private Button regist;
    private EditText username, password, password_again;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registactivity);
        init();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this, NotLoginActivity.class);
                startActivity(intent);
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namehint.setVisibility(View.INVISIBLE);
                psdhint.setVisibility(View.INVISIBLE);
                String psd = password.getText().toString();
                String psd2 = password_again.getText().toString();
                if (psd.equals(psd2)) {
                    String url = "http://" + ip + ":8080/MyApp/servlet/RegistServlet?username=" + username.getText().toString() + "&password=" + psd;
                    OkHttpUtil.enqueue(url, new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            final String result = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (result.equals("true")) {
                                        Toast.makeText(RegistActivity.this, "×¢²á³É¹¦£¡", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        namehint.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        }
                    });
                } else {
                    psdhint.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void init() {
        cancel = (ImageView) this.findViewById(R.id.regist_cancel);
        regist = (Button) this.findViewById(R.id.regeist_regist);
        username = (EditText) this.findViewById(R.id.regeist_username);
        password = (EditText) this.findViewById(R.id.regist_password);
        password_again = (EditText) this.findViewById(R.id.regist_password_again);
        namehint = (TextView) this.findViewById(R.id.regist_hint);
        psdhint = (TextView) this.findViewById(R.id.regist_hint1);
    }
}
