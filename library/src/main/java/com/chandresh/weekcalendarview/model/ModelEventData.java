package com.chandresh.weekcalendarview.model;

/**
 * Created by dtuser on 4/6/2017.
 */

public class ModelEventData {
    private String ID = "";
    private String title = "";
    private String desc = "";
    private int eventHour = 00;
    private int eventMinute = 00;
    private int duration = 60;
    private String headerID = "";
    private String eventType = "";
    private String bgColorHexCode = "";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEventHour() {
        return eventHour;
    }

    public void setEventHour(int eventHour) {
        this.eventHour = eventHour;
    }

    public int getEventMinute() {
        return eventMinute;
    }

    public void setEventMinute(int eventMinute) {
        this.eventMinute = eventMinute;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHeaderID() {
        return headerID;
    }

    public void setHeaderID(String headerID) {
        this.headerID = headerID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBgColorHexCode() {
        return bgColorHexCode;
    }

    public void setBgColorHexCode(String bgColorHexCode) {
        this.bgColorHexCode = bgColorHexCode;
    }
}
