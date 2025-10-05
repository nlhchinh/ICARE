package com.example.icare.logic.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_log")
public class DailyLog {

    @PrimaryKey
    @NonNull
    private String dailyLogId = "";

    private String date;
    private String symptoms;
    private String mood;
    private String bloodFlow;
    private String note;

    @Ignore
    public DailyLog() {
    }

    public DailyLog(@NonNull String dailyLogId, String date, String symptoms, String mood, String bloodFlow, String note) {
        this.dailyLogId = dailyLogId;
        this.date = date;
        this.symptoms = symptoms;
        this.mood = mood;
        this.bloodFlow = bloodFlow;
        this.note = note;
    }

    @NonNull
    public String getDailyLogId() {
        return dailyLogId;
    }

    public void setDailyLogId(@NonNull String dailyLogId) {
        this.dailyLogId = dailyLogId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getBloodFlow() {
        return bloodFlow;
    }

    public void setBloodFlow(String bloodFlow) {
        this.bloodFlow = bloodFlow;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "DailyLog{" +
                "dailyLogId='" + dailyLogId + '\'' +
                ", date='" + date + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", mood='" + mood + '\'' +
                ", bloodFlow='" + bloodFlow + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
