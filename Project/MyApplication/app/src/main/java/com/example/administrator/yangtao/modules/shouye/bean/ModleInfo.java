package com.example.administrator.yangtao.modules.shouye.bean;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class ModleInfo extends BaseShouyeInfo {

    /**
     * title : 榜单分会场
     * image_path : http://www.yangtaotop.com/upload/app_banner/20161123/榜单好货全搜罗.jpg
     * redirect_url :
     * r_type : 1
     * target_id : 121
     * keyword :
     */

    private String title;
    private String image_path;
    private String redirect_url;
    private String r_type;
    private String target_id;
    private String keyword;
    /**
     * 她们在讨论
     * posts_id : 59
     * nickname : 哈尼丽丽
     * avatar : http://www.yangtaotop.com/upload/avatar/majia/57a139899fa97.jpg
     * like_num : 8
     * is_like : 0
     */

    private String posts_id;
    private String nickname;
    private String avatar;
    private String like_num;
    private String is_like;

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

    public String getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(String posts_id) {
        this.posts_id = posts_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }
}
