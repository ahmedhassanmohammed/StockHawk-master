package com.udacity.stockhawk.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Ahmed Hassan on 5/5/2017.
 */

public class ListWidgetService extends RemoteViewsService {
    /*
* call the Adapter of the listview
*  Adapter is ListProvider
* */

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        return (new WidgetListProvider(this.getApplicationContext(), intent));
    }

}
//    Cursor data =null;
//    @Override
//    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
//        return new RemoteViewsService.RemoteViewsFactory() {
//            @Override
//            public void onCreate() {
//
//
//            }
//
//            @Override
//            public void onDataSetChanged() {
//
//
//                data = getContentResolver().query(Contract.Quote.URI,new String[]{Contract.Quote._ID, Contract.Quote.COLUMN_SYMBOL, Contract.Quote.COLUMN_PRICE,
//                                Contract.Quote.COLUMN_PERCENTAGE_CHANGE, Contract.Quote.COLUMN_ABSOLUTE_CHANGE, Contract.Quote.COLUMN_HISTORY},
//                        Contract.Quote.POSITION_SYMBOL + " = ?",
//                        new String[]{"1"},
//                        null);
//
//            }
//
//            @Override
//            public void onDestroy() {
//                if(data!=null)
//                    data.close();
//                data=null;
//
//            }
//
//            @Override
//            public int getCount() {
//                return 0;
//            }
//
//            @Override
//            public RemoteViews getViewAt(int i) {
//                if (i == AdapterView.INVALID_POSITION ||
//                        data == null || !data.moveToPosition(i)) {
//                    return null;
//                }
//
//                RemoteViews views =new RemoteViews(getPackageName(),R.layout.list_item_quote);
//                String symbol = data.getString(data.getColumnIndex("symbol"));
//
//                views.setTextViewText(R.id.symbol, symbol);
//                views.setTextViewText(R.id.price, data.getString(data.getColumnIndex("price")));
//                views.setTextViewText(R.id.change, data.getString(data.getColumnIndex("percentage_change")));
//
//                return views;
//            }
//
//            @Override
//            public RemoteViews getLoadingView() {
//                return null;
//            }
//
//            @Override
//            public int getViewTypeCount() {
//                return 1;
//            }
//
//            @Override
//            public long getItemId(int position ) {
//                return position;
//            }
//
//            @Override
//            public boolean hasStableIds() {
//                return true;
//            }
//        };
//    }



