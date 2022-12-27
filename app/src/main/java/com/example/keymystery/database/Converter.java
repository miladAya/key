package com.example.keymystery.database;

import android.content.Context;

import androidx.room.TypeConverter;

import com.example.keymystery.model.AppUtility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class Converter {
    private Context context;
//@TypeConverter
//public static ArrayList<String> fromString(String value) {
//    Type listType = new TypeToken<ArrayList<String>>() {}.getType();
//    return new Gson().fromJson(value, listType);
//}
//
//    @TypeConverter
//    public static String fromArrayList(ArrayList<String> list) {
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//        return json;

//    @TypeConverter // note this annotation
//public String fromObjectArrayList(ArrayList<Level.Question> questionArrayList) {
//    if (questionArrayList == null) {
//        return (null);
//    }
////    Type type = new TypeToken<ArrayList<Level.Question>>() {}.getType();
////    String json = new Gson().toJson(questionArrayList, type);
//    String json = new Gson().toJson(questionArrayList);
//    return json;
//}
//
//    @TypeConverter // note this annotation
//    public ArrayList<Level.Question> toObjectArrayList(String optionValuesString) {
//        if (optionValuesString == null) {
//            return (null);
//        }
//        Type type = new TypeToken<ArrayList<Level.Question>>() {}.getType();
//        ArrayList<Level.Question> productCategoriesList = new Gson().fromJson(optionValuesString,type);
//        return productCategoriesList;
//    }

//    }





    @TypeConverter
    public static ArrayList<Level.Question> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Level.Question>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromArrayList(ArrayList<Level.Question> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}
