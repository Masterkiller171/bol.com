package com.example.bol.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.bol.domain.Product;

public class Preferences {
    /**
     * Stores a String as persistent data using shared preferences
     * @param key {key for storing value}
     * @param value {value which needs to be stored}
     * @param activity {the context of the calling activity}
     */
    public static void storeString(String key, String value, Activity activity){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Stores a Boolean as persistent data using shared preferences
     * @param key {key for storing value}
     * @param value {value which needs to be stored}
     * @param activity {the context of the calling activity}
     */
    public static void storeBoolean(String key, boolean value, Activity activity){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Retrieves a String from shared preferences
     * @param key {key for retrieving value}
     * @param activity  {the context of the calling activity}
     * @return {stored value using the key}
     */
    public static String retrieveString(String key, Activity activity){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
       return sharedPreferences.getString(key, null);
    }

}
