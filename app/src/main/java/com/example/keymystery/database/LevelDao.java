package com.example.keymystery.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LevelDao {
    @Insert
    void  insertLevel (Level level);
    @Query("select * from Level  ")
    LiveData<List<Level>> getAllLevelsData();
}
