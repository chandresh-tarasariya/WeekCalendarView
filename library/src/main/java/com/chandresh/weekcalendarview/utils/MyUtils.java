package com.chandresh.weekcalendarview.utils;

import android.content.res.Resources;

/**
 * Created by dtuser on 4/7/2017.
 */

public class MyUtils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
