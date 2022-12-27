package com.example.keymystery.model;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class AppUtility {
    public static String readFromAssets(Context context, String fileName) {
        String string = "";
        try {
            InputStream inputStream=context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] byteObject = new byte[size];
            inputStream.read(byteObject);
            inputStream.close();
            string = new String(byteObject, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}
