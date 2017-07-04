package com.example.xfermodedemo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Bitmap mSourceBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView)findViewById(R.id.show_result);
        mSourceBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tttt);
    }

    public void usingRoundRect(View view){
        Bitmap b = Bitmap.createBitmap(mSourceBitmap.getWidth(),mSourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG|Paint.FILTER_BITMAP_FLAG);
        p.setColor(Color.RED);
        c.drawRoundRect(0,0,mSourceBitmap.getWidth(),mSourceBitmap.getHeight(),150,150,p);
        Bitmap result = getResultBitmap(mSourceBitmap.getWidth(),mSourceBitmap.getHeight(),b);
        mImageView.setImageBitmap(result);
    }

    public void usingLove(View view){
        Bitmap b = Bitmap.createBitmap(mSourceBitmap.getWidth(),mSourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG|Paint.FILTER_BITMAP_FLAG);
        p.setColor(Color.RED);
        VectorDrawable d = (VectorDrawable) getDrawable(R.drawable.ic_favorite_black_24dp);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        d.draw(c);
        Bitmap result = getResultBitmap(mSourceBitmap.getWidth(),mSourceBitmap.getHeight(),b);
        mImageView.setImageBitmap(result);
    }

    public Bitmap getResultBitmap(int width,int height,Bitmap maskBitmap){
        android.util.Log.i("zyq","width = "+width+",height="+height);
        Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG|Paint.FILTER_BITMAP_FLAG);
        Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        c.drawBitmap(mSourceBitmap,0,0,p);
        p.setXfermode(mXfermode);
        c.drawBitmap(maskBitmap,0,0,p);
        p.setXfermode(null);
        return b;
    }

    public void usingCircle(View view){
        Bitmap b = Bitmap.createBitmap(mSourceBitmap.getWidth(),mSourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG|Paint.FILTER_BITMAP_FLAG);
        p.setColor(Color.RED);
        c.drawCircle(mSourceBitmap.getWidth()/2,mSourceBitmap.getHeight()/2,Math.min(mSourceBitmap.getWidth()/2,mSourceBitmap.getHeight()/2),p);
        Bitmap result = getResultBitmap(mSourceBitmap.getWidth(),mSourceBitmap.getHeight(),b);
        mImageView.setImageBitmap(result);
    }

    public void usingOval(View view){
        Bitmap b = Bitmap.createBitmap(mSourceBitmap.getWidth(),mSourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG|Paint.FILTER_BITMAP_FLAG);
        p.setColor(Color.RED);
        c.drawOval(0,0,mSourceBitmap.getWidth(),mSourceBitmap.getHeight(),p);
        Bitmap result = getResultBitmap(mSourceBitmap.getWidth(),mSourceBitmap.getHeight(),b);
        mImageView.setImageBitmap(result);
    }
}