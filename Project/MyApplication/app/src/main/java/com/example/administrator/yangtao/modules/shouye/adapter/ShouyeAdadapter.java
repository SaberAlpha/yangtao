package com.example.administrator.yangtao.modules.shouye.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.yangtao.modules.shouye.bean.ShouyeAdInfo;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class ShouyeAdadapter extends LoopPagerAdapter{

    private final Context context;
    private final List<ShouyeAdInfo> adInfos;

    public ShouyeAdadapter(RollPagerView rollPagerView, Context context, List<ShouyeAdInfo> adurllist) {
        super(rollPagerView);
        this.context =context;
        adInfos =adurllist;

    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        String image = adInfos.get(position).getImage();
        Picasso.with(context).load(image).into(imageView);

        return imageView;
    }

    @Override
    public int getRealCount() {
        return adInfos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return adInfos.get(position).getTitle();
    }
}
