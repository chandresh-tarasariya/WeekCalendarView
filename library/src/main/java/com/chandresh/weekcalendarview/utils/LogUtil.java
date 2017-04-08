package com.chandresh.weekcalendarview.utils;

import android.util.Log;

/**
 * Created by chandresh on 07/25/2016.
 */
public class LogUtil {
    public static boolean IS_Debug_mode = true;

    public static void Print(String tag, String text) {

        if (IS_Debug_mode)
            Log.e(tag, "==========" + text);
    }
}
