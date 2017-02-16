package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.MyLogin.net.LBHttpUtil;
import com.example.administrator.yangtao.modules.MyLogin.util.LoginInfoUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
public class MySuggestionActivity extends AppCompatActivity {

    EditText content, phone;
    TextView count;
    Button submit;
    int maxlen = 500;
    String url = "http://www.yangtaotop.com/appapi/mine/feedback";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        initView();
        initEvent();
        postData();
    }

    private void initEvent() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words = content.getText().toString();
                if (words.length() < 20) {
                    Toast.makeText(MySuggestionActivity.this, "你输入的内容不能小于20字", Toast.LENGTH_SHORT).show();
                    return;
                }

                postData();
            }
        });

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String words = content.getText().toString();
                if (words.length() <= maxlen) {
                } else {
                    words = words.substring(0, maxlen);
                    content.setText(words);
                    content.setSelection(words.length());
                    Toast.makeText(MySuggestionActivity.this, "你输入的字数超出了限制", Toast.LENGTH_SHORT).show();
                }
                count.setText(words.length() + "/500字");
            }
        });
    }

    private void postData() {
        String words = content.getText().toString().replace(" ", "%20");
        String p = phone.getText().toString().replace(" ", "%20");

        HashMap<String, String> params = new HashMap<>();
        params.put("content", words);
        params.put("source", "603");
        params.put("contact", p);
        LBHttpUtil.doHttpPostHead(url, LoginInfoUtil.getAuthToken(), params, new BaseCallBack() {
            @Override
            public void success(Object data) {
                String result = (String) data;
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("status").equals("200")) {
                        Toast.makeText(MySuggestionActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(MySuggestionActivity.this, "提交失败!", Toast.LENGTH_SHORT).show();
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

    private void initView() {
        content = (EditText) findViewById(R.id.mysuggestion_content);
        phone = (EditText) findViewById(R.id.mysuggestion_phone);
        count = (TextView) findViewById(R.id.mysuggestion_count);
        submit = (Button) findViewById(R.id.mysuggestion_submit);
    }

}
