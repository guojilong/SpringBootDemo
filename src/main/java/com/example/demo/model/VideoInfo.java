package com.example.demo.model;

public class VideoInfo {
    public static final int TYPE_LIVING = 1;
    public static final int TYPE_VIDEO = 0;

    private String url;
    private long position;
    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
