package com.xujun.administrator.customviewspecif.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xujun.administrator.customviewspecif.transformer.TransFormerItem;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/8/15 11:18
 * @ email：gdutxiaoxu@163.com
 */
public class MenuAdapter extends BaseListAdapter<TransFormerItem> {


    public MenuAdapter(Context context, List<TransFormerItem> datas) {
        super(context, datas);
    }

    @Override
    protected BaseViewHolder<TransFormerItem> getHolder() {
        return new MenuHolder(mContext);
    }


}
