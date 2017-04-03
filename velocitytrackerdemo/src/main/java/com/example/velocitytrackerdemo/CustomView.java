package com.example.velocitytrackerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class CustomView extends View {
    private int mViewWidth,mViewHeight;
    private Rect mPaddingRect;
    private Bitmap mRotateBitmap;
    private Paint mBackgroundPaint,mBitmapPaint;
    private Matrix mMatrix;
    private float mDegree = 0;
    private long mDuration = 1;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaddingRect = new Rect();
        mPaddingRect.left = getPaddingLeft();
        mPaddingRect.top = getPaddingTop();
        mPaddingRect.right = getPaddingRight();
        mPaddingRect.bottom = getPaddingBottom();
        mRotateBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.bitmap_drawable,null)).getBitmap();
        mMatrix = new Matrix();
        initPaints();
    }

    private void initPaints(){
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.BLACK);
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setDither(true);
        mBackgroundPaint.setStrokeWidth(10);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mBitmapPaint = new Paint();
        mBitmapPaint.setFilterBitmap(true);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = mPaddingRect.left+mPaddingRect.right+mRotateBitmap.getWidth();
        mViewHeight = mRotateBitmap.getHeight()+mPaddingRect.top+mPaddingRect.bottom;
        setMeasuredDimension(mViewWidth,mViewHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawOther(canvas);
//        postInvalidateDelayed(mDuration);
        invalidate();
    }

    private void drawBackground(Canvas canvas){
        canvas.save();
        canvas.drawCircle(mViewWidth/2,mViewHeight/2,mRotateBitmap.getWidth()/2,mBackgroundPaint);
        canvas.restore();
    }

    public void setRotateSpeed(long time){
        this.mDuration = time;
        invalidate();
    }

    private void drawOther(Canvas canvas){
        canvas.save();
        mMatrix.reset();
        mMatrix.postRotate(mDegree,mViewWidth/2,mViewHeight/2);
        canvas.drawBitmap(mRotateBitmap,mMatrix,mBitmapPaint);
        canvas.restore();
        mDegree+=5;
    }
}
