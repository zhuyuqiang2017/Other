package com.example.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.animation.CycleInterpolator;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class DBhelper {
    private Context mContext;

    public DBhelper(Context context) {
        this.mContext = context;
        db = new DB(mContext, "test");
    }

    private DB db;

    private class DB extends SQLiteOpenHelper {
        private String Create_tb = "create table user(_id int,name varchar(20))";

        public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public DB(Context context, String name) {
            this(context, name, null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Create_tb);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public Cursor query() {
        SQLiteDatabase d = db.getReadableDatabase();
        Cursor c = d.query("user", new String[]{"_id","name"}, null, null, null, null, null, null);
        return c;
    }

    public void insert(String string) {
        SQLiteDatabase d = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", string);
        d.insert("user", null, values);
    }
}
