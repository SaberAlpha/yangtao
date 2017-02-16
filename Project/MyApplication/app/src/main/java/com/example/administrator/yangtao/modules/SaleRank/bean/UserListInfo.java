package com.example.administrator.yangtao.modules.SaleRank.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table(name = "UserListInfo")
public class UserListInfo {

    /**
     * row_total : 1322
     * hour : 18
     * products : [{"prod_id":"7489","prod_name":"用过都说好           dhc橄榄润唇膏","prod_price":"48","image_path":"http://img2.yangtaotop.com/upload/image/20160919/1474267135537582.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"8137","prod_name":"真正的日本爆卖面膜 PLuS胎盘素面膜","prod_price":"89","image_path":"http://img2.yangtaotop.com/upload/image/20160906/1473126573298240.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"5099","prod_name":"乐敦CC美白精华20ml","prod_price":"80","image_path":"http://img2.yangtaotop.com/upload/image/20160229/1456750198608889.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"5104","prod_name":"plus神经酰胺精华液","prod_price":"89","image_path":"http://img2.yangtaotop.com/upload/image/20160321/1458527949487908.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"5064","prod_name":"SK-II 神仙水 215ml","prod_price":"684","image_path":"http://img2.yangtaotop.com/upload/image/20161012/1476262305861230.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"93002","prod_name":"奥尔滨健康水330ml","prod_price":"670","image_path":"http://img2.yangtaotop.com/upload/image/20161017/1476694094747779.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"29735","prod_name":"黛珂肌底精华液60ml","prod_price":"582","image_path":"http://img2.yangtaotop.com/upload/image/20151228/1451274384479777.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"89465","prod_name":"城野医生VC100精华水","prod_price":"238","image_path":"http://img2.yangtaotop.com/upload/image/20160927/1474957095944816.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"89568","prod_name":"IPSA自律循环美肌液","prod_price":"463","image_path":"http://img2.yangtaotop.com/upload/image/20160930/1475217042734079.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"60198","prod_name":"珂润保湿面霜40g","prod_price":"107","image_path":"http://img2.yangtaotop.com/upload/image/20160812/1470985619189748.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"4147","prod_name":"美日法销量第一的洗发水 滋润型","prod_price":"101","image_path":"http://img2.yangtaotop.com/upload/image/20160907/1473243866154400.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"4149","prod_name":"销量6年连续第一的男士防脱发洗发露","prod_price":"261","image_path":"http://img2.yangtaotop.com/upload/image/20161105/1478347220814826.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"93199","prod_name":"爱上波波头眉上刘海短发 S 棕褐色","prod_price":"722","image_path":"http://img2.yangtaotop.com/upload/image/20161024/1477298518125204.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"93197","prod_name":"linea-storia手工制天使之波长卷发假发 深棕色","prod_price":"937","image_path":"http://img2.yangtaotop.com/upload/image/20161024/1477298806664500.png","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"5269","prod_name":"HABA 鲨烷精纯美容油 30ml","prod_price":"181","image_path":"http://img2.yangtaotop.com/upload/image/20160819/1471601327146018.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"89520","prod_name":"SUQQU丰盈润泽保湿唇膏　07 珊瑚石","prod_price":"380","image_path":"http://img2.yangtaotop.com/upload/image/20161104/1478252478355505.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"16500","prod_name":"果冻肌肤敷出来 佑天兰奢华美容液面膜","prod_price":"36","image_path":"http://img2.yangtaotop.com/upload/image/20160905/1473063724568905.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"5009","prod_name":"佳丽宝酵素洁颜粉32个","prod_price":"101","image_path":"http://img2.yangtaotop.com/upload/image/20160518/1463560215437342.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"96106","prod_name":"资生堂红色蜜露化妆水200ml","prod_price":"437","image_path":"http://img2.yangtaotop.com/upload/image/20161031/1477879355975082.jpg","tag":"","is_stock":"1","cata_image_path":""},{"prod_id":"5560","prod_name":"MOTEMASCARA  修复纤长睫毛膏7g","prod_price":"130","image_path":"http://img2.yangtaotop.com/upload/image/20161028/1477646647185716.jpg","tag":"","is_stock":"1","cata_image_path":""}]
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * prod_id : 7489
         * prod_name : 用过都说好           dhc橄榄润唇膏
         * prod_price : 48
         * image_path : http://img2.yangtaotop.com/upload/image/20160919/1474267135537582.jpg
         * tag :
         * is_stock : 1
         * cata_image_path :
         */

        private List<ProductsBean> products;

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            @Column(name = "_id",isId = true)
            private int _id;
            @Column(name = "prod_id")
            private String prod_id;
            @Column(name = "prod_name")
            private String prod_name;
            @Column(name = "prod_price")
            private String prod_price;
            @Column(name = "image_path")
            private String image_path;

            public String getProd_id() {
                return prod_id;
            }

            public void setProd_id(String prod_id) {
                this.prod_id = prod_id;
            }

            public String getProd_name() {
                return prod_name;
            }

            public void setProd_name(String prod_name) {
                this.prod_name = prod_name;
            }

            public String getProd_price() {
                return prod_price;
            }

            public void setProd_price(String prod_price) {
                this.prod_price = prod_price;
            }

            public String getImage_path() {
                return image_path;
            }

            public void setImage_path(String image_path) {
                this.image_path = image_path;
            }
        }
    }
}
