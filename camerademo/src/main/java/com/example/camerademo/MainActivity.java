package com.example.camerademo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.graphics.YuvImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mContainer1,mContainer2;
    private CameraPreview mPreview;
    private Camera mCamera;
    private CameraCallBack mCallBack;
    private SurfaceView mSurfaceView;
    private int mWidth,mHeight;
    private float width,height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer1 = (LinearLayout)findViewById(R.id.container1);
        mContainer2 = (LinearLayout)findViewById(R.id.container2);
        mCamera = Camera.open(0);
        mPreview = new CameraPreview(this, mCamera);
        mContainer1.addView(mPreview);
        mCallBack = new CameraCallBack();
        mCamera.setPreviewCallback(mCallBack);
        mSurfaceView = new SurfaceView(this);
        mContainer2.addView(mSurfaceView);
        Camera.Size mSize = mCamera.getParameters().getPreviewSize();
        mWidth = mSize.width;
        mHeight = mSize.height;
    }

    class CameraCallBack implements Camera.PreviewCallback{
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            Log.i("zyq","CameraCallBack:onPreviewFrame");
            if(data != null && data.length>0){
                dealWithSurfaceView(data);
            }

        }
    }

    private synchronized void dealWithSurfaceView(byte[] data){

        Rect mRect = new Rect(0,0,mWidth,mHeight);
        YuvImage mYuvImage = new YuvImage(data, ImageFormat.NV21,mWidth,mHeight,null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mYuvImage.compressToJpeg(mRect,80,outputStream);
        Bitmap b = BitmapFactory.decodeByteArray(outputStream.toByteArray(),0,outputStream.size());
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        matrix.postTranslate(mWidth,0);
        SurfaceHolder mHolder = mSurfaceView.getHolder();
        Canvas c = mHolder.lockCanvas();
        if(c != null)
        c.save();
                c.rotate(90,c.getWidth()/2,c.getHeight()/2);
        c.drawBitmap(blurImageAmeliorate(Bitmap.createBitmap(b,0,0,b.()/50,b.getHeight()/50)),
                new Rect(0,0,b.getWidth()/50,b.getHeight()/50),new Rect(0,0,c.getWidth(),c.getHeight()),null);
        c.restore();
        mHolder.unlockCanvasAndPost(c);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCamera!= null){
            mCamera.release();
        }
    }
    private Bitmap blurImageAmeliorate(Bitmap bmp)
    {
        long start = System.currentTimeMillis();
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 16; // 值越小图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++)
        {
            for (int k = 1, len = width - 1; k < len; k++)
            {
                idx = 0;
                for (int m = -1; m <= 1; m++)
                {
                    for (int n = -1; n <= 1; n++)
                    {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * gauss[idx]);
                        newG = newG + (int) (pixG * gauss[idx]);
                        newB = newB + (int) (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        long end = System.currentTimeMillis();
        Log.d("may", "used time="+(end - start));
        return bitmap;
    }
}
