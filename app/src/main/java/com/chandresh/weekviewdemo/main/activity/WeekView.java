package com.chandresh.weekviewdemo.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


import com.chandresh.weekcalendarview.implementor.onEventClickListener;
import com.chandresh.weekviewdemo.R;
import com.chandresh.weekcalendarview.implementor.onCellClickListener;
import com.chandresh.weekcalendarview.widget.WeekViewWidget;
import com.chandresh.weekcalendarview.model.ModelEventData;
import com.chandresh.weekcalendarview.model.ModelHeaderData;

import java.util.ArrayList;
import java.util.List;

public class WeekView extends AppCompatActivity implements onCellClickListener, onEventClickListener {
    WeekViewWidget wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        wv = (WeekViewWidget) findViewById(R.id.wv);
        setData();
    }

    private void setData() {
        List<ModelHeaderData> headerList = new ArrayList<>();
        List<ModelEventData> contentList = new ArrayList<>();
        headerList.clear();
        ModelHeaderData modelHeaderData = new ModelHeaderData();
        modelHeaderData.setTitle("Chandresh Tarasariya");
        modelHeaderData.setID("1");
        headerList.add(modelHeaderData);

        modelHeaderData = new ModelHeaderData();
        modelHeaderData.setTitle("Amit Viradiya");
        modelHeaderData.setID("2");
        headerList.add(modelHeaderData);

        modelHeaderData = new ModelHeaderData();
        modelHeaderData.setTitle("Vivek pipaliya");
        modelHeaderData.setID("3");
        headerList.add(modelHeaderData);

        contentList.clear();
        ModelEventData modelEventData = new ModelEventData();
        modelEventData.setID("1");
        modelEventData.setTitle("User1");
        modelEventData.setDesc("Hair Cut,wash,di,coloring,spa,statening many moreHair Cut,wash,di,coloring,spa,statening many more");
        modelEventData.setEventHour(17);
        modelEventData.setEventMinute(0);
        modelEventData.setDuration(75);
        modelEventData.setHeaderID("1");
        modelEventData.setEventType("1");
        modelEventData.setBgColorHexCode("#d3d3d3");
        contentList.add(modelEventData);

        modelEventData = new ModelEventData();
        modelEventData.setID("2");
        modelEventData.setTitle("User2");
        modelEventData.setDesc("Hair wash");
        modelEventData.setEventHour(0);
        modelEventData.setEventMinute(0);
        modelEventData.setDuration(30);
        modelEventData.setHeaderID("1");
        modelEventData.setEventType("1");
        modelEventData.setBgColorHexCode("#D4AAFF");
        contentList.add(modelEventData);

        modelEventData = new ModelEventData();
        modelEventData.setID("3");
        modelEventData.setTitle("User3");
        modelEventData.setDesc("Hair wash");
        modelEventData.setEventHour(1);
        modelEventData.setEventMinute(00);
        modelEventData.setDuration(30);
        modelEventData.setHeaderID("1");
        modelEventData.setEventType("1");
        modelEventData.setBgColorHexCode("#0000FF");
        contentList.add(modelEventData);

        modelEventData = new ModelEventData();
        modelEventData.setID("4");
        modelEventData.setTitle("");
        modelEventData.setDesc("Lunch");
        modelEventData.setEventHour(2);
        modelEventData.setEventMinute(00);
        modelEventData.setDuration(45);
        modelEventData.setHeaderID("1");
        modelEventData.setEventType("2");
        modelEventData.setBgColorHexCode("#00FF00");
        contentList.add(modelEventData);

        modelEventData = new ModelEventData();
        modelEventData.setID("5");
        modelEventData.setTitle("User1");
        modelEventData.setDesc("Hair wash");
        modelEventData.setEventHour(3);
        modelEventData.setEventMinute(15);
        modelEventData.setDuration(45);
        modelEventData.setHeaderID("2");
        modelEventData.setEventType("1");
        modelEventData.setBgColorHexCode("#F08080");
        contentList.add(modelEventData);

        modelEventData = new ModelEventData();
        modelEventData.setID("6");
        modelEventData.setTitle("User1");
        modelEventData.setDesc("Hair wash");
        modelEventData.setEventHour(4);
        modelEventData.setEventMinute(15);
        modelEventData.setDuration(45);
        modelEventData.setHeaderID("3");
        modelEventData.setEventType("1");
        modelEventData.setBgColorHexCode("#FF0000");
        contentList.add(modelEventData);

        modelEventData = new ModelEventData();
        modelEventData.setID("7");
        modelEventData.setTitle("User1");
        modelEventData.setDesc("Hair wash");
        modelEventData.setEventHour(11);
        modelEventData.setEventMinute(15);
        modelEventData.setDuration(45);
        modelEventData.setHeaderID("3");
        modelEventData.setEventType("2");
        modelEventData.setBgColorHexCode("#FFFF00");
        contentList.add(modelEventData);

        modelEventData = new ModelEventData();
        modelEventData.setID("8");
        modelEventData.setTitle("User1");
        modelEventData.setDesc("Hair wash");
        modelEventData.setEventHour(19);
        modelEventData.setEventMinute(15);
        modelEventData.setDuration(180);
        modelEventData.setHeaderID("3");
        modelEventData.setEventType("1");
        modelEventData.setBgColorHexCode("#800000");
        contentList.add(modelEventData);

        wv.setEventData(headerList, contentList);
        wv.setOnCellClickListener(this);
        wv.setOnEventClickListener(this);
    }

    @Override
    public void onCellClicked(View view, int clickedHour, int clickedMinute) {
        Toast.makeText(WeekView.this, "onCellClicked===hour==" + clickedHour + ",,,,,,minute===" + clickedMinute + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventClicked(View view, String eventID) {
        Toast.makeText(WeekView.this, "onEventClicked===eventID==" + eventID, Toast.LENGTH_SHORT).show();
    }
}
