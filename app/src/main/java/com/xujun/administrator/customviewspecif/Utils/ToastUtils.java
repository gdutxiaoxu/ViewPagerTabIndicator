package com.xujun.administrator.customviewspecif.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @ explain:
 * @ author：xujun on 2016/8/15 09:49
 * @ email：gdutxiaoxu@163.com
 */
public class ToastUtils {
    private static Toast shortToast = null; //Toast的对象！
    private static Toast longToast = null; //Toast的对象！

    public static void showText(Context mContext, String text) {
        if (shortToast == null) {
            shortToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        }
        else {
            shortToast.setText(text);
        }
        shortToast.show();
    }

    public static void showLongText(Context mContext, String text) {
        if (shortToast == null) {
            shortToast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
        }
        else {
            shortToast.setText(text);
        }
        shortToast.show();
    }
}
