package com.example.keymystery.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public class Repository {
  private   UserDao userDao;
  private   LevelsDao levelsDao;
  private  QuestionDao questionDao;

    Repository( Application application) {
        DataBase database = DataBase.getDatabase(application);
        userDao = database.userDao();
        questionDao=database.questionDao();
        levelsDao=database.levelsDao();

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

    void  insertLevels (Levels levels){
        DataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelsDao.insertLevels(levels);
            }
        });
    }

    LiveData<List<Levels>> getAllLevelsData(){
        return levelsDao.getAllLevelsData();

    }


               void insertQuestion(Question question){
                  DataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                questionDao.insertQuestion(question);
            }
        });
       }
       LiveData<List<Question>> getAllQuestionsData(){
         return questionDao.getAllQuestionsData();
       }



    }


