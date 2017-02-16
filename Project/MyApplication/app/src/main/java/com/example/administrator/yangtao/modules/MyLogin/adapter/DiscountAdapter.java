package com.example.administrator.yangtao.modules.MyLogin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.modules.MyLogin.bean.DiscountBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {

    List<DiscountBean> data;
    Context context;

    public DiscountAdapter(List<DiscountBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public DiscountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mydiscount, null);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiscountViewHolder holder, int position) {
        DiscountBean discountBean = data.get(position);
        holder.mydiscount_item_title.setText(discountBean.getSimple_title());
        holder.mydiscount_item_time.setText("截止时间" + discountBean.getFailure_time());
        holder.mydiscount_item_value.setText(discountBean.getR_value() + "元");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class DiscountViewHolder extends RecyclerView.ViewHolder {

        TextView mydiscount_item_title, mydiscount_item_time, mydiscount_item_value;

        public DiscountViewHolder(View itemView) {
            super(itemView);
            mydiscount_item_title = (TextView) itemView.findViewById(R.id.mydiscount_item_title);
            mydiscount_item_time = (TextView) itemView.findViewById(R.id.mydiscount_item_time);
            mydiscount_item_value = (TextView) itemView.findViewById(R.id.mydiscount_item_value);

        }
    }

}
