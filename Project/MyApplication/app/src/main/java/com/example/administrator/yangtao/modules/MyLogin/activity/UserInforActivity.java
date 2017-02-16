package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.MyLogin.bean.UserInfoBean;
import com.example.administrator.yangtao.modules.MyLogin.net.LBHttpUtil;
import com.example.administrator.yangtao.util.XutilsManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.ex.DbException;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class UserInforActivity extends AppCompatActivity {

    Button logout;
    String logoutUrl = "http://www.yangtaotop.com/appapi/mine/user_logout";
    ImageView userinfo_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        initView();
        initEvent();
    }

    private void initView() {
        logout = (Button) findViewById(R.id.logout);
        userinfo_back = (ImageView) findViewById(R.id.userinfo_back);
    }

    private void initEvent() {
        userinfo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(2001, i);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    UserInfoBean first = XutilsManager.getInstance().getDbManager().findFirst(UserInfoBean.class);
                    String authToken = first.getAuth_token();
                    LBHttpUtil.doHttpGetWithHead(logoutUrl, authToken, new BaseCallBack() {
                        @Override
                        public void success(Object data) {
                            String result = (String) data;
                            try {
                                JSONObject object = new JSONObject(result);
                                int status = object.getInt("status");
                                if (status == 200) {
                                    Intent i = new Intent();
                                    setResult(2002, i);
                                    XutilsManager.getInstance().getDbManager().delete(UserInfoBean.class);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(int errorCode, Object data) {

                        }
                    });
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
