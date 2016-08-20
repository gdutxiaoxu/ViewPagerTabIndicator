package com.xujun.administrator.customviewspecif.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @ explain:
 * @ author：xujun on 2016/8/15 10:39
 * @ email：gdutxiaoxu@163.com
 */
public abstract class BaseTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        onPreTransform(page,position);
        onTransform(page,position);

    }

    protected abstract void onTransform(View page, float position) ;

    protected void onPreTransform(View page, float position) {
        final float width = page.getWidth();

        page.setRotationX(0);
        page.setRotationY(0);
        page.setRotation(0);

        page.setScaleX(1);
        page.setScaleY(1);

        page.setPivotX(0);
        page.setPivotY(0);

        page.setTranslationY(0);
        page.setTranslationX(isPagingEnabled() ? 0f : -width * position);

        if (hideOffscreenPages()) {
            page.setAlpha(position <= -1f || position >= 1f ? 0f : 1f);
            page.setEnabled(false);
        } else {
            page.setEnabled(true);
            page.setAlpha(1f);
        }
    }

    protected boolean hideOffscreenPages() {
        return true;
    }

    protected boolean isPagingEnabled() {
        return false;
    }
}
