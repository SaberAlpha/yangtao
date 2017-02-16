package com.example.administrator.yangtao.modules.MyLogin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.i.BaseCallBack;
import com.example.administrator.yangtao.modules.MyLogin.bean.MyFavoriteBean;
import com.example.administrator.yangtao.modules.MyLogin.net.LBHttpUtil;
import com.example.administrator.yangtao.modules.MyLogin.util.LoginInfoUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class MyFavoriteAdapter extends RecyclerView.Adapter<MyFavoriteAdapter.MyFavoriteViewHolder> {

    String url = "http://www.yangtaotop.com/appapi/mine/collection?type=2&action=102&";
    List<MyFavoriteBean> data;
    Context context;

    public MyFavoriteAdapter(List<MyFavoriteBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myfavorite, null);
        return new MyFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyFavoriteViewHolder holder, final int position) {

        final MyFavoriteBean bean = data.get(position);
        holder.item_myfavorite_name.setText(bean.getC_name().substring(0, 2));
        holder.item_myfavorite_price.setText("￥" + bean.getPrice());
        ImageLoader.getInstance().displayImage(bean.getPic_url(), holder.item_myfavorite_iv);

        holder.item_myfavorite_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LBHttpUtil.doHttpGetWithHead(url + "id=" + bean.getId() + "&related_id=" + bean.getRelated_id(),
                        LoginInfoUtil.getAuthToken(), new BaseCallBack() {
                            @Override
                            public void success(Object o) {
                                String result = (String) o;
                                Log.d("lb", result);
                                try {
                                    JSONObject object = new JSONObject(result);
                                    if (object.getString("status").equals("200")) {
                                        data.remove(data.get(position));
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(context, "取消收藏操作失败", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(int errorCode, Object data) {

                            }
                        });
            }
        });

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

    class MyFavoriteViewHolder extends RecyclerView.ViewHolder {

        ImageView item_myfavorite_iv, item_myfavorite_delete;
        TextView item_myfavorite_name, item_myfavorite_price;

        public MyFavoriteViewHolder(View itemView) {
            super(itemView);
            item_myfavorite_delete = (ImageView) itemView.findViewById(R.id.item_myfavorite_delete);
            item_myfavorite_iv = (ImageView) itemView.findViewById(R.id.item_myfavorite_iv);
            item_myfavorite_name = (TextView) itemView.findViewById(R.id.item_myfavorite_name);
            item_myfavorite_price = (TextView) itemView.findViewById(R.id.item_myfavorite_price);

        }
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(MyFavoriteBean bean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
