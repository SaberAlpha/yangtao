package com.example.administrator.yangtao.modules.MyLogin.bean;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
public class MyTopicBean {
    /**
     * id : 10441
     * user_id : 23566
     * type : 6
     * related_id : 13
     * ctime : 1479801289
     * topic_title : #吃喝玩乐in日本#
     * topic_total : 35
     */

    private String id;
    private String user_id;
    private String type;
    private String related_id;
    private String ctime;
    private String topic_title;
    private int topic_total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelated_id() {
        return related_id;
    }

    public void setRelated_id(String related_id) {
        this.related_id = related_id;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public int getTopic_total() {
        return topic_total;
    }

    public void setTopic_total(int topic_total) {
        this.topic_total = topic_total;
    }
}
