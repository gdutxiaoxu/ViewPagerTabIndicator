package com.xujun.administrator.tagindicatordemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ explain:
 * @ author：xujun on 2016/7/30 19:00
 * @ email：gdutxiaoxu@163.com
 */
public abstract class BaseFragment extends Fragment{

    protected View mView;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        int layoutId = getLayoutId();
        if(layoutId<=0){
            throw new IllegalStateException("You must provide  a righr layoutId");
        }
        mView = inflater.inflate(layoutId, container, false);

        return mView;

    }

    protected  void initView(){

    }

    protected abstract int getLayoutId() ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    protected void initData() {
    }
}
