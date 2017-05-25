package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GraphStock extends AppCompatActivity {
    String history;
    String[] days;
//    Date[] date;
    String[] transport;


//    Date RealDate;
//    Calendar calendar = Calendar.getInstance();           // to cast from string to date

    // Graph


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_stock);

        Intent intent = getIntent();
        String symbol = intent.getStringExtra("symbol");

        Uri uri = Contract.Quote.URI;      // get the uri for the tablename

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(symbol)) {
                history = cursor.getString(5);
                break;
            }
        }

        days = history.split("\n");

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Entry> value = new ArrayList<>();


        for (int i = 0; i < days.length; i++) {
            transport = days[i].split(", ");
//            calendar.setTimeInMillis(Long.parseLong(transport[0]));        // to cast from String to long to date
//            RealDate = calendar.getTime();
//            date[i] = RealDate;                       // X value
            dates.add(changetoDate(transport[0]));
//            entries[i]=transport[1];
            value.add(new Entry( Float.valueOf(Float.parseFloat(transport[1])),i));                      // Y Value
        }


//        cursor.moveToNext();
//        for (int i = 0;i<6;i++)
//        {
//          h=cursor.getString(1);
//         history =cursor.getString(5);
//            cursor.moveToNext();
//        }

        LineChart lChart = (LineChart) findViewById(R.id.mygraph);

        LineDataSet dataSet = new LineDataSet(value,getString(R.string.description_of_label));
        dataSet.setColor(R.color.colorPrimaryDark);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(R.color.material_green_700);
        dataSet.setValueTextColor(R.color.material_blue_500);

        LineData lineData = new LineData(dates,dataSet);
        lChart.setData(lineData);
        lChart.invalidate();

        XAxis xAxis = lChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        Legend legend = lChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);

        lChart.setDrawBorders(true);
        lChart.setDrawGridBackground(true);
        lChart.setTouchEnabled(true);
        lChart.setDragEnabled(true);
        lChart.setKeepPositionOnRotation(true);
        lChart.setDescription(getString(R.string.line_graph_description));
        lChart.setScaleEnabled(true);
        lChart.setPinchZoom(true);
        lChart.setBackgroundColor(getResources().getColor(R.color.gray));
        lChart.setGridBackgroundColor(R.color.material_green_700);


        lChart.setContentDescription(getString(R.string.contentDescription));


    }

    public static String changetoDate(String s) {

        String x = s;

        long milliseconds = Long.valueOf(x);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.format(new Date(milliseconds));
    }


}
