package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.yangtao.R;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
public class MySettingActivity extends AppCompatActivity {

    ImageView back;
    RelativeLayout cache, suggestion, evaluation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysetting);

        initView();
        init();
        initEvent();
    }

    private void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MySettingActivity.this, MySuggestionActivity.class));
            }
        });

        evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAppDetail("com.tencent.mm", null);
            }
        });
    }

    private void init() {
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.mysetting_back);
        cache = (RelativeLayout) findViewById(R.id.mysetting_cache);
        suggestion = (RelativeLayout) findViewById(R.id.mysetting_suggestion);
        evaluation = (RelativeLayout) findViewById(R.id.mysetting_evaluation);
    }

    /**
     * 启动到app详情界面
     *
     * @param appPkg    App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MySettingActivity.this, "你的手机没有安装应用市场", Toast.LENGTH_SHORT).show();
        }
    }

}
