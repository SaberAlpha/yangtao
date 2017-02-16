package com.example.administrator.yangtao.modules.shouye.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.modules.shouye.bean.ShouyeRankInfo;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class ShouyeRolladapter extends LoopPagerAdapter{

    private final Context context;
    private final List<ShouyeRankInfo> adInfos;

    public ShouyeRolladapter(RollPagerView rollPagerView, Context context, List<ShouyeRankInfo> adurllist) {
        super(rollPagerView);
        this.context =context;
        adInfos =adurllist;

    }

    @Override
    public View getView(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_top, null);
        ImageView imageView= (ImageView) view.findViewById(R.id.top_fragment_iamge);
//        LinearLayout layout=new LinearLayout(context);
//        layout.setOrientation(LinearLayout.HORIZONTAL);
//        ImageView imageView=new ImageView(context);
        String image = adInfos.get(position).getImage_path();
        Log.e("print", "getView:image LLLLLLLLL "+image );
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        imageView.setAdjustViewBounds(true);
//        imageView.setMaxHeight(100);
//        imageView.setMaxWidth(100);

        ImageLoader.getInstance().displayImage(image,imageView);
//        layout.addView(imageView,0);
//        LinearLayout childlayout=new LinearLayout(context);
//        childlayout.setOrientation(LinearLayout.VERTICAL);
//        childlayout.setWeightSum(2);
        TextView titletext= (TextView) view.findViewById(R.id.top_fragment_title);
//        TextView titletext=new TextView(context);
        titletext.setTextColor(Color.BLACK);
        Log.e("print", "getView: etTitle(  " +adInfos.get(position).getTitle()+adInfos.get(position).getDescp());
        titletext.setText(adInfos.get(position).getTitle());
//        childlayout.addView(titletext,0);
        TextView contenttext= (TextView) view.findViewById(R.id.top_fragment_content);
//        TextView contenttext=new TextView(context);
        contenttext.setText(adInfos.get(position).getDescp());
        TextView pricetext= (TextView) view.findViewById(R.id.top_fragment_price);
        pricetext.setText("￥  "+adInfos.get(position).getFs_price());
//        childlayout.addView(contenttext,1);
//
//        layout.addView(childlayout);

//        Picasso.with(context).load(image).into(imageView);
        return view;
    }


    @Override
    public int getRealCount() {
        return adInfos.size();
    }

}
