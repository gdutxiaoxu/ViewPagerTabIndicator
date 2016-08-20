package com.xujun.administrator.customviewspecif.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/8/15 11:52
 * @ email：gdutxiaoxu@163.com
 */
public abstract class BaseViewHolder<T> {
    Context mContext;

    View mConvertView;

    public BaseViewHolder(Context context){
        mContext=context;
    }



    public abstract View initView();

    public void refreshData(List<T> data, int position, T t){

    }

    public View getConcertView(){
        return mConvertView;
    }
}
