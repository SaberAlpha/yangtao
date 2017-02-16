package com.example.administrator.yangtao.modules.SaleRank.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table(name = "FengXiangBiaoInfo")
public class FengXiangBiaoInfo {
    @Column(name = "_id",isId = true)
    private int _id;
    @Column(name = "id")
    private String id;

}
