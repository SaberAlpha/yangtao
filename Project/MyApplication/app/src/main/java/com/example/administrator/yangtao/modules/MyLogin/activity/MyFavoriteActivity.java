package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.MyLogin.adapter.MyFavoriteAdapter;
import com.example.administrator.yangtao.modules.MyLogin.bean.MyFavoriteBean;
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
public class MyFavoriteActivity extends AppCompatActivity {

    ImageView item_myfavorite_back;
    List<MyFavoriteBean> data;
    MyFavoriteAdapter adapter;
    RecyclerView myfavorite_rv;

    String url = "http://www.yangtaotop.com/appapi/mine/collection_list?type=2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfavorite);

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
                        TypeToken<List<MyFavoriteBean>> typeToken = new TypeToken<List<MyFavoriteBean>>() {
                        };
                        List<MyFavoriteBean> list = new Gson().fromJson(array.toString(), typeToken.getType());
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
        adapter = new MyFavoriteAdapter(data, this);
        myfavorite_rv.setLayoutManager(new LinearLayoutManager(this));
        myfavorite_rv.setAdapter(adapter);
    }


    private void initView() {
        item_myfavorite_back = (ImageView) findViewById(R.id.item_myfavorite_back);
        myfavorite_rv = (RecyclerView) findViewById(R.id.myfavorite_rv);
    }

    private void initEvent() {
        item_myfavorite_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(3001, i);
                finish();
            }
        });

        adapter.setOnItemClickListener(new MyFavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyFavoriteBean bean) {
                Toast.makeText(MyFavoriteActivity.this, "item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
