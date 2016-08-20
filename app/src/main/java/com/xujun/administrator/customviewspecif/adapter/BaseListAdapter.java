package com.xujun.administrator.customviewspecif.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/8/15 11:14
 * @ email：gdutxiaoxu@163.com
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected  List<T> mDatas;

    public BaseListAdapter(Context context,List<T> datas){
        mContext=context;
        mDatas=datas;
    }
    @Override
    public int getCount() {
        return isEmpty()?0:mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder<T> holder;
        if(convertView==null){
            holder=getHolder();
            convertView=holder.initView();
            convertView.setTag(holder);
        }else{
            holder= (BaseViewHolder<T>) convertView.getTag();
        }
        holder.refreshData(mDatas,position,mDatas.get(position));
        return holder.getConcertView();
    }

    protected abstract BaseViewHolder<T> getHolder();

    public boolean isEmpty(){
        return mDatas==null|| mDatas.size()==0;
    }
}
