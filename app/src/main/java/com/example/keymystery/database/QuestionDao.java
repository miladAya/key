package com.example.keymystery.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void  insertQuestion (Question question);
    @Query("select * from Question ")
    LiveData<List<Question>> getAllQuestionsData();
}
