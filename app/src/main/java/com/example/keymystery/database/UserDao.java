package com.example.keymystery.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public void insertUsers(User user);
    @Query("select * from User")
    LiveData<List<User>>getAllUsersData();
}
