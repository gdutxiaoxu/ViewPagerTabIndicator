package com.xujun.administrator.tagindicatordemo;

import android.view.View;
import android.widget.Button;

import com.xujun.administrator.tagindicatordemo.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private Button btnTest;
    private Button btnPagIndicator;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnTest = (Button) findViewById(R.id.btn_test);
        btnPagIndicator = (Button) findViewById(R.id.btn_pagIndicator);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                //                Toast.makeText(MainActivity.this, "该功能尚未更新，有兴趣的请关注该项目，待带更新",
                // Toast.LENGTH_SHORT)
                //                        .show();
                readyGo(ToolBarActivity.class);
                break;
            case R.id.btn_pagIndicator:
                readyGo(PagerIndicatorActivity.class);
                break;
            case R.id.btn_IconIndicator:
                readyGo(IconActivity.class);
            default:
                break;
        }
    }
}
