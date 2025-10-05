package com.example.icare.logic.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.icare.logic.dao.DailyLogDao;
import com.example.icare.logic.dao.PeriodInforDao;
import com.example.icare.logic.dao.UserDao;
import com.example.icare.logic.model.DailyLog;
import com.example.icare.logic.model.PeriodInfor;
import com.example.icare.logic.model.User;

@Database(entities = {User.class, PeriodInfor.class, DailyLog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract PeriodInforDao periodInforDao();
    public abstract DailyLogDao dailyLogDao();
}
