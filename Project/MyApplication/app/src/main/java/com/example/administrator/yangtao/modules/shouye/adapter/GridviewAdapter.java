package com.example.administrator.yangtao.modules.shouye.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.modules.shouye.bean.SortInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class GridviewAdapter extends BaseAdapter{

    private final Context context;
    private final List<SortInfo> infos;


    public GridviewAdapter(Context context, List<SortInfo> titles) {
        this.context =context;
        infos =titles;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.shouye_gridview_item, null);
        TextView title =  (TextView) view.findViewById(R.id.shouyegridview_tv_title);
        title.setText(infos.get(position).getTitle());
        ImageView imageView= (ImageView) view.findViewById(R.id.shouyegridview_iv_image);
        ImageLoader.getInstance().displayImage(infos.get(position).getImage_path(),imageView);
        return view;

    }
}
