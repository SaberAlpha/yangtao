package com.example.administrator.yangtao.modules.shouye.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.activity.SearchActivity;
import com.example.administrator.yangtao.adapter.CommonAdapter;
import com.example.administrator.yangtao.adapter.ViewHolder;
import com.example.administrator.yangtao.modules.shouye.bean.BaseShouyeInfo;
import com.example.administrator.yangtao.modules.shouye.bean.FindGoodInfo;
import com.example.administrator.yangtao.modules.shouye.bean.HotInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ModleInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeRankInfo;
import com.example.administrator.yangtao.modules.shouye.bean.SortInfo;
import com.example.administrator.yangtao.modules.shouye.widget.ShouyeGridview;
import com.example.administrator.yangtao.modules.shouye.widget.ShouyeListview;
import com.jude.rollviewpager.RollPagerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 * 如果要实现多布局就必须还要实现另外两个方法：
 * 1.getViewTypeCount
 * 2.getItemViewType
 */
public class ShouyeMuityListAdapter extends BaseAdapter implements View.OnClickListener {

    Context context;

    List<List<BaseShouyeInfo>> list;

    private int a=0,b=0,c=0,d=0,e=0;

    public ShouyeMuityListAdapter(Context context, List<List<BaseShouyeInfo>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO 初始化控件
        int type = list.get(position).get(0).getType();
        BaseViewHolder viewHolder = null;

            if (convertView == null) {
                switch (type) {
                    case BaseShouyeInfo.Types.TYPE_SORT:
                        convertView = LayoutInflater.from(context).inflate(R.layout.shouye_sort_item, parent, false);
                        viewHolder = new SortViewHolder(convertView);
                        break;
                    case BaseShouyeInfo.Types.TYPE_RANK:
                        convertView = LayoutInflater.from(context).inflate(R.layout.shouye_rank_viewpager, parent, false);
                        viewHolder = new RankViewHolder(convertView);
                        break;
                    case BaseShouyeInfo.Types.TYPE_MODLE:
                        convertView = LayoutInflater.from(context).inflate(R.layout.shouye_menu_item, parent, false);
                        viewHolder = new ModleViewHolder(convertView);
                        break;
                    case BaseShouyeInfo.Types.TYPE_HOT:
                        convertView = LayoutInflater.from(context).inflate(R.layout.shouye_hot_rank, parent, false);
                        viewHolder = new HotViewHolder(convertView);
                        break;
                    case BaseShouyeInfo.Types.TYPE_GOOD:
                        convertView = LayoutInflater.from(context).inflate(R.layout.shouye_findgood_item, parent, false);
                        viewHolder = new GoodViewHolder(convertView);
                        break;
                }
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (BaseViewHolder) convertView.getTag();
            }
            //TODO 填充数据
        List<BaseShouyeInfo> headlist = list.get(position);
        switch (type) {
                case BaseShouyeInfo.Types.TYPE_SORT:
                        dosort(viewHolder, headlist);
                    break;
                case BaseShouyeInfo.Types.TYPE_RANK:
                        doRank(viewHolder,headlist);
                    break;
                case BaseShouyeInfo.Types.TYPE_MODLE:
                        domodle(viewHolder,headlist);
                    break;
                case BaseShouyeInfo.Types.TYPE_HOT:
                    dohot(viewHolder,headlist);
                    break;
                case BaseShouyeInfo.Types.TYPE_GOOD:
                        dofindgood(viewHolder,headlist);
                    break;

            }
            return convertView;
    }

    private void dohot(BaseViewHolder viewHolder, List<BaseShouyeInfo> list) {
        List<HotInfo> infoList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getType()==3){
                infoList.add((HotInfo) list.get(i));
            }
        }
        HotViewHolder holder= (HotViewHolder) viewHolder;

       for(int i=0;i<infoList.size();i++){
          switch (infoList.get(i).getKeys()){
              case "jp_wind_vane":
                  ImageLoader.getInstance().displayImage(infoList.get(i).getImage_path(),holder.firstimage);
                  holder.firstview.setTag(infoList.get(i));
                  holder.firsttitle.setText(infoList.get(i).getTitle());
                  holder.firstcontent.setText(infoList.get(i).getIntro());
                  break;
              case "itemkey":
                  if(a==0){
                      holder.top1view.setTag(infoList.get(i));
                      holder.top1title.setText(infoList.get(i).getTitle());
                      holder.top1price.setText("￥  "+infoList.get(i).getProd_price());
                      ImageLoader.getInstance().displayImage(infoList.get(i).getItem_image(),holder.top1image);
                      a++;
                  }else if(a==1){
                      holder.top2view.setTag(infoList.get(i));
                      holder.top2title.setText(infoList.get(i).getTitle());
                      holder.top2price.setText("￥  "+infoList.get(i).getProd_price());
                      ImageLoader.getInstance().displayImage(infoList.get(i).getItem_image(),holder.top2image);
                      a++;
                  }else if(a==2){
                      holder.top3view.setTag(infoList.get(i));
                      holder.top3title.setText(infoList.get(i).getTitle());
                      holder.top3price.setText("￥  "+infoList.get(i).getProd_price());
                      ImageLoader.getInstance().displayImage(infoList.get(i).getItem_image(),holder.top3image);
                      a++;
                  }
                  break;
              case "cn_wind_vane":
                  holder.secondview.setTag(infoList.get(i));
                  ImageLoader.getInstance().displayImage(infoList.get(i).getImage_path(),holder.secondimage);
                  holder.secondtitle.setText(infoList.get(i).getTitle());
                  holder.secondcontent.setText(infoList.get(i).getIntro());
                  break;
              case "black_wind_vane":
                  holder.thirdview.setTag(infoList.get(i));
                  ImageLoader.getInstance().displayImage(infoList.get(i).getImage_path(),holder.thirdimage);
                  holder.thirdtitle.setText(infoList.get(i).getTitle());
                  holder.thirdcontent.setText(infoList.get(i).getIntro());
                  break;
          }
       }
        holder.firstview.setOnClickListener(this);
        holder.secondview.setOnClickListener(this);
        holder.thirdview.setOnClickListener(this);
        holder.top1view.setOnClickListener(this);
        holder.top2view.setOnClickListener(this);
        holder.top3view.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hot_first:
                Toast.makeText(context, ((HotInfo)v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.hot_second:
                Toast.makeText(context, ((HotInfo)v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, SearchActivity.class));
                break;
            case R.id.hot_third:
                Toast.makeText(context, ((HotInfo)v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.hot_top1:
                Toast.makeText(context, ((HotInfo)v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.hot_top2:
                Toast.makeText(context, ((HotInfo)v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.hot_top3:
                Toast.makeText(context, ((HotInfo)v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void dofindgood(BaseViewHolder viewHolder, List<BaseShouyeInfo> list) {

        List<FindGoodInfo> infoList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getType()==4){
                infoList.add((FindGoodInfo) list.get(i));
            }
        }
        GoodViewHolder holder= (GoodViewHolder) viewHolder;
        ListView listview=holder.listview;
        listview.setAdapter(new CommonAdapter<FindGoodInfo>(context,infoList,R.layout.shouye_findgood_listview_item) {
            @Override
            public void convert(ViewHolder helper, int position, FindGoodInfo item) {
                helper.setImageByUrl(R.id.shouye_image_item,item.getImage_path());
                helper.setText(R.id.shouye_findgood_title,item.getTitle());
                helper.setText(R.id.shouye_findgood_num,item.getView_num());
            }
        });

    }

    private void domodle(BaseViewHolder viewHolder, List<BaseShouyeInfo> list) {
        List<ModleInfo> infoList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getType()==2){
                infoList.add((ModleInfo) list.get(i));
            }
        }
        ModleViewHolder holder= (ModleViewHolder) viewHolder;
        ListView listview= holder.listview;
        listview.setAdapter(new CommonAdapter<ModleInfo>(context,infoList,R.layout.shouye_image_item) {
            @Override
            public void convert(ViewHolder helper, int position, ModleInfo item) {
                helper.setImageByUrl(R.id.shouye_image_item,item.getImage_path());
            }
        });

    }

    private void doRank(BaseViewHolder viewHolder, List<BaseShouyeInfo> list) {
        List<ShouyeRankInfo> infoList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getType()==1){
                infoList.add((ShouyeRankInfo) list.get(i));
            }
        }
        RankViewHolder holder= (RankViewHolder) viewHolder;
        RollPagerView rollpagerview = holder.rollpagerview;
//        rollpagerview.setHintView(null);
        ShouyeRolladapter rolladapter=new ShouyeRolladapter(rollpagerview,context,infoList);
        rollpagerview.setAdapter(rolladapter);

    }

    private void dosort(BaseViewHolder viewHolder, List<BaseShouyeInfo> list) {
        List<SortInfo> infoList=new ArrayList<>();
       for(int i=0;i<list.size();i++){
           if(list.get(i).getType()==0){
               infoList.add((SortInfo) list.get(i));
           }
       }
        SortViewHolder holder= (SortViewHolder) viewHolder;
        GridView gridview = holder.gridview;
        gridview.setNumColumns(4);
        gridview.setAdapter(new CommonAdapter<SortInfo>(context,infoList,R.layout.shouye_gridview_item) {
            @Override
            public void convert(ViewHolder helper, int position, SortInfo item) {
                helper.setText(R.id.shouyegridview_tv_title,item.getTitle());
                helper.setImageByUrl(R.id.shouyegridview_iv_image,item.getImage_path());
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, ">>"+position, Toast.LENGTH_SHORT).show();

            }
        });
    }
    /**
     * 返回所有类型的数量
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 5;
    }

    /**
     * 返回某个位置的item类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return list.get(position).get(1).getType();
    }


    class BaseViewHolder {

    }
    class SortViewHolder extends BaseViewHolder{
        ShouyeGridview gridview;
        public SortViewHolder(View converView){
            gridview= ( ShouyeGridview) converView.findViewById(R.id.shouye_sort_gridview);
        }
    }
    class RankViewHolder extends BaseViewHolder{
        RollPagerView rollpagerview;
        public RankViewHolder(View converView){
            rollpagerview= (RollPagerView) converView.findViewById(R.id.shouye_rank_rollpagerview);
        }
    }
    class ModleViewHolder extends BaseViewHolder{
        ShouyeListview listview;
        public ModleViewHolder(View converView){
            listview= (ShouyeListview) converView.findViewById(R.id.shouye_menu_listview);
        }
    }
    class HotViewHolder extends BaseViewHolder{
        View firstview,secondview,thirdview,top1view,top2view,top3view;
        ImageView top1image,top2image,top3image,firstimage,secondimage,thirdimage;
        TextView top1title,top2title,top3title,top1price,top2price,top3price, firsttitle,firstcontent,secondtitle,secondcontent
                ,thirdtitle,thirdcontent;
        public HotViewHolder(View converView){
            top1image= (ImageView) converView.findViewById(R.id.shouye_top1_image);
            top2image= (ImageView) converView.findViewById(R.id.shouye_top2_image);
            top3image= (ImageView) converView.findViewById(R.id.shouye_top3_image);
            firstimage= (ImageView) converView.findViewById(R.id.shouye_hot_rank1_image);
            secondimage= (ImageView) converView.findViewById(R.id.shouye_hot_rank2_image);
            thirdimage= (ImageView) converView.findViewById(R.id.shouye_hot_rank3_image);
            top1title= (TextView) converView.findViewById(R.id.shouye_hottop1_title);
            top2title= (TextView) converView.findViewById(R.id.shouye_hottop2_title);
            top3title= (TextView) converView.findViewById(R.id.shouye_hottop3_title);
            top1price= (TextView) converView.findViewById(R.id.shouye_hottop1_price);
            top2price= (TextView) converView.findViewById(R.id.shouye_hottop2_price);
            top3price= (TextView) converView.findViewById(R.id.shouye_hottop3_price);
           firsttitle= (TextView) converView.findViewById(R.id.shouye_hot_rank_text);
            secondtitle= (TextView) converView.findViewById(R.id.shouye_hot_rank_text2);
           thirdtitle= (TextView) converView.findViewById(R.id.shouye_hot_rank_text3);
            firstcontent= (TextView) converView.findViewById(R.id.shouye_contenttext);
            secondcontent= (TextView) converView.findViewById(R.id.shouye_contenttext2);
            thirdcontent= (TextView) converView.findViewById(R.id.shouye_contenttext3);
            firstview=converView.findViewById(R.id.hot_first);
            secondview=converView.findViewById(R.id.hot_second);
            thirdview=converView.findViewById(R.id.hot_third);
            top1view=converView.findViewById(R.id.hot_top1);
            top2view=converView.findViewById(R.id.hot_top2);
            top3view=converView.findViewById(R.id.hot_top3);

        }
    }
    class GoodViewHolder extends BaseViewHolder{
        ShouyeListview listview;
        public GoodViewHolder(View converView){
        listview= (ShouyeListview) converView.findViewById(R.id.shouye_findgood_listview);
        }
    }



}
