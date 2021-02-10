package com.wikitech.organizer.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesDataSource {
    public static final String VERSION_KEY = "VERSION_KEY";

    private static final String ORGANIZER_SHARED_PREF = "ORGANIZER_SHARED_PREF";
    private SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor = sharedPreferences.edit();

    public SharedPreferencesDataSource(Context context){
        sharedPreferences = context.getSharedPreferences(ORGANIZER_SHARED_PREF, Context.MODE_PRIVATE);
    }

    public String getString(String key){
        return sharedPreferences.getString(key, "");
    }

    public void putString(String key, String value){
        editor.putString(key, value);
    }
}
