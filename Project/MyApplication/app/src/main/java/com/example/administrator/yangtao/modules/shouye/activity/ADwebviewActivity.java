package com.example.administrator.yangtao.modules.shouye.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.administrator.yangtao.R;

public class ADwebviewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adwebview);

        String uri = getIntent().getStringExtra("uri");
        ((TextView)findViewById(R.id.shouye_title)).setText(getIntent().getStringExtra("title"));
        findViewById(R.id.shouye_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.shouye_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        webView = (WebView) findViewById(R.id.shouye_web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(uri);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

        });

    }
}
