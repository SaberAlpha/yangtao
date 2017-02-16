package com.example.administrator.yangtao.modules.MyLogin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.MyLogin.bean.UserInfoBean;
import com.example.administrator.yangtao.modules.MyLogin.net.LBHttpUtil;
import com.example.administrator.yangtao.modules.MyLogin.util.LoginInfoUtil;
import com.example.administrator.yangtao.util.XutilsManager;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class MyLoginFragnemet extends Fragment {

    String getUserInfoUrl = "http://www.yangtaotop.com/appapi/mine/get_userinfo";
    View view;
    //控件
    RelativeLayout rl;
    TextView me_name, me_score, me_shop_num, me_rank_num, me_party_num;
    RelativeLayout me_discount, me_favorite, me_rank, me_topic, me_trouble, me_setting;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mylogin, null);
        initView();
        initEvent();
        return view;
    }

    private void initView() {

        rl = (RelativeLayout) view.findViewById(R.id.mine_personinfor_rl);
        me_name = (TextView) view.findViewById(R.id.me_name);
        me_score = (TextView) view.findViewById(R.id.me_score);
        me_shop_num = (TextView) view.findViewById(R.id.me_shop_num);
        me_rank_num = (TextView) view.findViewById(R.id.me_rank_num);
        me_party_num = (TextView) view.findViewById(R.id.me_party_num);
        me_discount = (RelativeLayout) view.findViewById(R.id.me_discount);
        me_favorite = (RelativeLayout) view.findViewById(R.id.me_favorite);
        me_rank = (RelativeLayout) view.findViewById(R.id.me_rank);
        me_topic = (RelativeLayout) view.findViewById(R.id.me_topic);
        me_trouble = (RelativeLayout) view.findViewById(R.id.me_trouble);
        me_setting = (RelativeLayout) view.findViewById(R.id.me_setting);
    }

    private void initEvent() {
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<UserInfoBean> all = XutilsManager.getInstance().getDbManager().findAll(UserInfoBean.class);
                    //判断是否登录
                    if (all == null || all.size() == 0) {
                        Intent i = new Intent(getContext(), LoginActivity.class);
                        startActivityForResult(i, 1000);
                    } else {
                        Intent intent = new Intent(getContext(), UserInforActivity.class);
                        startActivityForResult(intent, 2000);
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }

            }
        });

        me_discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyDiscountActivity.class));
            }
        });

        me_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyFavoriteActivity.class);
                startActivityForResult(intent, 3000);
            }
        });

        me_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyRankActivity.class));
            }
        });
        me_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyTopicActivity.class));
            }
        });
        me_trouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyTroubleActivity.class));
            }
        });

        me_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MySettingActivity.class));
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == 1001) {
                UserInfoBean first = null;
                try {
                    first = XutilsManager.getInstance().getDbManager().findFirst(UserInfoBean.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                getUserInfoData();
            }
        }

        if (requestCode == 2000) {
            if (resultCode == 2001) {
                getUserInfoData();
            }
            if (resultCode == 2002) {
                me_name.setText("登录|注册");
                me_score.setText("积分0");
                me_shop_num.setVisibility(View.GONE);
                me_rank_num.setVisibility(View.GONE);
                me_party_num.setVisibility(View.GONE);
            }
        }

        if (requestCode == 3000) {
            if (resultCode == 3001) {
                getUserInfoData();
            }
        }
    }

    //获取用户的信息并显示
    private void getUserInfoData() {
        LBHttpUtil.doHttpGetWithHead(getUserInfoUrl, LoginInfoUtil.getAuthToken(), new BaseCallBack() {
            @Override
            public void success(Object data) {
                String result = (String) data;
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("msg");
                    if (msg.equals("加载成功")) {
                        JSONObject dataObject = object.getJSONObject("data");
                        UserInfoBean userInfoBean = new Gson().fromJson(dataObject.toString(), UserInfoBean.class);
                        XutilsManager.getInstance().getDbManager().delete(UserInfoBean.class);
                        XutilsManager.getInstance().getDbManager().save(userInfoBean);
                        Log.d("lb", "userinfo" + userInfoBean.toString());
                        me_name.setText(userInfoBean.getNickname());
                        me_score.setText("积分:" + userInfoBean.getScore());

                        //查看关注是否为0:0就不显示
                        if (userInfoBean.getProd_total() != 0) {
                            me_shop_num.setText(userInfoBean.getProd_total() + "");
                            me_shop_num.setVisibility(View.VISIBLE);
                        } else {
                            me_shop_num.setVisibility(View.GONE);
                        }

                        if (userInfoBean.getCatalog_total() != 0) {
                            me_rank_num.setText(userInfoBean.getCatalog_total() + "");
                            me_rank_num.setVisibility(View.VISIBLE);
                        } else {
                            me_rank_num.setVisibility(View.GONE);
                        }

                        if (userInfoBean.getTopic_total() != 0) {
                            me_party_num.setText(userInfoBean.getTopic_total() + "");
                            me_party_num.setVisibility(View.VISIBLE);
                        } else {
                            me_party_num.setVisibility(View.GONE);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(int errorCode, Object data) {
                getUserInfoData();
            }
        });
    }
}
