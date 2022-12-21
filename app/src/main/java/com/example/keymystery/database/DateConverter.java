package com.example.keymystery.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
@TypeConverter
public Date getData(Long data ){
        return new Date(data);

    }
    public  Long toDate(Date date){
    return date.getTime();

    }
}
