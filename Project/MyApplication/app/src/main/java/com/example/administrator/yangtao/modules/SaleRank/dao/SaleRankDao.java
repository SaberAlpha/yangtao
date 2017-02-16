package com.example.administrator.yangtao.modules.SaleRank.dao;

import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.SaleRank.bean.TabInfo;
import com.example.administrator.yangtao.modules.SaleRank.bean.UserListInfo;
import com.example.administrator.yangtao.modules.SaleRank.util.SaleRankParse;
import com.example.administrator.yangtao.net.HttpUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class SaleRankDao {

    public static void requestTabInfo(final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET", "http://www.yangtaotop.com/appapi/wind_vane/jp_wind_vane_tabs?pos_id=40", null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    List<TabInfo> tempList = SaleRankParse.parseTabInfo(data.toString());
                    callBack.success(tempList);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode,data);
                }
            }
        });
    }

    public static void requestUserList(int page, final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET", "http://www.yangtaotop.com/appapi/wind_vane/hot_user_products?p="+page+"&num=20", null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    List<UserListInfo.DataBean.ProductsBean> tempList = SaleRankParse.parseUserListInfo(data.toString());
                    callBack.success(tempList);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode,data);
                }
            }
        });
    }
}
