package com.example.apertureview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class ApertureView extends View {

    private Path mPath;
    private Paint mPaint;
    private int mWidth,mHeight;
    private int mRadius;
    private int mSpace = 6;
    private PointF[] mPoints = new PointF[6];
    private float mMaxApert = 1;
    private float mMinApert = 0.2f;
    private float mCurrentApert = 0.5f;
    private Bitmap mBlade;
    private int mBladeColor;
    private int mCount = 6;
    private float COS_30 = (float) Math.cos(30.0f/180*Math.PI);
    public ApertureView(Context context) {
        this(context,null);
    }

    public ApertureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ApertureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
        this.setBackgroundColor(Color.GRAY);
    }

    public ApertureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint();
        mPaint.setStrokeWidth(2);
        mPath = new Path();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setFilterBitmap(true);
        mBladeColor = Color.argb(200,255,0,0);
        Log.i("zyq","COS_30="+COS_30);
        for(int i = 0;i<6;i++){
            mPoints[i] = new PointF();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = right-left;
        mHeight = bottom - top;
        mRadius = Math.min((mWidth-getPaddingLeft()-getPaddingRight()),(mHeight-getPaddingTop()-getPaddingBottom()))/2;
        mPath.addCircle(0,0,mRadius, Path.Direction.CW);
        createBlade();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculatePoints();
        canvas.translate(mWidth/2,mHeight/2);
        canvas.rotate(mCurrentApert*60);
        canvas.clipPath(mPath);
        canvas.drawColor(Color.BLACK);
        for (int i = 0; i < 6; i++) {
            canvas.save();
            canvas.translate(mPoints[i].x, mPoints[i].y);
            canvas.rotate(-i * 60);
            canvas.drawBitmap(mBlade, 0, 0, mPaint);
            canvas.restore();
        }

    }

    private void calculatePoints() {
        if (mRadius - mSpace <= 0) {
            return;
        }
        //mCircleRadius - mSpace可以保证内嵌六边形在光圈内
        float curRadius = mCurrentApert / mMaxApert * (mRadius - mSpace);
        //利用对称关系，减少计算
        mPoints[0].x = curRadius / 2;
        mPoints[0].y = -curRadius * COS_30;
        mPoints[1].x = -mPoints[0].x;
        mPoints[1].y = mPoints[0].y;
        mPoints[2].x = -curRadius;
        mPoints[2].y = 0;
        mPoints[3].x = mPoints[1].x;
        mPoints[3].y = -mPoints[1].y;
        mPoints[4].x = -mPoints[3].x;
        mPoints[4].y = mPoints[3].y;
        mPoints[5].x = curRadius;
        mPoints[5].y = 0;
    }

    private void createBlade() {
        mBlade = Bitmap.createBitmap(mRadius,
                (int) (mRadius * 2 * COS_30), Bitmap.Config.ARGB_8888);
        Path path = new Path();
        Canvas canvas = new Canvas(mBlade);
        path.moveTo(mSpace / 2 / COS_30, mSpace);
        path.lineTo(mBlade.getWidth(), mBlade.getHeight());
        path.lineTo(mBlade.getWidth(), mSpace);
        path.close();
        canvas.clipPath(path);
        canvas.drawColor(mBladeColor);
    }
    public void setCurrentAperture(float x){
        mCurrentApert = x;
        invalidate();
    }
}
