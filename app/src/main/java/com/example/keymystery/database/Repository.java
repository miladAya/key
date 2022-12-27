package com.example.keymystery.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

public class Repository {
  private   UserDao userDao;
  private LevelDao levelDao;



    Repository( Application application) {
        DataBase database = DataBase.getDatabase(application);
        userDao = database.userDao();
//        levelDao=database.levelDao();
        levelDao=database.levelDao();
    }

    void insertUser(User user) {
        DataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
            }
        });
    }

    LiveData<List<User>> getAllUsersData(){
        return userDao.getAllUsersData();
    }



    LiveData<List<Level>> getAllLevelData(){
        return  levelDao.getAllLevelsData();
    }
   public void insertLevelData(Level level){
        DataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.insertLevel(level);
            }
        });



    }

}
