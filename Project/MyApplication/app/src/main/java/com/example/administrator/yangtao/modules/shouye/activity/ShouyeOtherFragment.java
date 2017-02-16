package com.example.administrator.yangtao.modules.shouye.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.activity.BaseFragment;
import com.example.administrator.yangtao.adapter.CommonAdapter;
import com.example.administrator.yangtao.adapter.ViewHolder;
import com.example.administrator.yangtao.modules.shouye.adapter.ShouyOtherMuityListAdapter;
import com.example.administrator.yangtao.modules.shouye.adapter.ShouyeAdadapter;
import com.example.administrator.yangtao.modules.shouye.bean.BaseShouyeInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ModleInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeAdInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeGoodInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeRankInfo;
import com.example.administrator.yangtao.modules.shouye.bean.SortInfo;
import com.example.administrator.yangtao.modules.shouye.widget.ShouyeListview;
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
 * Created by Administrator on 2016/11/25 0025.
 */
public class ShouyeOtherFragment extends BaseFragment{

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommonAdapter<ShouyeGoodInfo> adapter;
    private List<ShouyeGoodInfo> infoList;
    private int page=1;
    private View footview;
    private View headview;
    private RollPagerView rollPagerView;
    private List<ShouyeAdInfo> adurllist;
    private int headId=-1;
    private ShouyOtherMuityListAdapter muityListAdapter;
    private ShouyeListview headlistview;
    private String TAG="ShouyeOtherFragment";
    private List<List<BaseShouyeInfo>> headlistsum;
    private Bundle bundle;
    private String[] headurl;

    @Override
    protected void findViews(View view) {

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.shouye_swipeview);
        listView= (ListView) view.findViewById(R.id.shouye_selector_listview);
        bundle = getArguments();
        headurl = bundle.getStringArray("head");


    }
    @Override
    protected void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.GREEN,Color.DKGRAY);
                requestNetData();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==SCROLL_STATE_IDLE){
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    if(lastVisiblePosition==(infoList.size()+1)/2+listView.getHeaderViewsCount()){
                        View childAt = listView.getChildAt(listView.getChildCount() - 1);
                        if( childAt.getBottom() == listView.getBottom()){
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
    protected void init() {
        infoList = new ArrayList<>();
        headlistsum = new ArrayList<>();

        footview = getActivity().getLayoutInflater().inflate(R.layout.foot_layout_loading, listView, false);
        headview = getActivity().getLayoutInflater().inflate(R.layout.shouye_headview, listView, false);
        adapter=new CommonAdapter<ShouyeGoodInfo>(getContext(), infoList,R.layout.shouye_listview_doubleitem) {
            @Override
            public void convert(ViewHolder helper, int position, ShouyeGoodInfo item) {
                ShouyeGoodInfo leftshopinfo = infoList.get(position * 2);
                helper.setImageByUrl(R.id.shouye_listview_left_image,leftshopinfo.getImage_path());
                helper.setText(R.id.shouye_listview_title_left,leftshopinfo.getProd_name());
                helper.setText(R.id.shouye_listview_price_left,"￥  "+leftshopinfo.getProd_price());

                if(position*2+1==infoList.size()){
                    helper.getView(R.id.shouview_left_cardview).setVisibility(View.INVISIBLE);

                }else{
                    ShouyeGoodInfo rightshopinfo = infoList.get(position * 2 + 1);
                    helper.setImageByUrl(R.id.shouye_listview_right_image,rightshopinfo.getImage_path());
                    helper.setText(R.id.shouye_listview_title_right,rightshopinfo.getProd_name());
                    helper.setText(R.id.shouye_listview_price_right,"￥  "+rightshopinfo.getProd_price());

                }

            }
        };

        listView.addHeaderView(headview);
//        adapter.setCount((gameinfoList.size()+1)/2);
        listView.addFooterView(footview);
        listView.setAdapter(adapter);
        listView.removeFooterView(footview);

        getheadview(headview);
        addData();

    }
    private void getheadview(View headview) {
        rollPagerView = (RollPagerView) headview.findViewById(R.id.shouye_item1_rollviewpager);
        addPager();
        headlistview = (ShouyeListview) headview.findViewById(R.id.shouye_newlist_item);;
    }

    private void addData() {
//        for(int i=0;i<headurl.length;i++){
//            headId = i;
//            Log.e(TAG, "addData: "+i+"???????"+ headId+"--------->"+headurl[i] );
//            headloaddata(headId);
//            SystemClock.sleep(1000);

//        }
        headId++;
        Log.e(TAG, "addData: "+"???????"+ headId+"--------->" );
        headloaddata(headId);

    }

    private void headloaddata(final int headId) {
        if(headId>=headurl.length){
            return;
        }
        final List<BaseShouyeInfo> headlist=new ArrayList<>();
        new OkHttpClient().newCall(new Request.Builder().url(headurl[headId]).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("print", "run: 网络连接异常" );
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: ??????????"+headurl[headId] );
                String json=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(json);

                    switch (headId){
                        case BaseShouyeInfo.Types.TYPE_SORT:
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            SortInfo sortInfo=null;
                            Log.e(TAG, "onResponse:>>>>>>>>>0 "+json );
                            for(int i=0;i<jsonArray.length();i++){
                                sortInfo= new SortInfo();
                                JSONObject object = jsonArray.getJSONObject(i);
                                sortInfo.setTitle(object.getString("title"));
                                sortInfo.setImage_path(object.getString("image_path"));
                                sortInfo.setTarget_id(object.getString("target_id"));
                                sortInfo.setType(headId);
                                headlist.add(sortInfo);
                            }
                            break;
                        case BaseShouyeInfo.Types.TYPE_RANK:
                            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                            ShouyeRankInfo rankInfo=null;
                            Log.e(TAG, "onResponse:>>>>>>>>>1 "+json );
                            for(int i=0;i<jsonArray1.length();i++){
                                rankInfo=new ShouyeRankInfo();
                                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                rankInfo.setType(headId);
                                rankInfo.setImage_path(jsonObject1.getString("image_path"));
                                rankInfo.setTarget_id(jsonObject1.getString("target_id"));
                                rankInfo.setTitle(jsonObject1.getString("title"));
                                headlist.add(rankInfo);
                            }
                            break;
                        case BaseShouyeInfo.Types.TYPE_MODLE:
                            JSONObject data = jsonObject.getJSONObject("data");
                            JSONArray jsonArray2 = data.getJSONArray("posts");
                            ModleInfo modleInfo=null;
                            Log.e(TAG, "onResponse:>>>>>>>>>2 "+json );
                            for(int i=0;i<jsonArray2.length();i++){
                                modleInfo=new ModleInfo();
                                JSONObject jsonObject2 = jsonArray2.getJSONObject(i);
                                modleInfo.setType(headId);
                                modleInfo.setPosts_id(jsonObject2.getString("posts_id"));
                                modleInfo.setImage_path(jsonObject2.getString("image_path"));
                               modleInfo.setTitle(jsonObject2.getString("title"));
                                modleInfo.setNickname(jsonObject2.getString("nickname"));
                                modleInfo.setAvatar(jsonObject2.getString("avatar"));
                               modleInfo.setLike_num(jsonObject2.getString("like_num"));
                                headlist.add(modleInfo);
                            }
                            break;
                    }
                    headlistsum.add(headlist);

                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if(headId<headurl.length-1){
                                Log.e(TAG, "run:>>>>>> fragment"+headId );
                                addData();
                            }else if(headId==headurl.length-1){
                                muityListAdapter = new ShouyOtherMuityListAdapter(getContext(),headlistsum);
                                headlistview.setAdapter(muityListAdapter);
                            }
//                            if(headlistsum.size()==headurl.length){
//                                muityListAdapter = new ShouyOtherMuityListAdapter(getContext(),headlistsum);
//                                headlistview.setAdapter(muityListAdapter);
//                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private void addPager() {


        String adurl=bundle.getString("ad");
        new OkHttpClient().newCall(new Request.Builder().url(adurl).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("print", "run: 网络连接异常" );
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
                            if(adurllist.size()<=1){
                                rollPagerView.setHintView(null);
                            }else {
                                rollPagerView.setPlayDelay(3000);
                            }
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
    protected void loadData() {


    }

    @Override
    protected void requestNetData() {
        String cid = bundle.getString("cid");
        String goodurl="http://www.yangtaotop.com/appapi/main/hot_products?p="+page+"&cid="+cid+"&num=10";
        new OkHttpClient().newCall(new Request.Builder().url(goodurl).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("print", "run: 网络连接异常" );
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
                            swipeRefreshLayout.setRefreshing(false);
                            if(page==1){
                                infoList.clear();
                            }
                            if(listView.getFooterViewsCount()==0){
                                listView.addFooterView(footview);
                            }
                            //注意这里:
                            if(list.size()<10){
                                listView.removeFooterView(footview);
                                Toast.makeText(getActivity(), "数据加载完毕..", Toast.LENGTH_SHORT).show();
                            }

                            if(list!=null){
                                infoList.addAll(list);
                                adapter.setCount((infoList.size()+1)/2);
//                    adapter.notifyDataSetChanged();
                            }
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
        return R.layout.shouye_fragment_newselector;
    }
}
