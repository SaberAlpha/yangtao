package com.example.administrator.yangtao.modules.MyLogin.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table(name = "UserInfor")
public class UserInfoBean {

    /**
     * id : 23566
     * mobile : 18270864223
     * email :
     * password : $2y$14$.jUStiDRBDt46Ph7cuHpseNMqSrHdnBmROqxFa8ynNSoA2XHkSh9m
     * gender : 3
     * birthday : 2012-12-12
     * nickname : liubin
     * unionid :
     * weibo_token :
     * qq_token :
     * auth_token : bc38N0qURQTRBn9CzrrV68N+RtOVUSFcv16jIT1XFRAEtQ
     * app_token_expiry : 1480494499
     * avatar : http://www.yangtaotop.com/upload/avatar/23566/5833fb495061e.jpg
     * prod_total : 1
     * catalog_total : 3
     * topic_total : 2
     * not_pay_total : 0
     * not_process_total : 0
     * return_total : 0
     * wait_total : 0
     * already_total : 0
     * finish_total : 0
     * score : 0
     * app_share : {"share_key":"介绍给朋友","title":"【分享免邮】洋桃派，真正的同货同价同步全日本！","desc":"榜单选品 | 官网同价 | 直邮保税","rule_url":"","rule_rul":"","img_url":"http://www.yangtaotop.com/statics/mh5/images/app_share.png","bottom_text":"你的好友都将获得10元无门槛券","share_val":"10元无门槛券","url":"http://www.yangtaotop.com/home/app/share?uid=NoeaRM8Sh38pPbd2ig&c_id=M4OUFc9Hiy4qNg"}
     * kf_status : 当前在线
     * service_hotline : 400-755-7775
     */

    //登录返回信息（app_token_expiry为int，此处改为Strin

    @Column(name = "_id", isId = true)
    private int _id;
    @Column(name = "id")
    private String id;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "unionid")
    private String unionid;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "weibo_token")
    private String weibo_token;
    @Column(name = "qq_tokend")
    private String qq_token;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "auth_token")
    private String auth_token;
    @Column(name = "app_token_expiry")
    private String app_token_expiry;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "kf_status")
    private String kf_status;
    @Column(name = "service_hotline")
    private String service_hotline;

    //用户信息比登录返回信息多的地方
    @Column(name = "prod_total")
    private int prod_total;
    @Column(name = "catalog_total")
    private int catalog_total;
    @Column(name = "topic_total")
    private int topic_total;
    @Column(name = "not_pay_total")
    private int not_pay_total;
    @Column(name = "not_process_total")
    private int not_process_total;
    @Column(name = "return_total")
    private int return_total;
    @Column(name = "wait_total")
    private int wait_total;
    @Column(name = "already_total")
    private int already_total;
    @Column(name = "finish_total")
    private int finish_total;
    @Column(name = "score")
    private int score;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getWeibo_token() {
        return weibo_token;
    }

    public void setWeibo_token(String weibo_token) {
        this.weibo_token = weibo_token;
    }

    public String getQq_token() {
        return qq_token;
    }

    public void setQq_token(String qq_token) {
        this.qq_token = qq_token;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getApp_token_expiry() {
        return app_token_expiry;
    }

    public void setApp_token_expiry(String app_token_expiry) {
        this.app_token_expiry = app_token_expiry;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getProd_total() {
        return prod_total;
    }

    public void setProd_total(int prod_total) {
        this.prod_total = prod_total;
    }

    public int getCatalog_total() {
        return catalog_total;
    }

    public void setCatalog_total(int catalog_total) {
        this.catalog_total = catalog_total;
    }

    public int getTopic_total() {
        return topic_total;
    }

    public void setTopic_total(int topic_total) {
        this.topic_total = topic_total;
    }

    public int getNot_pay_total() {
        return not_pay_total;
    }

    public void setNot_pay_total(int not_pay_total) {
        this.not_pay_total = not_pay_total;
    }

    public int getNot_process_total() {
        return not_process_total;
    }

    public void setNot_process_total(int not_process_total) {
        this.not_process_total = not_process_total;
    }

    public int getReturn_total() {
        return return_total;
    }

    public void setReturn_total(int return_total) {
        this.return_total = return_total;
    }

    public int getWait_total() {
        return wait_total;
    }

    public void setWait_total(int wait_total) {
        this.wait_total = wait_total;
    }

    public int getAlready_total() {
        return already_total;
    }

    public void setAlready_total(int already_total) {
        this.already_total = already_total;
    }

    public int getFinish_total() {
        return finish_total;
    }

    public void setFinish_total(int finish_total) {
        this.finish_total = finish_total;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getKf_status() {
        return kf_status;
    }

    public void setKf_status(String kf_status) {
        this.kf_status = kf_status;
    }

    public String getService_hotline() {
        return service_hotline;
    }

    public void setService_hotline(String service_hotline) {
        this.service_hotline = service_hotline;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "_id=" + _id +
                ", id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", unionid='" + unionid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", weibo_token='" + weibo_token + '\'' +
                ", qq_token='" + qq_token + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", auth_token='" + auth_token + '\'' +
                ", app_token_expiry='" + app_token_expiry + '\'' +
                ", avatar='" + avatar + '\'' +
                ", kf_status='" + kf_status + '\'' +
                ", service_hotline='" + service_hotline + '\'' +
                ", prod_total=" + prod_total +
                ", catalog_total=" + catalog_total +
                ", topic_total=" + topic_total +
                ", not_pay_total=" + not_pay_total +
                ", not_process_total=" + not_process_total +
                ", return_total=" + return_total +
                ", wait_total=" + wait_total +
                ", already_total=" + already_total +
                ", finish_total=" + finish_total +
                ", score=" + score +
                '}';
    }
}
