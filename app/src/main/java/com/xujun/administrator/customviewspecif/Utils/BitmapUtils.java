package com.xujun.administrator.customviewspecif.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;

/**
 * @ explain:
 * @ author：xujun on 2016/8/14 18:41
 * @ email：gdutxiaoxu@163.com
 */
public class BitmapUtils {

    private Bitmap decodeBitmap(Context context,int targetWidth, int targetHeight) throws IOException {
        final BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getAssets().open("frog.jpg"), null, opts);

        opts.inSampleSize = getBitmapSampleSize(opts, targetWidth, targetHeight);

        opts.inJustDecodeBounds = false;

        return BitmapFactory.decodeStream(context.getAssets().open("frog.jpg"), null, opts);
    }

    private int getBitmapSampleSize(BitmapFactory.Options opts, int targetWidth, int targetHeight) {
        final int width = opts.outWidth;
        final int height = opts.outHeight;
        Log.d("tag", "bitmap's size is " + width + ", " + height);
        int inSampleSize = 1;

        if (height > targetHeight || width > targetWidth) {
            if (width > height) {
                inSampleSize = (int) Math.floor((float)height / (float)targetHeight);
            } else {
                inSampleSize = (int) Math.floor((float)width / (float)targetWidth);
            }
        }
        Log.d("tag", "target size is " + targetWidth + ", " + targetHeight);
        Log.d("tag", "inSampleSize is " + inSampleSize);
        return inSampleSize;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

}
