package com.example.administrator.yangtao.modules.shouye.bean;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class SortInfo extends BaseShouyeInfo {


    /**
     * title : 上新
     * image_path : http://www.yangtaotop.com/upload/app_banner/20161124/icon东.jpg
     * redirect_url :
     * r_type : 1
     * target_id : 92
     * keyword :
     */

    private String title;
    private String image_path;
    private String redirect_url;
    private String r_type;
    private String target_id;
    private String keyword;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getR_type() {
        return r_type;
    }

    public void setR_type(String r_type) {
        this.r_type = r_type;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
