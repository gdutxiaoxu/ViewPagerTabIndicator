package com.xujun.administrator.tagindicatordemo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Domen、on 2016/4/20.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final String DEFAULT_NAME = "DEFAULT_NAME";
    protected static final String DEFAULT_PARCEABLE_NAME = "DEFAULT_PARCEABLE_NAME";



    protected Context mContext;

    protected ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();
        mContext = this;
        dialog = new ProgressDialog(this);

        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());

        } else {
            Log.w("BaseActivity", "onCreate: Error contentView");
        }
        initView();
        initListener();
        initData();
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
            dialog=null;
        }

    }

    /**
     * 在setContentView前初始化Window设置
     */
    protected void initWindows() {
    }

    /**
     * 获取其他组件传来的数据
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 获取Layout的id
     */
    protected abstract int getContentViewLayoutID();


    /**
     * 启动Activity
     */
    public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 启动Activity并传递数据
     */
    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void readyGo(Class<?> clazz, String name, Parcelable parcelable) {
        Intent intent = new Intent(this, clazz);
        if (null != parcelable) {
            intent = intent.putExtra(name, parcelable);
        }
        startActivity(intent);
    }

    public void readyGo(Class<?> clazz, Parcelable parcelable) {
        Intent intent = new Intent(this, clazz);
        if (null != parcelable) {
            intent = intent.putExtra(DEFAULT_PARCEABLE_NAME, parcelable);
        }
        startActivity(intent);
    }

    /**
     * 显示菊花
     */
    public void showProressDialog() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void showProgressDialog(boolean cancelable, String msg) {
        if (dialog != null) {
            dialog.setCancelable(cancelable);
            dialog.setMessage(msg);
            dialog.show();
        }
    }

    /**
     * 显示带信息的菊花
     */
    public void showProressDialog(String msg) {
        if (dialog != null) {
            dialog.setMessage(msg);
            dialog.show();
        }
    }

    /**
     * 隐藏菊花
     */
    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.hide();
        }
    }

    /**
     * 根据字符串弹出Toast
     */
    protected void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据资源id弹出Toast
     */
    protected void showToast(int msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
