package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.MyLogin.bean.UserInfoBean;
import com.example.administrator.yangtao.modules.MyLogin.util.StringUtil;
import com.example.administrator.yangtao.net.HttpUtil;
import com.example.administrator.yangtao.util.XutilsManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.ex.DbException;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class LoginActivity extends AppCompatActivity {


    String passwordLoginUrl = "http://www.yangtaotop.com/appapi/mine/user_login ";
    String quickLoginUrl = "http://www.yangtaotop.com/appapi/mine/quick_login";
    String getCodeUrl = "http://www.yangtaotop.com/appapi/mine/send_verify_code?secret_key=20854474d90b917756a48fe550dbabec&mobile=";
    TextView back;
    Button btn_quick_login, btn_password_login, quicklogin_btn_code;
    EditText quicklogin_count_et, quicklogin_password_et, passwordlogin_count_et, passwordlogin_password_et;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intiView();
        initEvent();
    }


    private void intiView() {
        back = (TextView) findViewById(R.id.loginactivity_back);
        quicklogin_btn_code = (Button) findViewById(R.id.quicklogin_btn_code);
        btn_quick_login = (Button) findViewById(R.id.quicklogin_btn_login);
        btn_password_login = (Button) findViewById(R.id.passwordlogin_btn_login);
        quicklogin_count_et = (EditText) findViewById(R.id.quicklogin_count_et);
        quicklogin_password_et = (EditText) findViewById(R.id.quicklogin_password_et);
        passwordlogin_count_et = (EditText) findViewById(R.id.passwordlogin_count_et);
        passwordlogin_password_et = (EditText) findViewById(R.id.passwordlogin_password_et);
    }

    private void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //快捷登录
        btn_quick_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNum = quicklogin_count_et.getText().toString();
                if (TextUtils.isEmpty(mobileNum)) {
                    Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = quicklogin_password_et.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("mobile", mobileNum);
                params.put("verify_code", code);
                params.put("source", 603 + "");

                HttpUtil.doHttpReqeust1("POST", quickLoginUrl, params, new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        String result = (String) data;
                        try {
                            JSONObject object = new JSONObject(result);
                            String msg = object.getString("msg");
                            if (msg.equals("登录成功")) {
                                JSONObject dataObject = object.getJSONObject("data");
                                UserInfoBean bean = new Gson().fromJson(dataObject.toString(), UserInfoBean.class);
                                Log.d("lb", bean.toString());
                                XutilsManager.getInstance().getDbManager().save(bean);

                                Intent i = new Intent();
                                setResult(1001, i);
                                finish();
                            } else if (msg.equals("手机号或验证码错误")) {
                                Toast.makeText(LoginActivity.this, "手机号或验证码错误", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                Toast.makeText(LoginActivity.this, "验证码失效，请创新发送", Toast.LENGTH_SHORT).show();
                                return;
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
            }
        });

        //密码登录
        btn_password_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNum = passwordlogin_count_et.getText().toString();
                if (TextUtils.isEmpty(mobileNum)) {
                    Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = passwordlogin_password_et.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("mobileoremail", mobileNum);
                params.put("password", password);
                HttpUtil.doHttpReqeust1("POST", passwordLoginUrl, params, new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        String result = (String) data;
                        try {
                            JSONObject object = new JSONObject(result);
                            String msg = object.getString("msg");
                            if (msg.equals("登录成功")) {
                                JSONObject dataObject = object.getJSONObject("data");
                                UserInfoBean bean = new Gson().fromJson(dataObject.toString(), UserInfoBean.class);
                                XutilsManager.getInstance().getDbManager().save(bean);

                                Intent i = new Intent();
                                setResult(1001, i);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
                                return;
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
            }
        });

        //获取验证码
        quicklogin_btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNum = quicklogin_count_et.getText().toString();
                if (TextUtils.isEmpty(mobileNum)) {
                    Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!StringUtil.isMobileNum(mobileNum)) {
                    Toast.makeText(LoginActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                //请求验证码
                HttpUtil.doHttpReqeust1("GET", getCodeUrl + mobileNum, null, new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        String result = (String) data;
                        try {
                            JSONObject object = new JSONObject(result);
                            String msg = object.getString("msg");
                            if (msg.equals("验证码发送成功")) {
                                quicklogin_btn_code.setText("已发送");
                            } else {
                                Toast.makeText(LoginActivity.this, "验证码获取失败！", Toast.LENGTH_SHORT).show();
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
        });
    }


}
