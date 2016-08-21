package com.xujun.administrator.customviewspecif.transformer;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

/**
 * @ explain:
 * @ author：xujun on 2016/8/15 10:45
 * @ email：gdutxiaoxu@163.com
 */
public class TableTransformer extends BaseTransformer {

    private static final Matrix OFFSET_MATRIX = new Matrix();
    private static final Camera OFFSET_CAMERA = new Camera();
    private static final float[] OFFSET_TEMP_FLOAT = new float[2];
    private static final float  minScale=0.6f;

    @Override
    protected void onTransform(View view, float position) {
        final float rotation = (position < 0 ? 30f : -30f) * Math.abs(position);

        view.setTranslationX(getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(0);
        view.setRotationY(rotation);
        float scaleX=Math.max(1-Math.abs(position),minScale);
        view.setScaleX(scaleX);

    }

    protected static final float getOffsetXForRotation(float degrees, int width, int height) {
        OFFSET_MATRIX.reset();
        OFFSET_CAMERA.save();

        OFFSET_CAMERA.rotateY(Math.abs(degrees));
        OFFSET_CAMERA.getMatrix(OFFSET_MATRIX);
        OFFSET_CAMERA.restore();

        OFFSET_MATRIX.preTranslate(-width * 0.5f, -height * 0.5f);
        OFFSET_MATRIX.postTranslate(width * 0.5f, height * 0.5f);
        OFFSET_TEMP_FLOAT[0] = width;
        OFFSET_TEMP_FLOAT[1] = height;
        OFFSET_MATRIX.mapPoints(OFFSET_TEMP_FLOAT);
        return (width - OFFSET_TEMP_FLOAT[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
    }
}
