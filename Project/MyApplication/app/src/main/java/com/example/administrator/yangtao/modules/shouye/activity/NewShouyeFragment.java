package com.example.administrator.yangtao.modules.shouye.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
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
import com.example.administrator.yangtao.modules.shouye.adapter.ShouyeAdadapter;
import com.example.administrator.yangtao.modules.shouye.adapter.ShouyeMuityListAdapter;
import com.example.administrator.yangtao.modules.shouye.bean.BaseShouyeInfo;
import com.example.administrator.yangtao.modules.shouye.bean.FindGoodInfo;
import com.example.administrator.yangtao.modules.shouye.bean.HotInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ModleInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeAdInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeGoodInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeRankInfo;
import com.example.administrator.yangtao.modules.shouye.bean.SortInfo;
import com.example.administrator.yangtao.modules.shouye.widget.ShouyeListview;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/25.
 */
public class NewShouyeFragment extends BaseFragment {

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommonAdapter<ShouyeGoodInfo> adapter;
    private List<ShouyeGoodInfo> infoList;
    private int page=1;
    private View footview;
    private View headview;
    private RollPagerView rollPagerView;
    private List<ShouyeAdInfo> adurllist;
    String[]  headurl={"http://www.yangtaotop.com/appapi/banner/carousels?pos_id=2","http://www.yangtaotop.com/appapi/main/flash_sales?pos_id=3",
    "http://www.yangtaotop.com/appapi/banner/carousels?pos_id=4","http://www.yangtaotop.com/appapi/main/wind_vanes?pos_id=51"
    ,"http://www.yangtaotop.com/appapi/main/specials?pos_id=6&s_type=2"};
    private int headId=-1;
    private ShouyeMuityListAdapter muityListAdapter;
    private ShouyeListview headlistview;
    private String TAG="NewShouyeFragment";
    private List<List<BaseShouyeInfo>> headlistsum;

    @Override
    protected void findViews(View view) {

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.shouye_swipeview);
        listView= (ListView) view.findViewById(R.id.shouye_selector_listview);


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

        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(!TextUtils.isEmpty(adurllist.get(position).getRedirect_url())){
                    Intent intent=new Intent(getActivity(),ADwebviewActivity.class);
                    intent.putExtra("uri",adurllist.get(position).getRedirect_url());
                    intent.putExtra("title",adurllist.get(position).getTitle());
                    startActivity(intent);
                }
            }
        });

        headlistview = (ShouyeListview) headview.findViewById(R.id.shouye_newlist_item);;
    }

    private void addData() {
        for(int i=0;i<headurl.length;i++){
            headId = i;
            Log.e(TAG, "addData: "+i+"???????"+ headId+"--------->"+headurl[i] );
            headloaddata(headId);
            SystemClock.sleep(2000);
        }

//        headId++;
//        Log.e(TAG, "addData: "+"???????"+ headId+"--------->" );
//        headloaddata(headId);

    }

    private void headloaddata(final int headId) {
        final List<BaseShouyeInfo> headlist=new ArrayList<>();
//        if(headId>=headurl.length){
//            return;
//        }
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
                                rankInfo.setFs_price(jsonObject1.getString("fs_price"));
                                rankInfo.setProd_id(jsonObject1.getString("prod_id"));
                                rankInfo.setEnd_date(jsonObject1.getString("end_date"));
                                rankInfo.setTitle(jsonObject1.getString("title"));
                                rankInfo.setDescp(jsonObject1.getString("descp"));
                                headlist.add(rankInfo);
                            }
                            break;
                        case BaseShouyeInfo.Types.TYPE_MODLE:
                            JSONArray jsonArray2 = jsonObject.getJSONArray("data");
                            ModleInfo modleInfo=null;
                            Log.e(TAG, "onResponse:>>>>>>>>>2 "+json );
                            for(int i=0;i<jsonArray2.length();i++){
                                modleInfo=new ModleInfo();
                                JSONObject jsonObject2 = jsonArray2.getJSONObject(i);
                                modleInfo.setType(headId);
                                modleInfo.setImage_path(jsonObject2.getString("image_path"));
                                modleInfo.setTitle(jsonObject2.getString("title"));
                                modleInfo.setTarget_id(jsonObject2.getString("target_id"));
                                modleInfo.setR_type(jsonObject2.getString("r_type"));
                                headlist.add(modleInfo);
                            }
                            break;
                        case BaseShouyeInfo.Types.TYPE_HOT:
                            JSONObject subjson = jsonObject.getJSONObject("data");
                            HotInfo hotInfo=null;
                            Iterator<String> keys = subjson.keys();
                            Log.e(TAG, "onResponse:>>>>>>>>>3 "+json );
                            while (keys.hasNext()){
                                String next = keys.next();
                                JSONObject object = subjson.getJSONObject(next);
                                hotInfo=new HotInfo();
                                hotInfo.setType(headId);
                                hotInfo.setTitle(object.getString("title"));
                                hotInfo.setImage_path(object.getString("image_path"));
                                hotInfo.setIntro(object.getString("intro"));
                                hotInfo.setPos_id(object.getString("pos_id"));
                                hotInfo.setWv_id(object.getString("wv_id"));
                                hotInfo.setKeys(next);
                                headlist.add(hotInfo);
                                Log.e(TAG, "onResponse: keys.next()"+next );
                                if(next.equals("jp_wind_vane")){
                                    JSONArray prods = object.getJSONArray("prods");
                                    for(int i=0;i<prods.length();i++){
                                        hotInfo=new HotInfo();
                                        JSONObject prodsJSONObject = prods.getJSONObject(i);
                                        hotInfo.setType(headId);
                                        hotInfo.setItem_image(prodsJSONObject.getString("image_path"));
                                        hotInfo.setProd_id(prodsJSONObject.getString("prod_id"));
                                        hotInfo.setProd_name(prodsJSONObject.getString("prod_name"));
                                        hotInfo.setProd_price(prodsJSONObject.getString("prod_price"));
                                        hotInfo.setKeys("itemkey");
                                        headlist.add(hotInfo);
                                    }
                                }
                            }
                            break;
                        case BaseShouyeInfo.Types.TYPE_GOOD:
                            Log.e(TAG, "onResponse:>>>>>>>>>4 "+json );
                            JSONArray jsonArray4 = jsonObject.getJSONArray("data");
                            FindGoodInfo goodInfo=null;
                            for(int i=0;i<jsonArray4.length();i++){
                                goodInfo=new  FindGoodInfo();
                                JSONObject jsonObject4 = jsonArray4.getJSONObject(i);
                                goodInfo.setType(headId);
                                goodInfo.setImage_path(jsonObject4.getString("image_path"));
                                goodInfo.setS_id(jsonObject4.getString("s_id"));
                                goodInfo.setTitle(jsonObject4.getString("title"));
                                goodInfo.setView_num(jsonObject4.getString("view_num"));
                                goodInfo.setS_type(jsonObject4.getString("s_type"));
                                headlist.add(goodInfo);
                            }
                            break;
                    }

                    headlistsum.add(headlist);

                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
//                            if(headId<headurl.length-1){
//                                Log.e(TAG, "run:>>>>>> "+headId );
//                                addData();
//                            }else if(headId==headurl.length-1){
//                                muityListAdapter = new ShouyeMuityListAdapter(getContext(),headlistsum);
//                                headlistview.setAdapter(muityListAdapter);
//                            }
                            if(headlistsum.size()==5){
                                muityListAdapter = new ShouyeMuityListAdapter(getContext(),headlistsum);
                                headlistview.setAdapter(muityListAdapter);
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private void addPager() {


        String adurl="http://www.yangtaotop.com/appapi/banner/carousels?pos_id=1";
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
                        adInfo.setRedirect_url(object.getString("redirect_url"));
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
        String goodurl="http://www.yangtaotop.com/appapi/main/hot_products?p="+page+"&cid=1&num=10";
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
