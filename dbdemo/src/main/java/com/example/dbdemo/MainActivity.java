package com.example.dbdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView l;
    private SimpleCursorAdapter mAdapter;
    private DBhelper mhelper;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mhelper = new DBhelper(MainActivity.this);
        mAdapter = new SimpleCursorAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,mhelper.query(),
                new String[]{"name"},
                new int[]{android.R.id.text1},0);
        l = (ListView) findViewById(R.id.list_view);
        l.setAdapter(mAdapter);
    }

    public void insert(View view){
        mhelper.insert("name"+count);
        count ++;
    }
}
