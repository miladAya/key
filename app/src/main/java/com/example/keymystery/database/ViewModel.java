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
     repository.insertUsers(user);
    }
    LiveData<List<User>> getAllUsersData(){
        return repository.getAllUsersData();
    }
}
