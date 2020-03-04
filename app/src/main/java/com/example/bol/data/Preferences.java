package com.example.bol.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.bol.domain.Product;

public class Preferences {
    public static void storeString(String key, String value, Activity activity){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String retrieveString(String key, Activity activity){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
       return sharedPreferences.getString(key, null);
    }

}
