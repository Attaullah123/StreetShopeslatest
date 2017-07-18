package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UtilitySharedPreferences {

    /**
     * Method to put string value in shared preference
     *
     * @param context Context of the calling class
     * @param key     Key in which value to store
     * @param value   String value to be stored
     */
    public static void putStringValueInSharedPreference(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Method to put long value in shared preference
     *
     * @param context Context of the calling class
     * @param key     Key in which value to store
     * @param value   Long value to be stored
     */
    public static void putLongValueInSharedPreference(Context context, String key, long value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Method to put boolean value in shared preference
     *
     * @param context Context of the calling class
     * @param key     Key in which value to store
     * @param value   Boolean value to be stored
     */
    public static void putBooleanValueInSharedPreference(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Method to get string value from shared preference
     *
     * @param context Context of the calling class
     * @param param   Key from which value is retrieved
     */
    public static String getStringSharedPreference(Context context, String param) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(param, "");
    }

    /**
     * Method to get long value from shared preference
     *
     * @param context Context of the calling class
     * @param param   Key from which value is retrieved
     */
    public static long getLongSharedPreference(Context context, String param) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getLong(param, 0);
    }

    /**
     * Method to get long value from shared preference
     *
     * @param context      Context of the calling class
     * @param param        Key from which value is retrieved
     * @param defaultValue Return default value if not found
     */
    public static long getLongSharedPreference(Context context, String param, int defaultValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getLong(param, defaultValue);
    }

    /**
     * Method to get boolean value from shared preference
     *
     * @param context Context of the calling class
     * @param param   Key from which value is retrieved
     */
    public static boolean getBooleanSharedPreference(Context context, String param) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(param, false);
    }

    /**
     * Method to clear shared preference key value
     *
     * @param context Context of the calling class
     * @param key     Key from which value is to be cleared
     */
    public static void clearSharedPrefData(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * Method to clear all shared preference key value
     *
     * @param context Context of the calling class
     */
    public static void clearAllSharedPrefData(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


}
