package com.perrest.restaurante.sincpedidos.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.User;

public class SharedPrefsUtil {

    private static final String tableKey = "table_key";
    private static final String tableSelectedTimestampKey = "table_selected_timestamp";
    private static final long fourHours = 14400000L;
    private static final String usernameKey = "username_key";
    private static final String passwordKey = "password_key";
    private static final String tokenKey = "token_key";

    public static void saveSelectedTable(Context context, int id) {
        SharedPreferences.Editor sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE).edit();
        sharedPreferences.putInt(tableKey, id);
        sharedPreferences.putLong(tableSelectedTimestampKey, System.currentTimeMillis());
        sharedPreferences.apply();
    }

    public static int getSelectedTable(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        long lastTableSelectedTimestamp = sharedPreferences.getLong(tableSelectedTimestampKey, -1);
        if (lastTableSelectedTimestamp == -1 && lastTableSelectedTimestamp + fourHours < System.currentTimeMillis()) {
            return -1;
        } else {
            return sharedPreferences.getInt(tableKey, -1);
        }
    }

    public static void saveLoginInfo(Context context, User user) {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE).edit();
        editor.putString(usernameKey, user.getNomeUsuario());
        editor.putString(passwordKey, user.getSenha());
        editor.apply();
    }

    public static User getLoginInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        User user = new User();
        user.setNomeUsuario(sharedPreferences.getString(usernameKey, ""));
        user.setSenha(sharedPreferences.getString(passwordKey, ""));
        return user;
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE).edit();
        editor.putString(tokenKey, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        return sharedPreferences.getString(tokenKey, "");
    }
}
