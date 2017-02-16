package com.example.administrator.yangtao.modules.shouye.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/11/27.
 */
public class ShouyeGridview extends GridView {
    public ShouyeGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //将listview的高度尽量设置到最大值
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
