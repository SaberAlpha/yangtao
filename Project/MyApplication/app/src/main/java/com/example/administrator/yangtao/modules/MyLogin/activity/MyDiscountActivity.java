package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.MyLogin.adapter.DiscountAdapter;
import com.example.administrator.yangtao.modules.MyLogin.bean.DiscountBean;
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
 * Created by Administrator on 2016/11/24 0024.
 */
public class MyDiscountActivity extends AppCompatActivity {

    TextView mydiscount_change;
    ImageView mydiscount_back;
    RecyclerView rv;
    DiscountAdapter adapter;
    List<DiscountBean> data;
    String url = "http://www.yangtaotop.com/appapi/mine/discount?ver_coupons=1&offset=20&page=1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiscount);

        initView();
        init();
        initData();
        initEvent();
    }

    private void initData() {

        LBHttpUtil.doHttpGetWithHead(url, LoginInfoUtil.getAuthToken(), new BaseCallBack() {
            @Override
            public void success(Object json) {
                String result = (String) json;
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("status");
                    if (msg.equals("200")) {
                        JSONObject dataObject = object.getJSONObject("data");
                        JSONArray array = dataObject.getJSONArray("result");
                        TypeToken<List<DiscountBean>> typeToken = new TypeToken<List<DiscountBean>>() {
                        };
                        List<DiscountBean> list = new Gson().fromJson(array.toString(), typeToken.getType());
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
        adapter = new DiscountAdapter(data, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void initView() {
        mydiscount_change = (TextView) findViewById(R.id.mydiscount_change);
        rv = (RecyclerView) findViewById(R.id.mydiscount_rv);
        mydiscount_back = (ImageView) findViewById(R.id.mydiscount_back);
    }

    private void initEvent() {
        mydiscount_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mydiscount_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}