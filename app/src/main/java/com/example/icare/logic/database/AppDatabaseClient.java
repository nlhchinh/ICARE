package com.example.icare.logic.database;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseClient {
    private static AppDatabaseClient instance;
    private final AppDatabase appDatabase;

    private AppDatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "icare_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries() // để test cho nhanh, sau nên dùng background
                .build();
    }

    public static synchronized AppDatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
