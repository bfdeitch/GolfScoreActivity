package com.teamtreehouse.golfscoreactivity;

public class Hole {
    private String mLabel;
    private int mStrokeCount;

    public Hole (String label, int strokeCount) {
        mLabel = label;
        mStrokeCount = strokeCount;
    }

    public String getLabel() {
        return mLabel;
    }

    public int getStrokeCount() {
        return mStrokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        mStrokeCount = strokeCount;
    }
}
