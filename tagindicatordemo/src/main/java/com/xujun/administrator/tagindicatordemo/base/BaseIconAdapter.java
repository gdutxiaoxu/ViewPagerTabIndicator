package com.xujun.administrator.tagindicatordemo.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xujun.viewpagertabindicator.TabPagerIndicator;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/7/30 18:48
 * @ email：gdutxiaoxu@163.com
 */
public class BaseIconAdapter extends FragmentPagerAdapter implements TabPagerIndicator.IconTabProvider {

    List<Fragment> data;
    int[] resIds;

    public BaseIconAdapter(FragmentManager fm, List<Fragment> data, int[] resIds) {
        super(fm);
        this.data = data;
        this.resIds = resIds;
    }

    @Override
    public int getCount() {
        return isEmpty() ? 0 : data.size();
    }

    boolean isEmpty() {
        return data == null || data.size() == 0;
    }


    @Override
    public Fragment getItem(int position) {
        return this.data.get(position);

    }

    @Override
    public int getPageIconResId(int position) {
        return resIds[position];
    }
}
