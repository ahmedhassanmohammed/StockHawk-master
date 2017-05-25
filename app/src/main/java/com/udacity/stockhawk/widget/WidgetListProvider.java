package com.udacity.stockhawk.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 5/16/2017.
 */


public class WidgetListProvider implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<String> symbolList = new ArrayList();
    private ArrayList<String> priceList = new ArrayList();
    private ArrayList<String> changeList = new ArrayList();

    private Context context;
    private int appWidgetId;

    public WidgetListProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        Uri uri = Contract.Quote.URI;      // get the uri for the tablename

        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            symbolList.add(cursor.getString(1));
            priceList.add(cursor.getString(2));
            changeList.add(cursor.getString(4));

            Log.e("WidgetListProvider: ",cursor.getString(1) );
        }
    }

    @Override
    public void onCreate() {

    }

    /*
    * getView of Adapter where instead of View we return RemoteViews
    */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_item_quote);

        remoteView.setTextViewText(R.id.symbol, symbolList.get(position));
        remoteView.setTextViewText(R.id.price, priceList.get(position));
        remoteView.setTextViewText(R.id.change, changeList.get(position));

        Bundle extras = new Bundle();
     //   extras.putInt(StockWidgetProvider.EXTRA_ITEM, position);
        extras.putString("symbol",symbolList.get(position));
        Intent intent = new Intent();
        intent.putExtras(extras);
        // Make it possible to distinguish the individual on-click action of a given item
        remoteView.setOnClickFillInIntent(R.id.list_item, intent);

        return remoteView;
    }

    @Override
    public int getCount() {
        return symbolList.size();
    }


    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}