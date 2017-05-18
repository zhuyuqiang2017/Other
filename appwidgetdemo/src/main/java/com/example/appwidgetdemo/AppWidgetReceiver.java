package com.example.appwidgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class AppWidgetReceiver extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i("zyq","AppWidgetReceiver:onReceive");

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i("zyq","AppWidgetReceiver:onUpdate");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.app_widget_layout);
        PendingIntent i = PendingIntent.getActivity(context,0,new Intent(context,MainActivity.class),0);
        remoteViews.setOnClickPendingIntent(R.id.text_clock_container,i);
        for (int i1 = 0;i1<appWidgetIds.length;i1++){
            appWidgetManager.updateAppWidget(appWidgetIds[i1], remoteViews);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.i("zyq","AppWidgetReceiver:onAppWidgetOptionsChanged");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i("zyq","AppWidgetReceiver:onDeleted");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i("zyq","AppWidgetReceiver:onEnabled");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.i("zyq","AppWidgetReceiver:onDisabled");
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Log.i("zyq","AppWidgetReceiver:onRestored");
    }
}
