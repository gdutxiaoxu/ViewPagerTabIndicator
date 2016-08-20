package com.xujun.administrator.customviewspecif;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * 博客地址：http://blog.csdn.net/gdutxiaoxu
 * @author xujun
 * @time 2016/8/15 10:15.
 */
public class BasePagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
