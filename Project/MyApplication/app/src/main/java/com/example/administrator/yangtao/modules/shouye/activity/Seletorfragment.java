package com.example.administrator.yangtao.modules.shouye.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.classic.adapter.CommonRecyclerAdapter;
import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.activity.BaseFragment;
import com.example.administrator.yangtao.activity.SearchActivity;
import com.example.administrator.yangtao.modules.shouye.adapter.RecyclerViewAdapter;
import com.example.administrator.yangtao.modules.shouye.adapter.ShouyeAdadapter;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeAdInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeGoodInfo;
import com.jude.rollviewpager.RollPagerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class Seletorfragment extends BaseFragment {

    private RecyclerView recyclerview;
    private RecyclerView itemrecyclerview;
    private RecyclerViewHeader recyclerViewHeader;
    private RollPagerView rollPagerView;
    private List<ShouyeAdInfo> adurllist;
    private String TAG;
    private GridLayoutManager gridLayoutManager;
    private List<ShouyeGoodInfo> datalist;
    private CommonRecyclerAdapter<ShouyeGoodInfo> commonRecyclerAdapter;
    private int page=1;
    private RecyclerViewAdapter adapter;

    @Override
    protected void findViews(View view) {
        recyclerview = (RecyclerView) view.findViewById(R.id.shouye_recyclerview1);
        recyclerViewHeader = (RecyclerViewHeader) view.findViewById(R.id.shouye_recyclerviewheader1);
        datalist=new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(gridLayoutManager);

        itemrecyclerview = (RecyclerView) view.findViewById(R.id.shouye_item1_recyclerview);
        rollPagerView = (RollPagerView) view.findViewById(R.id.shouye_item1_rollviewpager);
    }


    @Override
    protected void init() {

        recyclerview.setHasFixedSize(true);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerViewAdapter(getContext(),datalist);
        recyclerview.setAdapter(adapter);
        recyclerViewHeader.attachTo(recyclerview);
        addPager();

        RecyclerView.LayoutManager gridLayoutManageritem=new LinearLayoutManager(getContext());
        itemrecyclerview.setLayoutManager(gridLayoutManageritem);
        itemrecyclerview.setAdapter(adapter);

    }

    private void addPager() {


        String adurl="http://www.yangtaotop.com/appapi/banner/carousels?pos_id=1";
        new OkHttpClient().newCall(new Request.Builder().url(adurl).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "run: 网络连接异常" );
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    String json=response.body().string();
                adurllist = new ArrayList<ShouyeAdInfo>();
                try {
                    ShouyeAdInfo adInfo=null;
                    JSONObject jsonObject=new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                       adInfo=new ShouyeAdInfo();
                        JSONObject object = jsonArray.getJSONObject(i);
                        adInfo.setImage(object.getString("image_path"));
                        adInfo.setTargeId(object.getString("target_id"));
                        adInfo.setTitle(object.getString("title"));
                        adurllist.add(adInfo);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ShouyeAdadapter Rolladapter=new ShouyeAdadapter(rollPagerView,getContext(), adurllist);
                            rollPagerView.setAdapter(Rolladapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    protected void initEvent() {
        recyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
              if(newState==RecyclerView.SCROLL_STATE_IDLE){
                  int lastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                  if(lastCompletelyVisibleItemPosition==datalist.size()-1){
                      page++;
                        requestNetData();
                  }
              }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {
        String goodurl="http://www.yangtaotop.com/appapi/main/hot_products?p=1&cid="+page+"&num=10";
        new OkHttpClient().newCall(new Request.Builder().url(goodurl).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "run: 网络连接异常" );
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                final List<ShouyeGoodInfo> list = new ArrayList<ShouyeGoodInfo>();
                try {
                    ShouyeGoodInfo Info=null;
                    JSONObject jsonObject=new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        Info=new ShouyeGoodInfo();
                        JSONObject object = jsonArray.getJSONObject(i);
                        Info.setImage_path(object.getString("image_path"));
                        Info.setProd_id(object.getString("prod_id"));
                        Info.setProd_name(object.getString("prod_name"));
                        Info.setProd_price(object.getString("prod_price"));
                        list.add(Info);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           datalist.addAll(list);
                           adapter.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.shouye_fragment_selector;
    }
}
