package com.example.administrator.yangtao.modules.SaleRank.activity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class TopImgInfo {

    /**
     * title : 去日本必买清单
     * image_path : http://www.yangtaotop.com/upload/app_banner/20161114/日本必买の上榜好物清单300.jpg
     * redirect_url :
     * r_type : 1
     * target_id : 152
     * keyword :
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String image_path;
        private String target_id;

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public String getTarget_id() {
            return target_id;
        }

        public void setTarget_id(String target_id) {
            this.target_id = target_id;
        }
    }
}
