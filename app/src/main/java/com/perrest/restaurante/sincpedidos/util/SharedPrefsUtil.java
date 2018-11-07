package com.perrest.restaurante.sincpedidos.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.perrest.restaurante.sincpedidos.R;

public class SharedPrefsUtil {

    private static final String table = "table";
    private static final String tableSelectedTimestamp = "table_selected_timestamp";
    private static final long fourHours = 14400000L;

    public static void saveSelectedTable(Context context, int id) {
        SharedPreferences.Editor sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE).edit();
        sharedPreferences.putInt(table, id);
        sharedPreferences.putLong(tableSelectedTimestamp, System.currentTimeMillis());
        sharedPreferences.apply();
    }

    public static int getSelectedTable(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        long lastTableSelectedTimestamp = sharedPreferences.getLong(tableSelectedTimestamp,-1);
        if(lastTableSelectedTimestamp == -1 &&  lastTableSelectedTimestamp + fourHours < System.currentTimeMillis()) {
            return -1;
        } else {
            return sharedPreferences.getInt(table,-1);
        }
    }
}
