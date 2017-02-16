package com.example.administrator.yangtao.modules.shouye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.adapter.CommonAdapter;
import com.example.administrator.yangtao.adapter.ViewHolder;
import com.example.administrator.yangtao.modules.shouye.bean.BaseShouyeInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ModleInfo;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeRankInfo;
import com.example.administrator.yangtao.modules.shouye.bean.SortInfo;
import com.example.administrator.yangtao.modules.shouye.widget.ShouyeGridview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 * 如果要实现多布局就必须还要实现另外两个方法：
 * 1.getViewTypeCount
 * 2.getItemViewType
 */
public class ShouyOtherMuityListAdapter extends BaseAdapter {

    Context context;

    List<List<BaseShouyeInfo>> list;
    public ShouyOtherMuityListAdapter(Context context, List<List<BaseShouyeInfo>> list) {
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
                        convertView = LayoutInflater.from(context).inflate(R.layout.shouye_special_sub, parent, false);
                        viewHolder = new RankViewHolder(convertView);
                        break;
                    case BaseShouyeInfo.Types.TYPE_MODLE:
                        convertView = LayoutInflater.from(context).inflate(R.layout.shouye_arugrement, parent, false);
                        viewHolder = new ModleViewHolder(convertView);
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
            }
            return convertView;


    }


    private void domodle(BaseViewHolder viewHolder, List<BaseShouyeInfo> list) {
        List<ModleInfo> infoList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getType()==2){
                infoList.add((ModleInfo) list.get(i));
            }
        }

        ModleViewHolder holder= ( ModleViewHolder) viewHolder;
        GridView gridview = holder.gridview;
        gridview.setNumColumns(2);
        gridview.setAdapter(new CommonAdapter<ModleInfo>(context,infoList,R.layout.shouye_image_arugement) {
            @Override
            public void convert(ViewHolder helper, int position, ModleInfo item) {
                helper.setText(R.id.arugment_title,item.getTitle());
                helper.setText(R.id.arugment_name,item.getNickname());
                helper.setText(R.id.arugment_num,item.getLike_num());
                helper.setImageByUrl(R.id.shouye_image_item,item.getImage_path());
                helper.setImageByUrl(R.id.arugment_item_image,item.getAvatar());
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, ">>"+position, Toast.LENGTH_SHORT).show();

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
        GridView gridview = holder.gridview;
        gridview.setNumColumns(2);
        gridview.setAdapter(new CommonAdapter<ShouyeRankInfo>(context,infoList,R.layout.shouye_image_special) {
            @Override
            public void convert(ViewHolder helper, int position, ShouyeRankInfo item) {

                helper.setImageByUrl(R.id.shouye_image_item,item.getImage_path());
            }

        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, ">>"+position, Toast.LENGTH_SHORT).show();

            }
        });
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
        return list.size();
    }

    /**
     * 返回某个位置的item类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return list.get(position).get(0).getType();
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
        ShouyeGridview gridview;
        public RankViewHolder(View converView){
            gridview= (ShouyeGridview) converView.findViewById(R.id.shouye_sort_gridview);
        }
    }
    class ModleViewHolder extends BaseViewHolder{
        ShouyeGridview gridview;
        public ModleViewHolder(View converView){
            gridview= (ShouyeGridview) converView.findViewById(R.id.shouye_sort_gridview);
        }
    }

}
