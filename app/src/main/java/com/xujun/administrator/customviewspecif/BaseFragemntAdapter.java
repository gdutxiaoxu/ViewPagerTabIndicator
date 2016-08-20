package com.xujun.administrator.customviewspecif;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 博客地址：http://blog.csdn.net/gdutxiaoxu
 * @author xujun
 * @time 2016/8/15 10:12.
 */
public class BaseFragemntAdapter extends FragmentPagerAdapter {

    protected List<Fragment> mFragmentList;


    public BaseFragemntAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return isEmpty()?0:mFragmentList.size();
    }

    public boolean isEmpty(){
        return mFragmentList==null|| mFragmentList.size()==0;
    }
}
