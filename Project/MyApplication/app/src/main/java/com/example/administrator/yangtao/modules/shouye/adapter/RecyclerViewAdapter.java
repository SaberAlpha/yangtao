package com.example.administrator.yangtao.modules.shouye.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeGoodInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BaseHolder> {

    private final Context context;
    private final List<ShouyeGoodInfo> goodInfoList;
    private OnItemClickListener onItemClickListener;

    private OnItemLongClickListener onItemLongClickListener;

    public RecyclerViewAdapter(Context context, List<ShouyeGoodInfo> datalist) {
        this.context =context;
        goodInfoList =datalist;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseHolder(LayoutInflater.from(context).inflate(R.layout.shouye_recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final BaseHolder holder, int position) {
        holder.titletext.setText(goodInfoList.get(position).getProd_name());
        holder.pricetext.setText(goodInfoList.get(position).getProd_price());
        Picasso.with(context).load(goodInfoList.get(position).getImage_path()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,holder.getLayoutPosition());
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListener!=null){
                    onItemLongClickListener.onItemLongClick(v,holder.getLayoutPosition());
                }
                //返回值决定了是否消费当前的事件，如果消费了（true），就不会传递到后面的单击事件中
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodInfoList.size();
    }

    class BaseHolder extends RecyclerView.ViewHolder{
        TextView titletext,pricetext;
        ImageView iv;
        public BaseHolder(View itemView) {
            super(itemView);
            titletext= (TextView) itemView.findViewById(R.id.shouye_recyclerview_title);
            pricetext= (TextView) itemView.findViewById(R.id.shouye_recyclerview_price);
            iv= (ImageView) itemView.findViewById(R.id.shouye_recyclerview_image);

        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
