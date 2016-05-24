package com.example.cc.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cc.myapp.R;
import com.example.cc.myapp.fragment.Discover_one_fragment;

/**
 * Created by caiyujie on 2016/5/8.
 */
public class SearchActivity extends ActionBarActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private static final int FOOTTYPE = 2;
    private static final int TYPE = 1;
    private String[] datas = {"111", "fdaf", "f222da"};
    private HistroyListViewAdapter hAdapter;
    private ImageView delete;
    private TextView search;
    private EditText textKey;
    private ListView history_listView;
    private String historykey = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        preferences = getSharedPreferences("searchpre", MODE_PRIVATE);
        editor = preferences.edit();
        init();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textKey.setText("");
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("searchkey", textKey.getText().toString());
                String prehiskey = preferences.getString("historykey", null);
                if (!textKey.getText().toString().equals("")) {
                    if (prehiskey == null) {
                        historykey = textKey.getText().toString();
                    } else {
                        historykey = prehiskey + "," + textKey.getText().toString();
                    }
                }
                editor.putString("historykey", historykey);
                editor.commit();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        textKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    delete.setVisibility(View.INVISIBLE);
                } else {
                    delete.setVisibility(View.VISIBLE);
                }
            }
        });

        history_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == hAdapter.getCount() - 1) {
                    hAdapter.delete(new String[]{});
                    editor.putString("historykey", null);
                    editor.commit();
                } else {
                    TextView textView = (TextView) view.findViewById(R.id.search_history_key);
                    textKey.setText(textView.getText().toString());
                }
            }
        });

    }

    public void init() {
        search = (TextView) this.findViewById(R.id.search_searchbtn);
        delete = (ImageView) this.findViewById(R.id.search_delete);
        textKey = (EditText) this.findViewById(R.id.search_key);
        history_listView = (ListView) this.findViewById(R.id.search_history_list);
        historykey = preferences.getString("historykey", null);
        if (historykey != null) {
            datas = historykey.split(",");
        }
        hAdapter = new HistroyListViewAdapter(datas, this);
        history_listView.setAdapter(hAdapter);
    }

    class HistroyListViewAdapter extends BaseAdapter {
        private String[] datas;
        private LayoutInflater inflater;

        public HistroyListViewAdapter(String[] datas, Context context) {
            this.datas = datas;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return datas.length + 1;
        }

        @Override
        public Object getItem(int position) {
            return datas[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getCount() - 1) {
                return FOOTTYPE;
            } else {
                return TYPE;
            }
        }

        public void delete(String[] list) {
            datas = list;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getItemViewType(position) == FOOTTYPE) {
                View view = inflater.inflate(R.layout.search_history_listview_item, null);
                ImageView img = (ImageView) view.findViewById(R.id.search_history_img);
                img.setVisibility(View.GONE);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView text = (TextView) view.findViewById(R.id.search_history_key);
                text.setGravity(Gravity.CENTER);
                text.setText("Çå³ýËÑË÷¼ÇÂ¼");
                text.setLayoutParams(lp);
                text.setTextColor(getResources().getColor(R.color.gray));
                text.setTextSize(15);
                return view;
            } else {
                View view = inflater.inflate(R.layout.search_history_listview_item, null);
                TextView historyKey = (TextView) view.findViewById(R.id.search_history_key);
                historyKey.setText(datas[position]);
                return view;
            }
        }
    }

}
