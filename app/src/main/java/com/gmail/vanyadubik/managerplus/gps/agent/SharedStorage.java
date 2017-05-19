//package com.gmail.vanyadubik.managerplus.gps.agent;
//
//
//import android.content.Context;
//import android.content.SharedPreferences.Editor;
//
//public class SharedStorage {
//    public static final String APP_PREFS = "AgentP2Prefs";
//
//    private SharedStorage() {
//    }
//
//    public static String getString(Context context, String key, String defValue) {
//        String result = null;
//        try {
//            result = context.getSharedPreferences(APP_PREFS, 0).getString(key, defValue);
//        } catch (ClassCastException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static boolean getBoolean(Context context, String key, boolean defValue) {
//        return context.getSharedPreferences(APP_PREFS, 0).getBoolean(key, defValue);
//    }
//
//    public static int getInteger(Context context, String key, int defValue) {
//        return context.getSharedPreferences(APP_PREFS, 0).getInt(key, defValue);
//    }
//
//    public static long getLong(Context context, String key, long defValue) {
//        return context.getSharedPreferences(APP_PREFS, 0).getLong(key, defValue);
//    }
//
//    public static void setString(Context context, String key, String value) {
//        Editor editor = context.getSharedPreferences(APP_PREFS, 0).edit();
//        editor.putString(key, value);
//        editor.commit();
//    }
//
//    public static void setBoolean(Context context, String key, Boolean value) {
//        Editor editor = context.getSharedPreferences(APP_PREFS, 0).edit();
//        editor.putBoolean(key, value.booleanValue());
//        editor.commit();
//    }
//
//    public static void setInteger(Context context, String key, int value) {
//        Editor editor = context.getSharedPreferences(APP_PREFS, 0).edit();
//        editor.putInt(key, value);
//        editor.commit();
//    }
//}
