package com.richsoft.maintenace.common;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.SwitchRoomBean;
import com.richsoft.maintenace.bean.workorder.SwitchRoomDeviceBean;

import ren.solid.library.utils.LogUtils;
import ren.solid.library.utils.StringUtils;

/**
 * 作者：e430 on 2017/3/4 12:24
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class SwitchRoom extends View {
    SwitchRoomBean mSwitchRoomBean;

    Paint paint = new Paint();//用于绘制开关柜图片的画笔
    Paint headPaint;//绘制布置图头部的画笔
    Paint pathPaint;
    Paint redBorderPaint;
    Paint overviewPaint=new Paint();

    RectF rectF;//概览图矩形
    float rectHeight;//概览图白色方块高度
    float rectWidth;//概览图白色方块的宽度
    float overviewSpacing;//概览图上方块的水平间距
    float overviewVerSpacing;//概览图上方块的垂直间距
    float overviewScale = 4.8f;//概览图的比例
    float rectW;//整个概览图的宽度
    float rectH;//整个概览图的高度
    Bitmap overviewBitmap;

    Matrix matrix = new Matrix();

    int overview_start;//预览图已开启的柜子颜色
    int overview_stop;//预览图停运的柜子颜色
    int overview_temp_stop;//预览图被临时关闭的柜子颜色
    int overview_data_complete;//预览图完成数据采集的柜子颜色
    int txt_color;//柜子名称文本颜色

    int switch_cabinet_start_resid;//柜子已开启的图片资源
    Bitmap switch_cabinet_start_bitmap;//柜子已开启的图片
    int switch_cabinet_stop_resid;//柜子停运的图片资源
    Bitmap switch_cabinet_stop_bitmap;//柜子停运的图片
    int switch_cabinet_temp_stop_resid;//柜子临时关闭的图片资源
    Bitmap switch_cabinet_temp_stop_bitmap;//柜子临时关闭的图片
    int switch_cabinet_data_complete_resid;//柜子完成数据采集的图片资源
    Bitmap switch_cabinet_data_complete_bitmap;//柜子完成数据采集的图片

    /**
     * 默认的开关柜图宽度,如果使用的自己的开关柜图比这个尺寸大或者小,会缩放到这个大小
     */
    private float defaultImgW = 80;

    /**
     * 默认的开关柜图高度
     */
    private float defaultImgH = 80;

    float xScale1 = 1;
    float yScale1 = 1;

    /**
     * 开关柜图片的宽度
     */
    private int switchCabinetWidth;

    /**
     * 开关柜图片的高度
     */
    private int switchCabinetHeight;

    /**
     * 座位水平间距
     */
    int spacing;

    /**
     * 座位垂直间距
     */
    int verSpacing;
    /**
     * 行数
     */
    int row;

    /**
     * 列数
     */
    int column;
    /**
     * 整个开关柜布置图的宽度
     */
    int totalWidth;

    /**
     * 整个开关柜布置图的高度
     */
    int totalHeight;

    /**
     * 顶部高度
     */
    float headHeight;

    /**
     * 开关柜停运
     */
    private static final int SWITCH_CABINET_STOP = 0;

    /**
     * 开关柜运行
     */
    private static final int SWITCH_CABINET_START = 1;

    /**
     * 开关柜临时关闭
     */
    private static final int SWITCH_CABINET_TEMP_STOP = 2;

    /**
     * 开关柜数据采集完毕
     */
    private static final int SWITCH_CABINET_DATA_COMPLETE = 3;
    /**
     * 通道
     */
    private static final int SWITCH_CABINET_NULL = 4;

    Bitmap headBitmap;

    private SwitchCabinetChecker switchCabinetChecker;

    public void setSwitchCabinetChecker(SwitchCabinetChecker switchCabinetChecker) {
        this.switchCabinetChecker = switchCabinetChecker;
        invalidate();
    }

    /**
     * 标识是否需要绘制概览图
     */
    boolean isDrawOverview = false;

    /**
     * 标识是否需要更新概览图
     */
    boolean isDrawOverviewBitmap = true;


    public SwitchRoom(Context context) {
        super(context);
    }

    public SwitchRoom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public SwitchRoom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchRoomView);

        overview_start = typedArray.getColor(R.styleable.SwitchRoomView_overview_start, Color.parseColor("#ccdcda"));
        overview_stop = typedArray.getColor(R.styleable.SwitchRoomView_overview_stop, Color.parseColor("#d6d9de"));
        overview_temp_stop = typedArray.getColor(R.styleable.SwitchRoomView_overview_temp_stop, Color.parseColor("#ffeeee"));
        overview_data_complete = typedArray.getColor(R.styleable.SwitchRoomView_overview_data_complete, Color.parseColor("#97d3ff"));
        txt_color= typedArray.getColor(R.styleable.SwitchRoomView_txt_color, Color.parseColor("#797979"));


        switch_cabinet_start_resid = typedArray.getResourceId(R.styleable.SwitchRoomView_switch_cabinet_start, R.drawable.green);
        switch_cabinet_stop_resid = typedArray.getResourceId(R.styleable.SwitchRoomView_switch_cabinet_stop, R.drawable.gray);
        switch_cabinet_temp_stop_resid = typedArray.getResourceId(R.styleable.SwitchRoomView_switch_cabinet_temp_stop, R.drawable.pink);
        switch_cabinet_data_complete_resid = typedArray.getResourceId(R.styleable.SwitchRoomView_switch_cabinet_data_complete, R.drawable.blue);
        typedArray.recycle();
    }

    /**
     * 使用该view设置数据之前，调用该方法初始化
     */
    private void init() {
        spacing = (int) dip2Px(5);
        verSpacing = (int) dip2Px(10);

        //将开关柜开启状态图片资源转为位图
        switch_cabinet_stop_bitmap= BitmapFactory.decodeResource(getResources(), switch_cabinet_stop_resid);

        //计算出位图与我们预定大小的比例
        float scaleX = defaultImgW / switch_cabinet_stop_bitmap.getWidth();
        float scaleY = defaultImgH / switch_cabinet_stop_bitmap.getHeight();
        xScale1 = scaleX;
        yScale1 = scaleY;
        //将位图进行缩放，以达到我们预定的尺寸
        switchCabinetHeight= (int) (switch_cabinet_stop_bitmap.getHeight()*yScale1);
        switchCabinetWidth= (int) (switch_cabinet_stop_bitmap.getWidth()*xScale1);

        //将开关柜停运状态图片资源转为位图
        switch_cabinet_start_bitmap= BitmapFactory.decodeResource(getResources(), switch_cabinet_start_resid);
        //将开关柜临时关闭图片资源转为位图
        switch_cabinet_temp_stop_bitmap= BitmapFactory.decodeResource(getResources(), switch_cabinet_temp_stop_resid);
        //将开关柜数据采集完毕图片资源转为位图
        switch_cabinet_data_complete_bitmap= BitmapFactory.decodeResource(getResources(), switch_cabinet_data_complete_resid);

        //计算整个布置图的高度和宽度
        totalWidth=(int) (column * switch_cabinet_stop_bitmap.getWidth()*xScale1 + (column - 1) * spacing);
        totalHeight = (int) (row * switch_cabinet_stop_bitmap.getHeight()*yScale1 + (row - 1) * verSpacing);
        //设置绘制开关柜图片的画笔
        paint.setColor(Color.parseColor("#0065af"));

        //设置绘制头部的画笔
        headHeight = dip2Px(30);
        headPaint = new Paint();
        headPaint.setStyle(Paint.Style.FILL);
        headPaint.setTextSize(24);
        headPaint.setColor(Color.WHITE);
        headPaint.setAntiAlias(true);
        int borderHeight = 1;//头部下面横线的高度

        //绘制间隔线的画笔
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setStyle(Paint.Style.FILL);
        pathPaint.setColor(Color.parseColor("#e2e2e2"));

        //绘制概览红框的画笔
        redBorderPaint = new Paint();
        redBorderPaint.setAntiAlias(true);
        redBorderPaint.setColor(Color.parseColor("#ff6b7b"));
        redBorderPaint.setStyle(Paint.Style.STROKE);
        redBorderPaint.setStrokeWidth(getResources().getDisplayMetrics().density * 1);

        //概览图开关柜矩形初始化
        rectF = new RectF();
        rectHeight = switchCabinetHeight / overviewScale;
        rectWidth = switchCabinetWidth / overviewScale;
        overviewSpacing = spacing / overviewScale;
        overviewVerSpacing = verSpacing / overviewScale;
        //计算整个概览图的高度和宽度
        rectW = column * rectWidth + (column - 1) * overviewSpacing + overviewSpacing * 2;
        rectH = row * rectHeight + (row - 1) * overviewVerSpacing + overviewVerSpacing * 2;
        //生成概览图位图
        overviewBitmap = Bitmap.createBitmap((int) rectW, (int) rectH, Bitmap.Config.ARGB_4444);

        matrix.postTranslate(spacing, headHeight + borderHeight + verSpacing);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtils.i("zhoul","开始绘制");
        if (row <= 0 || column == 0) {
            return;
        }
        drawSwitchCabinet(canvas);
        if (headBitmap == null) {
            headBitmap = drawHeadInfo();
        }
        canvas.drawBitmap(headBitmap, 0, 0, null);
        if (isDrawOverview) {
            if (isDrawOverviewBitmap) {
                drawOverview();
            }
            canvas.drawBitmap(overviewBitmap, 0, 0, null);
            drawOverview(canvas);
        }
    }

    /**
     * 绘制概览图
     */
    void drawOverview(Canvas canvas) {

        //绘制红色框
        int left = (int) -getTranslateX();
        if (left < 0) {
            left = 0;
        }
        left /= overviewScale;
        left /= getMatrixScaleX();

        int currentWidth = (int) (getTranslateX() + (column * switchCabinetWidth + spacing * (column - 1)) * getMatrixScaleX());
        if (currentWidth > getWidth()) {
            currentWidth = currentWidth - getWidth();
        } else {
            currentWidth = 0;
        }
        int right = (int) (rectW - currentWidth / overviewScale / getMatrixScaleX());

        float top = -getTranslateY() + headHeight;
        if (top < 0) {
            top = 0;
        }
        top /= overviewScale;
        top /= getMatrixScaleY();
        if (top > 0) {
            top += overviewVerSpacing;
        }

        int currentHeight = (int) (getTranslateY() + (row * switchCabinetHeight + verSpacing * (row - 1)) * getMatrixScaleY());
        if (currentHeight > getHeight()) {
            currentHeight = currentHeight - getHeight();
        } else {
            currentHeight = 0;
        }
        int bottom = (int) (rectH - currentHeight / overviewScale / getMatrixScaleY());

        canvas.drawRect(left, top, right, bottom, redBorderPaint);
    }

    Bitmap drawOverview() {
        isDrawOverviewBitmap = false;

        int bac = Color.parseColor("#bdbdbd");
        overviewPaint.setColor(bac);
        overviewPaint.setAntiAlias(true);
        overviewPaint.setStyle(Paint.Style.FILL);
        overviewBitmap.eraseColor(Color.TRANSPARENT);
        Canvas canvas = new Canvas(overviewBitmap);
        //绘制透明灰色背景
        canvas.drawRect(0, 0, rectW, rectH, overviewPaint);

        overviewPaint.setColor(Color.WHITE);
        for (int i = 0; i < row; i++) {
            float top = i * rectHeight + i * overviewVerSpacing + overviewVerSpacing;
            for (int j = 0; j < column; j++) {

                int type = getSwitchCabinetType(i, j);
                switch (type) {
                    case SWITCH_CABINET_STOP:
                        overviewPaint.setColor(overview_stop);
                        break;
                    case SWITCH_CABINET_NULL:
                        continue;
                    case SWITCH_CABINET_TEMP_STOP:
                        overviewPaint.setColor(overview_temp_stop);
                        break;
                    case SWITCH_CABINET_DATA_COMPLETE:
                        overviewPaint.setColor(overview_data_complete);
                        break;
                    case SWITCH_CABINET_START:
                        overviewPaint.setColor(overview_start);
                        break;
                }

                float left;

                left = j * rectWidth + j * overviewSpacing + overviewSpacing;
                canvas.drawRect(left, top, left + rectWidth, top + rectHeight, overviewPaint);
            }
        }

        return overviewBitmap;
    }

    Bitmap drawHeadInfo() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), (int) headHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //绘制分割线
        headPaint.setStrokeWidth(1);
        headPaint.setColor(Color.GRAY);
        canvas.drawLine(0, headHeight, getWidth(), headHeight, headPaint);
        return bitmap;

    }

    Matrix tempMatrix = new Matrix();
    private void drawSwitchCabinet(Canvas canvas) {
        zoom = getMatrixScaleX();
        float translateX = getTranslateX();
        float translateY = getTranslateY();
        float scaleX = zoom;
        float scaleY = zoom;
        for (int i = 0; i < row; i++) {
            float top = i * switch_cabinet_stop_bitmap.getHeight() * yScale1 * scaleY + i * verSpacing * scaleY + translateY;

            float bottom = top + switch_cabinet_stop_bitmap.getHeight() * yScale1 * scaleY;
            if (bottom < 0 || top > getHeight()) {
                continue;
            }

            for (int j = 0; j < column; j++) {
                float left = j * switch_cabinet_stop_bitmap.getWidth() * xScale1 * scaleX + j * spacing * scaleX + translateX;

                float right = (left + switch_cabinet_stop_bitmap.getWidth() * xScale1 * scaleY);
                if (right < 0 || left > getWidth()) {
                    continue;
                }

                int seatType = getSwitchCabinetType(i, j);
                tempMatrix.setTranslate(left, top);
                tempMatrix.postScale(xScale1, yScale1, left, top);
                tempMatrix.postScale(scaleX, scaleY, left, top);

                switch (seatType) {
                    case SWITCH_CABINET_START:
                        canvas.drawBitmap(switch_cabinet_start_bitmap, tempMatrix, paint);
                        drawText(canvas, i, j, top, left);
                        break;
                    case SWITCH_CABINET_TEMP_STOP:
                        canvas.drawBitmap(switch_cabinet_temp_stop_bitmap, tempMatrix, paint);
                        drawText(canvas, i, j, top, left);
                        break;
                    case SWITCH_CABINET_DATA_COMPLETE:
                        canvas.drawBitmap(switch_cabinet_data_complete_bitmap, tempMatrix, paint);
                        drawText(canvas, i, j, top, left);
                        break;
                    case SWITCH_CABINET_STOP:
                        canvas.drawBitmap(switch_cabinet_stop_bitmap, tempMatrix, paint);
                        drawText(canvas, i, j, top, left);
                        break;
                    case SWITCH_CABINET_NULL:
                        break;
                }
            }
        }
    }

    /**
     * 绘制选中座位的行号列号
     *
     * @param row
     * @param column
     */
    private void drawText(Canvas canvas, int row, int column, float top, float left) {
        SwitchRoomDeviceBean srdb=mSwitchRoomBean.getSwitchRoomDeviceRowBeans().get(row).getSwitchRoomDeviceBeans().get(column);
        String txt = srdb.getSwitchRoomDeviceNo();
        String txt1 = srdb.getSwitchRoomDeviceName();

        TextPaint txtPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        txtPaint.setColor(txt_color);
        txtPaint.setTypeface(Typeface.DEFAULT_BOLD);
        float seatHeight = this.switchCabinetHeight * getMatrixScaleX();
        float seatWidth = this.switchCabinetWidth * getMatrixScaleX();
        txtPaint.setTextSize(seatHeight / 6);

        //获取中间线
        float center = seatHeight / 2;
        float txtWidth = txtPaint.measureText(txt);
        float startX = left + seatWidth / 2 - txtWidth / 2;

        //只绘制一行文字
        if(StringUtils.isNullOrEmpty(txt1)){
            canvas.drawText(txt, startX, getBaseLine(txtPaint, top, top + seatHeight), txtPaint);
        }else {
            canvas.drawText(txt, startX, getBaseLine(txtPaint, top, top + center), txtPaint);
            canvas.drawText(txt1, startX, getBaseLine(txtPaint, top + center, top + center + seatHeight / 2), txtPaint);
        }
    }

    private float getBaseLine(Paint p, float top, float bottom) {
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
        int baseline = (int) ((bottom + top - fontMetrics.bottom - fontMetrics.top) / 2);
        return baseline;
    }

    private int getSwitchCabinetType(int row, int column) {
        if(mSwitchRoomBean!=null&&mSwitchRoomBean.getSwitchRoomDeviceRowBeans()!=null&&mSwitchRoomBean.getSwitchRoomDeviceRowBeans().size()>0){
            return mSwitchRoomBean.getSwitchRoomDeviceRowBeans().get(row).getSwitchRoomDeviceBeans().get(column).getSwitchRoomDeviceState();
        }else{
            return SWITCH_CABINET_NULL;
        }
    }

    public interface SwitchCabinetChecker {
        void onSwitchCabinetClick(int row, int column);
    }

    private float zoom;


    private float getMatrixScaleX() {
        matrix.getValues(m);
        return m[Matrix.MSCALE_X];
    }

    float[] m = new float[9];

    private float getTranslateX() {
        matrix.getValues(m);
        return m[2];
    }

    private float getTranslateY() {
        matrix.getValues(m);
        return m[5];
    }

    private float getMatrixScaleY() {
        matrix.getValues(m);
        return m[4];
    }

    private float dip2Px(float value) {
        return getResources().getDisplayMetrics().density * value;
    }

    public void setData(SwitchRoomBean bean) {
        if(bean!=null&&bean.getSwitchRoomDeviceRowBeans()!=null
                &&bean.getSwitchRoomDeviceRowBeans().size()>0
                &&bean.getSwitchRoomDeviceRowBeans().get(0).getSwitchRoomDeviceBeans()!=null
                &&bean.getSwitchRoomDeviceRowBeans().get(0).getSwitchRoomDeviceBeans().size()>0){
            LogUtils.i("zhoul","开始给定数据");
            this.row = bean.getSwitchRoomDeviceRowBeans().size();
            this.column = bean.getSwitchRoomDeviceRowBeans().get(0).getSwitchRoomDeviceBeans().size();
            this.mSwitchRoomBean=bean;
            isDrawOverview=false;
            isDrawOverviewBitmap=true;
            init();
            invalidate();
        }
    }

    /*************************触摸处理*******************************/
    private boolean pointer;
    private int downX, downY;
    Handler handler = new Handler();
    boolean isOnClick;
    int lastX;
    int lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        int x = (int) event.getX();
        super.onTouchEvent(event);

        scaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        int pointerCount = event.getPointerCount();
        if (pointerCount > 1) {
            pointer = true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pointer = false;
                downX = x;
                downY = y;
                isDrawOverview = true;
                handler.removeCallbacks(hideOverviewRunnable);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isScaling && !isOnClick) {
                    int downDX = Math.abs(x - downX);
                    int downDY = Math.abs(y - downY);
                    if ((downDX > 10 || downDY > 10) && !pointer) {
                        int dx = x - lastX;
                        int dy = y - lastY;
                        matrix.postTranslate(dx, dy);
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                handler.postDelayed(hideOverviewRunnable, 1500);
                autoScale();
                int downDX = Math.abs(x - downX);
                int downDY = Math.abs(y - downY);
                if ((downDX >10 || downDY > 10) && !pointer) {
                    autoScroll();
                }

                break;
        }
        isOnClick = false;
        lastY = y;
        lastX = x;

        return true;
    }

    int borderHeight = 1;

    /**
     * 自动回弹
     * 整个大小不超过控件大小的时候:
     * 往左边滑动,自动回弹到行号右边
     * 往右边滑动,自动回弹到右边
     * 往上,下滑动,自动回弹到顶部
     * <p>
     * 整个大小超过控件大小的时候:
     * 往左侧滑动,回弹到最右边,往右侧滑回弹到最左边
     * 往上滑动,回弹到底部,往下滑动回弹到顶部
     */
    private void autoScroll() {
        float currentSeatBitmapWidth = totalWidth * getMatrixScaleX();
        float currentSeatBitmapHeight = totalHeight * getMatrixScaleY();
        float moveYLength = 0;
        float moveXLength = 0;

        //处理左右滑动的情况
        if (currentSeatBitmapWidth < getWidth()) {
            if (getTranslateX() < 0 || getMatrixScaleX() <  spacing) {
                //计算要移动的距离

                if (getTranslateX() < 0) {
                    moveXLength = (-getTranslateX()) + spacing;
                } else {
                    moveXLength = spacing - getTranslateX();
                }

            }
        } else {

            if (getTranslateX() < 0 && getTranslateX() + currentSeatBitmapWidth > getWidth()) {

            } else {
                //往左侧滑动
                if (getTranslateX() + currentSeatBitmapWidth < getWidth()) {
                    moveXLength = getWidth() - (getTranslateX() + currentSeatBitmapWidth);
                } else {
                    //右侧滑动
                    moveXLength = -getTranslateX()  + spacing;
                }
            }

        }

        float startYPosition = getMatrixScaleY() + verSpacing * getMatrixScaleY() + headHeight + borderHeight;

        //处理上下滑动
        if (currentSeatBitmapHeight+headHeight < getHeight()) {

            if (getTranslateY() < startYPosition) {
                moveYLength = startYPosition - getTranslateY();
            } else {
                moveYLength = -(getTranslateY() - (startYPosition));
            }

        } else {

            if (getTranslateY() < 0 && getTranslateY() + currentSeatBitmapHeight > getHeight()) {

            } else {
                //往上滑动
                if (getTranslateY() + currentSeatBitmapHeight < getHeight()) {
                    moveYLength = getHeight() - (getTranslateY() + currentSeatBitmapHeight);
                } else {
                    moveYLength = -(getTranslateY() - (startYPosition));
                }
            }
        }

        Point start = new Point();
        start.x = (int) getTranslateX();
        start.y = (int) getTranslateY();

        Point end = new Point();
        end.x = (int) (start.x + moveXLength);
        end.y = (int) (start.y + moveYLength);

        moveAnimate(start, end);

    }

    private void autoScale() {

        if (getMatrixScaleX() > 2.2) {
            zoomAnimate(getMatrixScaleX(), 2.0f);
        } else if (getMatrixScaleX() < 0.98) {
            zoomAnimate(getMatrixScaleX(), 1.0f);
        }
    }

    private void zoomAnimate(float cur, float tar) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(cur, tar);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        ZoomAnimation zoomAnim = new ZoomAnimation();
        valueAnimator.addUpdateListener(zoomAnim);
        valueAnimator.addListener(zoomAnim);
        valueAnimator.setDuration(400);
        valueAnimator.start();
    }

    class ZoomAnimation implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            zoom = (Float) animation.getAnimatedValue();
            zoom(zoom);

        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }

        @Override
        public void onAnimationStart(Animator animation) {
        }

    }

    private void zoom(float zoom) {
        float z = zoom / getMatrixScaleX();
        matrix.postScale(z, z, scaleX, scaleY);
        invalidate();
    }

    private void moveAnimate(Point start, Point end) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new MoveEvaluator(), start, end);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        MoveAnimation moveAnimation = new MoveAnimation();
        valueAnimator.addUpdateListener(moveAnimation);
        valueAnimator.setDuration(400);
        valueAnimator.start();
    }

    class MoveEvaluator implements TypeEvaluator {

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;
            int x = (int) (startPoint.x + fraction * (endPoint.x - startPoint.x));
            int y = (int) (startPoint.y + fraction * (endPoint.y - startPoint.y));
            return new Point(x, y);
        }
    }

    class MoveAnimation implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            Point p = (Point) animation.getAnimatedValue();

            move(p);
        }
    }

    private void move(Point p) {
        float x = p.x - getTranslateX();
        float y = p.y - getTranslateY();
        matrix.postTranslate(x, y);
        invalidate();
    }

    private Runnable hideOverviewRunnable = new Runnable() {
        @Override
        public void run() {
            isDrawOverview = false;
            invalidate();
        }
    };

    /**
     * 标识是否正在缩放
     */
    boolean isScaling;
    float scaleX, scaleY;
    /**
     * 是否是第一次缩放
     */
    boolean firstScale = true;
    ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            isScaling = true;
            float scaleFactor = detector.getScaleFactor();
            if (getMatrixScaleY() * scaleFactor > 3) {
                scaleFactor = 3 / getMatrixScaleY();
            }
            if (firstScale) {
                scaleX = detector.getCurrentSpanX();
                scaleY = detector.getCurrentSpanY();
                firstScale = false;
            }

            if (getMatrixScaleY() * scaleFactor < 0.5) {
                scaleFactor = 0.5f / getMatrixScaleY();
            }
            matrix.postScale(scaleFactor, scaleFactor, scaleX, scaleY);
            invalidate();
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            isScaling = false;
            firstScale = true;
        }
    });

    GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            isOnClick = true;
            int x = (int) e.getX();
            int y = (int) e.getY();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    int tempX = (int) ((j * switchCabinetWidth + j * spacing) * getMatrixScaleX() + getTranslateX());
                    int maxTemX = (int) (tempX + switchCabinetWidth * getMatrixScaleX());

                    int tempY = (int) ((i * switchCabinetHeight + i * verSpacing) * getMatrixScaleY() + getTranslateY());
                    int maxTempY = (int) (tempY + switchCabinetHeight * getMatrixScaleY());
                    if (switchCabinetChecker!= null) {
                        if (x >= tempX && x <= maxTemX && y >= tempY && y <= maxTempY) {
                            switchCabinetChecker.onSwitchCabinetClick(i, j);
                        }
                    }
                }
            }
            return super.onSingleTapConfirmed(e);
        }
    });

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);

        return super.dispatchTouchEvent(ev);
    }
}
