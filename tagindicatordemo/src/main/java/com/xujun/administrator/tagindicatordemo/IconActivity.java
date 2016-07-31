package com.xujun.administrator.tagindicatordemo;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xujun.administrator.tagindicatordemo.base.BaseActivity;
import com.xujun.administrator.tagindicatordemo.base.BaseIconAdapter;
import com.xujun.viewpagertabindicator.TabPagerIndicator;

import java.util.ArrayList;

public class IconActivity extends BaseActivity implements View.OnClickListener{

    private TabPagerIndicator mPagerIndicator;
    private ViewPager mViewPager;
    private RelativeLayout mNagRoot;
    private TextView mTvTitle;
    private Button mBtnStyle;

    private ArrayList<Fragment> mFragments;
    private String[] mTitles;
    private BaseIconAdapter mPagerAdapter;
    private boolean first;
    static  final int[] mResIds=new int[]{
        R.mipmap.ic_launcher_chrome,R.mipmap.ic_launcher_gmail,
                R.mipmap.ic_launcher_gmaps,R.mipmap.ic_launcher_gplus,
            R.mipmap.ic_launcher_gplus
    };

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_pager_indicator;
    }

    @Override
    protected void initView() {
        mPagerIndicator = (TabPagerIndicator) findViewById(R.id.pagerIndicator);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mNagRoot = (RelativeLayout) findViewById(R.id.nag_root);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnStyle = (Button) findViewById(R.id.btn_style);

    }

    @Override
    protected void initListener() {
        mBtnStyle.setOnClickListener(this);
    }

    public void onColorClicked(View  view){
        int color = Color.parseColor(view.getTag().toString());
        mPagerIndicator.setIndicatorColor(color);
        mNagRoot.setBackgroundColor(color);
    }

    @Override
    protected void initData() {
        mTitles = this.getResources().getStringArray(R.array.fragments_titles);
        IconCardFragment iconCardFragment = IconCardFragment.newInstance(0);
        IconCardFragment iconCardFragment1 = IconCardFragment.newInstance(1);
        IconCardFragment iconCardFragment2 = IconCardFragment.newInstance(2);
        IconCardFragment iconCardFragment3 = IconCardFragment.newInstance(3);
        IconCardFragment iconCardFragment4 = IconCardFragment.newInstance(4);


        mFragments = new ArrayList<>();
        mFragments.add(iconCardFragment);
        mFragments.add(iconCardFragment1);
        mFragments.add(iconCardFragment2);
        mFragments.add(iconCardFragment3);
        mFragments.add(iconCardFragment4);



        mPagerAdapter = new BaseIconAdapter(getSupportFragmentManager(), mFragments, mResIds);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerIndicator.setViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_style:
                if(!first){
                    mPagerIndicator.setIndicatorMode(TabPagerIndicator.IndicatorMode.MODE_WEIGHT_EXPAND_NOSAME,
                            true);
                }else{
                    mPagerIndicator.setIndicatorMode(TabPagerIndicator.IndicatorMode.MODE_WEIGHT_EXPAND_SAME,
                            true);
                }
                first=!first;

                break;
            default:break;
        }
    }
}