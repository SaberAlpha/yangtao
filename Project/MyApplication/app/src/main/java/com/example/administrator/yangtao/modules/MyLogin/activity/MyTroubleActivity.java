package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.administrator.yangtao.R;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
public class MyTroubleActivity extends AppCompatActivity {

    String url = "http://www.yangtaotop.com/home/about/app_qa";
    ImageView back;
    ProgressBar pb;
    WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytrouble);

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
    }

    private void init() {
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb.setProgress(newProgress);
            }
        });

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.item_mytrouble_back);
        pb = (ProgressBar) findViewById(R.id.mytrouble_pb);
        wv = (WebView) findViewById(R.id.mytrouble_wv);
    }
}
