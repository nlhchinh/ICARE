package com.example.icare.logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.icare.logic.model.DailyLog;

import java.util.List;

@Dao
public interface DailyLogDao {
    @Insert
    void insert(DailyLog log);

    @Query("SELECT * FROM daily_log WHERE date = :date LIMIT 1")
    DailyLog getLogByDate(String date);

    @Query("SELECT * FROM daily_log")
    List<DailyLog> getAllLogs();
}
