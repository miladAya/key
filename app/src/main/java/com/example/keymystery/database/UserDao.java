package com.example.keymystery.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void  insertUser (User user);
    @Delete
    void  deleteUser (User user);


    @Query("select * from User")
    LiveData<List<User>>getAllUsersData();


}
