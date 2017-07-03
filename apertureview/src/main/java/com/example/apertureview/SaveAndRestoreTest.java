package com.example.apertureview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class SaveAndRestoreTest extends View {

    private int mWidth,mHeight;
    private Paint mPaint;
    private Path mPath;
    public SaveAndRestoreTest(Context context) {
        super(context);
    }

    public SaveAndRestoreTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SaveAndRestoreTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SaveAndRestoreTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mPaint = new Paint();
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);
        mPath = new Path();
        mPath.addCircle(0,0,120, Path.Direction.CW);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = right - left;
        mHeight = bottom - top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        testCanvasRotation(canvas);
//        canvas.drawColor(Color.GRAY);
//        canvas.translate(mWidth/2,mHeight/2);
////        canvas.drawCircle(0,0,120,mPaint);
//        canvas.clipPath(mPath);
//        canvas.drawColor(Color.RED);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(-140,-140,200,200,mPaint);
//        canvas.save();
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(-140,-140,80,80,mPaint);
//        canvas.restore();
    }

    private void testCanvasRotation(Canvas canvas) {
        canvas.drawCircle(0,0,80,mPaint);
        canvas.save();//相当于复制了一个新的画布，对这个新的画布执行一系列操作
        canvas.rotate(90);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(mWidth/2,0,80,mPaint);
        canvas.restore();//将新画布上的图案与旧的画布合并起来
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(mWidth/2,mHeight/2,80,mPaint);
    }

    private void testCanvasTranslate(Canvas canvas) {
        canvas.drawCircle(40,40,40,mPaint);
        canvas.save();//保存当前画布状态
        mPaint.setColor(Color.BLUE);
        canvas.translate(mWidth/2,0);//画布右移mWidth/2,此时画布的原点位置由原来的（0,0）移动至（mWidth/2,0）
        canvas.drawCircle(0,40,40,mPaint);
        canvas.restore();
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(40,mHeight/2,40,mPaint);
    }
}
