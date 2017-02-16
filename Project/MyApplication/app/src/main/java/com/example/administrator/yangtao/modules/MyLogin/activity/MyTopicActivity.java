package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.MyLogin.adapter.MyTopicAdapter;
import com.example.administrator.yangtao.modules.MyLogin.bean.MyTopicBean;
import com.example.administrator.yangtao.modules.MyLogin.net.LBHttpUtil;
import com.example.administrator.yangtao.modules.MyLogin.util.LoginInfoUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class MyTopicActivity extends AppCompatActivity {

    String url = "http://www.yangtaotop.com/appapi/mine/collection_list?type=6";
    ImageView item_mytopic_back;
    RecyclerView rv;
    List<MyTopicBean> data;
    MyTopicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytopic);

        initView();
        init();
        initEvent();
        initData();
    }

    private void initData() {
        LBHttpUtil.doHttpGetWithHead(url, LoginInfoUtil.getAuthToken(), new BaseCallBack() {
            @Override
            public void success(Object j) {
                String json = (String) j;
                try {
                    JSONObject object = new JSONObject(json);
                    if (object.getString("status").equals("200")) {
                        JSONObject dataObject = object.getJSONObject("data");
                        JSONArray array = dataObject.getJSONArray("result");
                        TypeToken<List<MyTopicBean>> typeToken = new TypeToken<List<MyTopicBean>>() {
                        };
                        List<MyTopicBean> list = new Gson().fromJson(array.toString(), typeToken.getType());
                        data.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {

            }
        });
    }

    private void init() {
        data = new ArrayList<>();
        adapter = new MyTopicAdapter(data, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void initEvent() {
        item_mytopic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        item_mytopic_back = (ImageView) findViewById(R.id.item_mytopic_back);
        rv = (RecyclerView) findViewById(R.id.mytopic_rv);
    }
}
