package com.example.keymystery.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LevelsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void  insertLevels (Levels levels);
    @Query("select * from Levels  ")
    LiveData<List<Levels>> getAllLevelsData();
}
