package com.example.administrator.yangtao.modules.MyLogin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.modules.MyLogin.bean.MyTopicBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class MyTopicAdapter extends RecyclerView.Adapter<MyTopicAdapter.MyTopicViewHolder> {

    List<MyTopicBean> data;
    Context context;

    public MyTopicAdapter(List<MyTopicBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyTopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mytopic, null);
        return new MyTopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyTopicViewHolder holder, final int position) {

        final MyTopicBean bean = data.get(position);
        holder.item_mytopic_title.setText(bean.getTopic_title());
        holder.item_mytopic_num.setText(bean.getTopic_total() + "条内容");

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

    class MyTopicViewHolder extends RecyclerView.ViewHolder {


        TextView item_mytopic_title, item_mytopic_num;

        public MyTopicViewHolder(View itemView) {
            super(itemView);
            item_mytopic_title = (TextView) itemView.findViewById(R.id.item_mytopic_title);
            item_mytopic_num = (TextView) itemView.findViewById(R.id.item_mytopic_num);

        }
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(MyTopicBean bean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
