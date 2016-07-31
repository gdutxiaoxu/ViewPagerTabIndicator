package com.xujun.administrator.tagindicatordemo;

import android.os.Bundle;
import android.widget.TextView;

import com.xujun.administrator.tagindicatordemo.base.BaseFragment;

/**
 * @ explain:
 * @ author：xujun on 2016/7/30 19:19
 * @ email：gdutxiaoxu@163.com
 */
public class TestFragment extends BaseFragment {

    protected TextView mTvContent;

    private static final String ARG_POSITION = "position";

    protected int position;

    public static TestFragment newInstance(int position) {
        TestFragment f = new TestFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
        position = getArguments().getInt(ARG_POSITION);
        String[] titles = getResources().getStringArray(R.array.fragments_titles);
        mTvContent = (TextView) mView.findViewById(R.id.tv_content);
        mTvContent.setText(titles[position]);
    }


}
