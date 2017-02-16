package com.example.administrator.yangtao.modules.SaleRank.activity;

import android.view.View;
import android.widget.ListView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.activity.BaseFragment;
import com.example.administrator.yangtao.adapter.CommonAdapter;
import com.example.administrator.yangtao.adapter.ViewHolder;
import com.example.administrator.yangtao.modules.SaleRank.bean.FengXiangBiaoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class FengXiangBiaoFrament extends BaseFragment {
    private ListView listView;
    private List<FengXiangBiaoInfo> list;

    @Override
    protected void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        list = new ArrayList<>();
        CommonAdapter<FengXiangBiaoInfo> adapter = new CommonAdapter<FengXiangBiaoInfo>(getContext(), list,R.layout.feng_xiang_biao_item_adpter) {
            @Override
            public void convert(ViewHolder helper, int position, FengXiangBiaoInfo item) {

            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.sale_list_view_layout;
    }
}
