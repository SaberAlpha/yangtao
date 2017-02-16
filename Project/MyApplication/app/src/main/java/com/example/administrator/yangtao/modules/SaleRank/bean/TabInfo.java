package com.example.administrator.yangtao.modules.SaleRank.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table(name = "TabInfo")
public class TabInfo {
    @Column(name = "_id",isId = true)
    private int _id;
    @Column(name = "tabId")
    private String tabId;
    @Column(name = "tab")
    private String tab;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
}
