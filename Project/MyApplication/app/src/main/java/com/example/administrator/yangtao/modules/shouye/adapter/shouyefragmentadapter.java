package com.example.administrator.yangtao.modules.shouye.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class shouyefragmentadapter extends FragmentPagerAdapter {

    private final Context context;
    private final List<Fragment> fragments;

    public shouyefragmentadapter(FragmentManager fragmentManager, Context context, List<Fragment> fragments) {
        super(fragmentManager);
        this.context =context;
        this.fragments =fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return fragments.get(position).getArguments().getString("title");
    }
}
