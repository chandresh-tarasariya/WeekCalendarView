package com.chandresh.weekcalendarview.implementor;

import android.view.View;

/**
 * Created by dtuser on 4/8/2017.
 */

public interface onCellClickListener {
    void onCellClicked(View view, int clickedHour, int clickedMinute);
}
