package com.example.administrator.yangtao.modules.SaleRank.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.activity.BaseFragment;
import com.example.administrator.yangtao.adapter.CommonAdapter;
import com.example.administrator.yangtao.adapter.ViewHolder;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.SaleRank.bean.TabInfo;
import com.example.administrator.yangtao.modules.SaleRank.bean.UserListInfo;
import com.example.administrator.yangtao.modules.SaleRank.dao.SaleRankDao;
import com.example.administrator.yangtao.util.ThreadTask;
import com.example.administrator.yangtao.util.XutilsManager;
import com.google.gson.Gson;

import org.xutils.ex.DbException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class SaleRankFragnemet extends BaseFragment {


    private ImageView fenLei;
    private EditText searchEdit;
    private ImageView rankCar;
    private ListView listView;
    private ImageView progressImg;
    private View header;
    private ImageView topImg;
    private TabLayout tabLayout;
    private RelativeLayout fxbAllList;
    private RelativeLayout hmbAllList;
    private GridView gridView;
    private ListView heimaListView;
    private List<UserListInfo.DataBean.ProductsBean> list;
    private CommonAdapter<UserListInfo.DataBean.ProductsBean> adapter;
    private List<TabInfo> tabInfoList;
    private String tabId;
    private int page = 1;
    private View footer;


    @Override
    protected void findViews(View view) {

        progressImg = (ImageView) view.findViewById(R.id.progress_img);
    }

    @Override
    protected void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE){
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    if (lastVisiblePosition == (list.size()+1)/2+listView.getHeaderViewsCount()){
                        View lastVisibleView = listView.getChildAt(listView.getChildCount() - 1);
                        if (lastVisibleView.getBottom() == listView.getBottom()){
                            page++;
                            requestNetData();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FengXiangBiaoFrament frament = new FengXiangBiaoFrament();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_contaniner, frament);
    }

    @Override

    protected void init() {


        header = getActivity().getLayoutInflater().inflate(R.layout.sale_head_layout, listView, false);
        footer = getActivity().getLayoutInflater().inflate(R.layout.progress_animation_layout, listView, false);

        list = new ArrayList<>();
        adapter = new CommonAdapter<UserListInfo.DataBean.ProductsBean>(getContext(), list, R.layout.user_list_item_adapter) {
            @Override
            public void convert(ViewHolder helper, int position, UserListInfo.DataBean.ProductsBean item) {
                UserListInfo.DataBean.ProductsBean leftInfo = list.get(position * 2);
                helper.setImageByUrl(R.id.left_img, leftInfo.getImage_path());
                helper.setText(R.id.left_name, leftInfo.getProd_name());
                helper.setText(R.id.left_price, "￥" + leftInfo.getProd_price());

                View leftItem = helper.getView(R.id.left_item);
                View rightItem = helper.getView(R.id.right_item);
                if (position * 2 + 1 == list.size()) {
                    rightItem.setVisibility(View.GONE);
                } else {
                    rightItem.setVisibility(View.VISIBLE);
                    UserListInfo.DataBean.ProductsBean righrInfo = list.get(position * 2 + 1);
                    helper.setImageByUrl(R.id.right_img, righrInfo.getImage_path());
                    helper.setText(R.id.right_name, righrInfo.getProd_name());
                    helper.setText(R.id.right_price, "￥" + righrInfo.getProd_price());
                }
            }
        };
        listView.addHeaderView(header);
        listView.setAdapter(adapter);
        topImg = (ImageView) header.findViewById(R.id.top_iv);
        tabLayout = (TabLayout) header.findViewById(R.id.tab_layout);
        fxbAllList = (RelativeLayout) header.findViewById(R.id.all_list1);
        hmbAllList = (RelativeLayout) header.findViewById(R.id.all_list2);
        gridView = (GridView) header.findViewById(R.id.grid_view);
        heimaListView = (ListView) header.findViewById(R.id.heima_list_view);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.green));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        loadTabData();
        loadTopImgData();
    }

    private void loadTopImgData() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://www.yangtaotop.com/appapi/banner/carousels?pos_id=39";
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getContext(), "网络错误，加载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                UserListInfo.DataBean dataBean = gson.fromJson(json, UserListInfo.DataBean.class);
//                dataBean
            }
        });

    }


    private void loadTabData() {

        SaleRankDao.requestTabInfo(new BaseCallBack() {

            @Override
            public void success(Object data) {
                tabInfoList = (List<TabInfo>) data;
                Log.e("info------>", "success: " + tabInfoList.size());
                for (int i = 0; i < tabInfoList.size(); i++) {
                    if (i == 0) {
                        tabLayout.addTab(tabLayout.newTab().setText(tabInfoList.get(i).getTab()), i, true);
                    } else {
                        tabLayout.addTab(tabLayout.newTab().setText(tabInfoList.get(i).getTab()), i, false);
                    }
                }
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        tabId = tabInfoList.get(position).getTabId();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }

            @Override
            public void failed(int errorCode, Object data) {

            }
        });


    }


    @Override
    protected void loadData() {
        ThreadTask.getInstance().executorDBThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<UserListInfo.DataBean.ProductsBean> tempList = XutilsManager.getInstance().getDbManager().findAll(UserListInfo.DataBean.ProductsBean.class);
                    if (tempList != null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(tempList);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestNetData();
                    }
                });
            }
        }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
    }

    @Override
    protected void requestNetData() {
        SaleRankDao.requestUserList(page, new BaseCallBack() {

            @Override
            public void success(Object data) {
                progressImg.setVisibility(View.GONE);
                final List<UserListInfo.DataBean.ProductsBean> tempList = (List<UserListInfo.DataBean.ProductsBean>) data;
                if (page == 1) {
                    ThreadTask.getInstance().executorDBThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                XutilsManager.getInstance().getDbManager().delete(UserListInfo.DataBean.ProductsBean.class);
                                XutilsManager.getInstance().getDbManager().save(tempList);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }
                    }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
                    list.clear();
                }

                if (listView.getFooterViewsCount() == 0) {
                    listView.addFooterView(footer);
                }

                if (tempList.size() < 20) {
                    listView.removeFooterView(footer);
                }

                if (tempList != null) {
                    list.addAll(tempList);
                    adapter.setCount((list.size() + 1) / 2);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {

            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_salerank;
    }
}
