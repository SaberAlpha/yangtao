package com.example.administrator.yangtao.modules.SaleRank.util;

import com.example.administrator.yangtao.modules.SaleRank.bean.TabInfo;
import com.example.administrator.yangtao.modules.SaleRank.bean.UserListInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class SaleRankParse {

    public static List<TabInfo> parseTabInfo(String json) {
        List<TabInfo> list = new ArrayList<>();
        TabInfo info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                try{
                    info = new TabInfo();
                    JSONObject subJson = data.getJSONObject(i);
                    info.setTabId(subJson.getString("tab_id"));
                    info.setTab(subJson.getString("wv_tag"));
                    list.add(info);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<UserListInfo.DataBean.ProductsBean> parseUserListInfo(String json) {
        List<UserListInfo.DataBean.ProductsBean> list = new ArrayList<>();
        UserListInfo.DataBean.ProductsBean info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray products = data.getJSONArray("products");
            for (int i = 0; i < products.length(); i++) {
                try{
                    info = new UserListInfo.DataBean.ProductsBean();
                    JSONObject subJson = products.getJSONObject(i);
                    info.setProd_id(subJson.getString("prod_id"));
                    info.setProd_name(subJson.getString("prod_name"));
                    info.setProd_price(subJson.getString("prod_price"));
                    info.setImage_path(subJson.getString("image_path"));
                    list.add(info);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
