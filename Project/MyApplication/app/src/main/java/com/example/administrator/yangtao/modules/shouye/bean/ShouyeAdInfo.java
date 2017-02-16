package com.example.administrator.yangtao.modules.shouye.bean;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class ShouyeAdInfo {
        private String image;
        private String targeId;
    private String title;
    /**
     * image_path : http://www.yangtaotop.com/upload/app_banner/20161123/黑五主会场750-300.jpg
     * redirect_url : http://www.yangtaotop.com/mhome/activity/master_black?app_type=h5
     * r_type : 3
     * target_id :
     * keyword :
     */

    private String redirect_url;
    private String r_type;
    private String keyword;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTargeId() {
        return targeId;
    }

    public void setTargeId(String targeId) {
        this.targeId = targeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
