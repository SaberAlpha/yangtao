package com.example.administrator.yangtao.modules.shouye.bean;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class BaseShouyeInfo {
    private String id;
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public interface Types {
        int TYPE_SORT= 0;
        int TYPE_RANK = 1;
        int TYPE_MODLE = 2;
        int TYPE_HOT = 3;
        int TYPE_GOOD = 4;
    }
}
