package com.xujun.administrator.customviewspecif.transformer;

import android.support.v4.view.ViewPager;

/**
 * @ explain:
 * @ author：xujun on 2016/8/15 10:54
 * @ email：gdutxiaoxu@163.com
 */
public class TransFormerItem {

    public final String title;
    public final Class<? extends ViewPager.PageTransformer> clazz;
    public ViewPager.PageTransformer mPageTransformer;

    public TransFormerItem(Class<? extends ViewPager.PageTransformer> clazz) {
        this.clazz = clazz;
        try {
            mPageTransformer=clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        title = clazz.getSimpleName();
    }

    @Override
    public String toString() {
        return title;
    }

}
