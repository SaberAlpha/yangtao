package com.example.administrator.yangtao.activity;

import android.view.View;

import com.example.administrator.yangtao.R;

public class SearchActivity extends BaseActivity {


    @Override
    protected void loadData() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.search_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void requestNetData() {

    }
}
