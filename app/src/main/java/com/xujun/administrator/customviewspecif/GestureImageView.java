package com.xujun.administrator.customviewspecif;

/**
 * 博客地址：http://blog.csdn.net/gdutxiaoxu
 * @author xujun
 * @time 2016/8/15 12:27.
 */

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by John on 2016/5/9.
 */
public class GestureImageView extends ImageView implements View.OnTouchListener {

    public class ZoomMode {
        public final static int Ordinary = 0;
        public final static int ZoomIn = 1;
        public final static int DoubleZoomIn = 2;
    }

    private int curMode = 0;

    private Matrix matrix;

    private PointF viewSize;

    private PointF imageSize;

    private PointF scaleSize;

    //��¼ͼƬ��ǰ���
    private PointF curPoint;

    private PointF originScale;

    //0:�����Ӧ 1:�߶���Ӧ
    private int fitMode = 0;

    private PointF start;

    private PointF center;

    private float scaleDoubleZoom = 0;

    private PointF relativePoint;

    private float doubleFingerDistance = 0;

    long doubleClickTimeSpan = 280;

    long lastClickTime = 0;

    int rationZoomIn = 2;

    public GestureImageView(Context context) {
        super(context);
        GestureImageViewInit();
    }

    public GestureImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        GestureImageViewInit();
    }

    public GestureImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GestureImageViewInit();
    }

    public void GestureImageViewInit() {
        this.setOnTouchListener(this);
        this.setScaleType(ScaleType.MATRIX);
        matrix = new Matrix();
        originScale = new PointF();
        scaleSize = new PointF();
        start = new PointF();
        center = new PointF();
        curPoint = new PointF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        viewSize = new PointF(width, height);

        //��ȡ��ǰDrawable�Ĵ�С
        Drawable drawable = getDrawable();
        if (drawable == null) {
            Log.e("no drawable", "drawable is nullPtr");
        } else {
            imageSize = new PointF(drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }

        FitCenter();
    }

    /**
     * ʹͼƬ����������
     */
    public void FitCenter() {
        float scaleH = viewSize.y / imageSize.y;
        float scaleW = viewSize.x / imageSize.x;
        //ѡ��С����������ȷ��ͼƬȫ����ʾ����Ұ��
        float scale = scaleH < scaleW ? scaleH : scaleW;
        //���view��Ӧ��С
        setImageScale(new PointF(scale, scale));

        originScale.set(scale, scale);
        //����������Ӵ�С����ͼƬ���ĵ���view ����
        if (scaleH < scaleW) {
            setImageTranslation(new PointF(viewSize.x / 2 - scaleSize.x / 2, 0));
            fitMode = 1;
        } else {
            fitMode = 0;
            setImageTranslation(new PointF(0, viewSize.y / 2 - scaleSize.y / 2));
        }

        //��¼�������� �´μ���������������
        scaleDoubleZoom = originScale.x;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                start.set(event.getX(), event.getY());
                //��ָ�����¼�
                if (event.getPointerCount() == 1) {
                    if (event.getEventTime() - lastClickTime <= doubleClickTimeSpan) {
                        //˫���¼�����
                        Log.e("TouchEvent", "DoubleClick");
                        if (curMode == ZoomMode.Ordinary) {
                            curMode = ZoomMode.ZoomIn;
                            relativePoint = new PointF();
                            //�����һ�����
                            relativePoint.set((start.x - curPoint.x) / scaleSize.x, (start.y -
                                    curPoint.y) / scaleSize.y);

                            setImageScale(new PointF(originScale.x * rationZoomIn, originScale.y
                                    * rationZoomIn));
                            setImageTranslation(new PointF(start.x - relativePoint.x * scaleSize
                                    .x, start.y - relativePoint.y * scaleSize.y));
                        } else {
                            curMode = ZoomMode.Ordinary;
                            FitCenter();
                        }
                    } else {
                        lastClickTime = event.getEventTime();
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //��Ļ���Ѿ���һ���㰴ס �ٰ���һ��ʱ�������¼�
                doubleFingerDistance = getDoubleFingerDistance(event);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //��Ļ���Ѿ��������㰴ס ���ɿ�һ��ʱ�������¼�
                curMode = ZoomMode.ZoomIn;
                scaleDoubleZoom = scaleSize.x / imageSize.x;
                if (scaleSize.x < viewSize.x && scaleSize.y < viewSize.y) {
                    curMode = ZoomMode.Ordinary;
                    FitCenter();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //��ָ�ƶ�ʱ�����¼�
                if (event.getPointerCount() == 1) {
                    if (curMode == ZoomMode.ZoomIn) {
                        setImageTranslation(new PointF(event.getX() - start.x, event.getY() -
                                start.y));
                        start.set(event.getX(), event.getY());
                    }
                } else {
                    //˫ָ����ʱ�ж��Ƿ�����һ������
                    if (Math.abs(getDoubleFingerDistance(event) - doubleFingerDistance) > 50 &&
                            curMode != ZoomMode.DoubleZoomIn) {
                        //��ȡ˫ָ�е�
                        center.set((event.getX(0) + event.getX(1)) / 2, (event.getY(0) + event
                                .getY(1)) / 2);
                        //�������
                        start.set(center);
                        curMode = ZoomMode.DoubleZoomIn;
                        doubleFingerDistance = getDoubleFingerDistance(event);
                        relativePoint = new PointF();

                        //���ͼƬ��ǰ���ֵ�����һ�����
                        relativePoint.set((start.x - curPoint.x) / scaleSize.x, (start.y -
                                curPoint.y) / scaleSize.y);
                    }
                    if (curMode == ZoomMode.DoubleZoomIn) {
                        float scale = scaleDoubleZoom * getDoubleFingerDistance(event) /
                                doubleFingerDistance;
                        setImageScale(new PointF(scale, scale));
                        setImageTranslation(new PointF(start.x - relativePoint.x * scaleSize.x,
                                start.y - relativePoint.y * scaleSize.y));
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                //��ָ�ɿ�ʱ�����¼�

                break;
        }

        //ע������return ��һ��Ҫ��true ����ֻ�ᴥ�������¼�
        return true;
    }

    /**
     * ���������������ͼƬ
     *
     * @param scale
     */
    public void setImageScale(PointF scale) {
        matrix.setScale(scale.x, scale.y);
        scaleSize.set(scale.x * imageSize.x, scale.y * imageSize.y);
        this.setImageMatrix(matrix);
    }

    /**
     * ���ƫ�����ı�ͼƬλ��
     *
     * @param offset
     */
    public void setImageTranslation(PointF offset) {
        matrix.postTranslate(offset.x, offset.y);
        curPoint.set(offset);
        this.setImageMatrix(matrix);
    }

    public static float getDoubleFingerDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }


}