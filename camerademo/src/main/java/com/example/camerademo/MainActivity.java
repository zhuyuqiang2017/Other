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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.graphics.YuvImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mContainer1,mContainer2;
    private CameraPreview mPreview;
    private Camera mCamera;
    private CameraCallBack mCallBack;
    private SurfaceView mSurfaceView;
    private int mWidth,mHeight;
    private float width,height;
    private ImageView show_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_image = (ImageView) findViewById(R.id.image_view);
        mContainer1 = (LinearLayout)findViewById(R.id.container1);
        mContainer2 = (LinearLayout)findViewById(R.id.container2);
        mCamera = Camera.open(0);
        mPreview = new CameraPreview(this, mCamera);
        mContainer1.addView(mPreview);
        mCallBack = new CameraCallBack();
        mCamera.setPreviewCallback(mCallBack);
//        mSurfaceView = new SurfaceView(this);
//        mContainer2.addView(mSurfaceView);
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
        show_image.setImageBitmap(Blur(b));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCamera!= null){
            mCamera.release();
        }
    }

    private Bitmap Blur(Bitmap b){
        long start = System.currentTimeMillis();
        int width = b.getWidth();
        int height = b.getHeight();
        Bitmap result = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        int color = 0;
        Random rnd = new Random();
        int iModel = 10;
        int i = width-iModel;
        while(i>1){
            int j = height-iModel;
            while(j >1){
                int iPos = rnd.nextInt(1000)%iModel;
                color = b.getPixel(i+iPos,j+iPos);
                result.setPixel(i,j,color);
                j--;
            }
            i--;
        }
        Log.e("zyq","total time = "+(System.currentTimeMillis()-start));
        return result;
    }
}
