package com.example.keymystery.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Question.class,Levels.class}, version = 26, exportSchema = false)
abstract class DataBase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract LevelsDao levelsDao();
    public abstract QuestionDao questionDao();



    private static volatile DataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DataBase.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}