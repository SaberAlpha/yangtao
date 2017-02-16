package com.example.administrator.yangtao.modules.shouye.bean;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class HotInfo extends BaseShouyeInfo {

    /**
     * wv_id : 20
     * pos_id : 54
     * image_path : http://www.yangtaotop.com/upload/image/20160818/黑马榜.jpg
     * title : 日用小家电黑马榜
     * intro : 生活中有那么些出奇不易的东西能带给我们莫名的幸福感～一起来看看本期荣登日用小家电黑马榜的商品有哪些～
     */

    private String wv_id;
    private String pos_id;
    private String image_path;
    private String title;
    private String intro;
    private String keys;
    private String itemkey;

    public String getItemkey() {
        return itemkey;
    }

    public void setItemkey(String itemkey) {
        this.itemkey = itemkey;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    /**
     * prod_id : 4147
     * prod_name : 日本 BOTANIST 植物学家 天然植物萃取洗发水 490ml 黑色保湿滋润型
     * prod_price : 101
     */

    private String prod_id;
    private String prod_name;
    private String prod_price;
    private String item_image;

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getWv_id() {
        return wv_id;
    }

    public void setWv_id(String wv_id) {
        this.wv_id = wv_id;
    }

    public String getPos_id() {
        return pos_id;
    }

    public void setPos_id(String pos_id) {
        this.pos_id = pos_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

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
}
