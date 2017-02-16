package com.example.administrator.yangtao.modules.MyLogin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.modules.MyLogin.bean.MyRankBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class MyRankAdapter extends RecyclerView.Adapter<MyRankAdapter.MyRankViewHolder> {

    List<MyRankBean> data;
    Context context;

    public MyRankAdapter(List<MyRankBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyRankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myrank, null);
        return new MyRankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRankViewHolder holder, final int position) {

        final MyRankBean bean = data.get(position);
        holder.item_myrank_name.setText(bean.getCatalog_name());
        ImageLoader.getInstance().displayImage(bean.getPic_urls(), holder.item_myrank_iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MyRankViewHolder extends RecyclerView.ViewHolder {

        ImageView item_myrank_iv;
        TextView item_myrank_name;

        public MyRankViewHolder(View itemView) {
            super(itemView);
            item_myrank_iv = (ImageView) itemView.findViewById(R.id.item_myrank_iv);
            item_myrank_name = (TextView) itemView.findViewById(R.id.item_myrank_name);

        }
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(MyRankBean bean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
