package com.example.icare.logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.icare.logic.model.PeriodInfor;

import java.util.List;

@Dao
public interface PeriodInforDao {
    @Insert
    void insert(PeriodInfor periodInfor);

    @Query("SELECT * FROM period_infor WHERE userId = :userId")
    List<PeriodInfor> getByUserId(String userId);
}
