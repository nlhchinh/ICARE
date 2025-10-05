package com.example.icare.logic.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "period_infor")
public class PeriodInfor {
    @PrimaryKey
    @NonNull
    private String periodInforId = "";
    private String userId;
    private String startDay;
    private int cycleLength;
    private int periodLength;

    @Ignore
    public PeriodInfor() {
    }

    public PeriodInfor(@NonNull String periodInforId, String userId, String startDay, int cycleLength, int periodLength) {
        this.periodInforId = periodInforId;
        this.userId = userId;
        this.startDay = startDay;
        this.cycleLength = cycleLength;
        this.periodLength = periodLength;
    }

    @NonNull
    public String getPeriodInforId() {
        return periodInforId;
    }

    public void setPeriodInforId(@NonNull String periodInforId) {
        this.periodInforId = periodInforId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public int getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(int cycleLength) {
        this.cycleLength = cycleLength;
    }

    public int getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(int periodLength) {
        this.periodLength = periodLength;
    }

    @Override
    public String toString() {
        return "PeriodInfor{" +
                "periodInforId='" + periodInforId + '\'' +
                ", userId='" + userId + '\'' +
                ", startDay='" + startDay + '\'' +
                ", cycleLength=" + cycleLength +
                ", periodLength=" + periodLength +
                '}';
    }
}
