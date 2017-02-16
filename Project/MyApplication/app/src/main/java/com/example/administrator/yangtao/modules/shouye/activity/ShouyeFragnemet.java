package com.example.administrator.yangtao.modules.shouye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.activity.BaseFragment;
import com.example.administrator.yangtao.activity.SearchActivity;
import com.example.administrator.yangtao.modules.shouye.adapter.shouyefragmentadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class ShouyeFragnemet extends BaseFragment implements View.OnClickListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    String[] titles={"精选","美妆个护","母婴","营养健康","箱包配饰","日用综合"};
    List<Fragment> fragments=new ArrayList<>();
    private FragmentPagerAdapter adapter;
    String[]  adurls={"http://www.yangtaotop.com/appapi/main/flash_sales?pos_id=3","http://www.yangtaotop.com/appapi/banner/carousels?pos_id=14",
    "http://www.yangtaotop.com/appapi/banner/carousels?pos_id=19","http://www.yangtaotop.com/appapi/banner/carousels?pos_id=24","http://www.yangtaotop.com/appapi/banner/carousels?pos_id=29",
    "http://www.yangtaotop.com/appapi/banner/carousels?pos_id=34"};

    String[] contenturi={"http://www.yangtaotop.com/appapi/main/hot_products?p=","http://www.yangtaotop.com/appapi/main/hot_products?p=",
    "http://www.yangtaotop.com/appapi/main/hot_products?p=","http://www.yangtaotop.com/appapi/main/hot_products?p="
    ,"http://www.yangtaotop.com/appapi/main/hot_products?p=","http://www.yangtaotop.com/appapi/main/hot_products?p="};

    List<String[]> headuri=new ArrayList<>();

    @Override
    protected void findViews(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.shouye_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.shouye_Tablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        headuri.add(new String[]{"http://www.yangtaotop.com/appapi/banner/carousels?pos_id=15","http://www.yangtaotop.com/appapi/banner/carousels?pos_id=16"});
        headuri.add(new String[]{"http://www.yangtaotop.com/appapi/banner/carousels?pos_id=20","http://www.yangtaotop.com/appapi/banner/carousels?pos_id=21",
                "http://www.yangtaotop.com/appapi/channel/posts?pos_id=22"});
        headuri.add(new String[]{"http://www.yangtaotop.com/appapi/banner/carousels?pos_id=25","http://www.yangtaotop.com/appapi/banner/carousels?pos_id=26"});
        headuri.add(new String[]{"http://www.yangtaotop.com/appapi/banner/carousels?pos_id=30"});
        headuri.add(new String[]{"http://www.yangtaotop.com/appapi/banner/carousels?pos_id=35"});
        viewPager.setOffscreenPageLimit(3);

        (view.findViewById(R.id.shouye_searchtext)).setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

        Fragment shouyefragment=new NewShouyeFragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("title",titles[0]);
        shouyefragment.setArguments(bundle1);
        fragments.add(shouyefragment);

        for(int i=1;i<titles.length;i++){
            Fragment fragment=new ShouyeOtherFragment();
            Bundle bundle=new Bundle();
            bundle.putString("title",titles[i]);
            bundle.putString("ad",adurls[i]);
            bundle.putString("content",contenturi[i]);
            bundle.putStringArray("head",headuri.get(i-1));
            bundle.putString("cid",(i+1)+"");
//            bundle.putString("");
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        adapter = new shouyefragmentadapter(getFragmentManager(),getContext(),fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shouye;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }
}
