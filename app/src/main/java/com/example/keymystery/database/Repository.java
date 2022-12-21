package com.example.keymystery.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

public class Repository {
    private UserDao userDao;

     Repository( Application application) {
        DataBase database = DataBase.getDatabase(application);
        userDao = database.userDao();
    }

    public void insertUsers(User user){
        DataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUsers(user);

            }
        });
    }
    LiveData<List<User>> getAllUsersData(){
        return userDao.getAllUsersData();
    }
}
