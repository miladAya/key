package com.example.keymystery.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;

    public ViewModel (@NonNull Application application) {
        super(application);
        repository=new Repository(application);


    }
    public void insertUsers(User user){
     repository.insertUser(user);
    }
  public   LiveData<List<User>> getAllUsersData(){
        return repository.getAllUsersData();
    }

   public void  insertLevels (Levels levels){
                repository.insertLevels(levels);

    }
  public   LiveData<List<Levels>> getAllLevelsData(){
        return repository.getAllLevelsData();

    }


   public void insertQuestion(Question question){
      repository.insertQuestion(question);
    }
    public LiveData<List<Question>> getAllQuestionsData(){
        return repository.getAllQuestionsData();
    }
    public void  deleteUser (User user){
   repository.deleteUser(user);
    }
    public void  deleteQuestion (Question question){
      repository.deleteQuestion(question);
    }

    }
