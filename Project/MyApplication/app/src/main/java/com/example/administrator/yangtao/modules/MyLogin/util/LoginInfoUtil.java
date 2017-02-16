package com.example.administrator.yangtao.modules.MyLogin.util;

import com.example.administrator.yangtao.modules.MyLogin.bean.UserInfoBean;
import com.example.administrator.yangtao.util.XutilsManager;

import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class LoginInfoUtil {


    public static boolean hasLogin() {
        try {
            List<UserInfoBean> all = XutilsManager.getInstance().getDbManager().findAll(UserInfoBean.class);
            if (all == null || all.size() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getAuthToken() {
        try {
            UserInfoBean first = XutilsManager.getInstance().getDbManager().findFirst(UserInfoBean.class);
            String auth_token = first.getAuth_token();
            return auth_token;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return "";
    }
}
