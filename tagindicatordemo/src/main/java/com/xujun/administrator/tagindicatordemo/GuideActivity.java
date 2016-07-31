package com.xujun.administrator.tagindicatordemo;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xujun.administrator.tagindicatordemo.base.BaseActivity;
import com.xujun.administrator.tagindicatordemo.base.BaseViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {
    private static final String TAG = "xujun";

    private HorizontalScrollView horizontalScrollView;
    private LinearLayout tabsContainer;
    private ViewPager mViewPager;
    private ArrayList<KeyValue> tabs = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    private String[] mTitles;
    private BaseViewPagerAdapter mPagerAdapter;
    private int lastScrollX = 0;
    private int scrollOffset = 10;// 当显示多个tab时，底部导航线距离左侧的位移,默认是10，请不要太小
    private int tabPadding=15;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        tabsContainer = (LinearLayout) findViewById(R.id.ll_title);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

    }

    @Override
    protected void initData() {
        //1：未确认；2：待支付；3：已支付；4：已使用；5；用户退订；6：停车场退订；7：退款中；8；已退款


        TestFragment testFragment = new TestFragment();
        TestFragment testFragment1 = new TestFragment();
        TestFragment testFragment2 = new TestFragment();
        TestFragment testFragment3 = new TestFragment();
        TestFragment testFragment4 = new TestFragment();
        TestFragment testFragment5 = new TestFragment();
        TestFragment testFragment6 = new TestFragment();
        TestFragment testFragment7 = new TestFragment();
        mFragmentList.add(testFragment);
        mFragmentList.add(testFragment1);
        mFragmentList.add(testFragment2);
        mFragmentList.add(testFragment3);
        mFragmentList.add(testFragment4);
        mFragmentList.add(testFragment5);
        mFragmentList.add(testFragment6);
        mFragmentList.add(testFragment7);
        mTitles = this.getResources().getStringArray(R.array.fragments_titles);
        mPagerAdapter = new BaseViewPagerAdapter(getSupportFragmentManager(), mFragmentList,
                mTitles);

        mViewPager.setAdapter(mPagerAdapter);
        int count = mPagerAdapter.getCount();
        for(int  i=0;i<count;i++){
            String pageTitle = mPagerAdapter.getPageTitle(i).toString();
            addTextTab(i,pageTitle);
        }
        mViewPager.addOnPageChangeListener(new PagerListener());
    }

    class PagerListener extends ViewPager.SimpleOnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int width = tabsContainer.getChildAt(position).getWidth();
            Log.i(TAG, "onPageScrolled:width=" +width);

            int xDistance = (int) (positionOffset * width);
            scrollToChild(position,xDistance);
        }

        @Override
        public void onPageSelected(int position) {
           for(int  i=0;i<mPagerAdapter.getCount();i++){
               View child = tabsContainer.getChildAt(i);
               if(child instanceof TextView){
                   ((TextView) child).setTextColor(position==i? Color.RED : Color.BLACK);
               }

           }
        }
    }


    private void scrollToChild(int position, int offset) {
        if ( offset == 0) {
            return;
        }


        View view = tabsContainer.getChildAt(position);
        int left = view.getLeft();
        int newScrollX = left + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            horizontalScrollView.scrollTo(newScrollX, 0);
        }

    }

    private void addTextTab(final int position, String title) {

        TextView tab = new TextView(this);
        tab.setText(title);
        tab.setTextSize(25);
        tab.setFocusable(true);
        tab.setGravity(Gravity.CENTER);
        tab.setSingleLine();
        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(position);
            }
        });


        tab.setPadding(tabPadding, 0, tabPadding, 0);

        tabsContainer.addView(tab, position);
    }
}
