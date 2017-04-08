package com.chandresh.weekcalendarview.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chandresh.weekcalendarview.R;
import com.chandresh.weekcalendarview.implementor.onEventClickListener;
import com.chandresh.weekcalendarview.model.ModelHeaderData;
import com.chandresh.weekcalendarview.utils.LogUtil;

import com.chandresh.weekcalendarview.implementor.onCellClickListener;
import com.chandresh.weekcalendarview.model.ModelEventData;
import com.chandresh.weekcalendarview.utils.MyUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * Created by chandresh on 3/23/2016.
 */
public class WeekViewWidget extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "WeekViewWidget";
    public LinearLayout rootView;
    public Context ctx;
    public List<ModelHeaderData> headerList = new ArrayList<>();
    public List<ModelEventData> contentList = new ArrayList<>();
    public int startTime = 0;
    public int endTime = 23;
    public int noOfColumn = headerList.size();
    public int deviceWidth, deviceHeight;
    public int currentHours = Calendar.getInstance(Locale.ENGLISH).get(Calendar.HOUR_OF_DAY);
    public int currentMinute = Calendar.getInstance(Locale.ENGLISH).get(Calendar.MINUTE);

    public int headerLeftWidthInDp = (int) (getResources().getDimension(R.dimen.header_left_cell_width) / getResources().getDisplayMetrics().density);
    public int headerLeftHeightInDp = (int) (getResources().getDimension(R.dimen.header_left_cell_height) / getResources().getDisplayMetrics().density);
    public int columnDefaultWidthInDp = (int) (getResources().getDimension(R.dimen.content_cell_right_width_default) / getResources().getDisplayMetrics().density);

    public onCellClickListener mCellClickListener;
    public onEventClickListener mEventClickListener;

    public WeekViewWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx = context;
        initView();
    }

    public WeekViewWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        initView();
    }

    public WeekViewWidget(Context context) {
        super(context);
        ctx = context;
        initView();
    }

    private void initView() {

        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        deviceWidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels;


        rootView = (LinearLayout) inflate(ctx, R.layout.layout_week_view_widget, null);
        addRightHeaderRows();
        addLeftTimeRows();
        addRightContentRows();
        addEventSlot();
        scrollViewListener();
        removeAllViews();
        addView(rootView);
    }

    private void scrollViewListener() {
        final HorizontalScrollView hsv_content = (HorizontalScrollView) rootView.findViewById(R.id.hsv_content);
        final HorizontalScrollView hsv_header = (HorizontalScrollView) rootView.findViewById(R.id.hsv_header);
        hsv_content.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollX = hsv_content.getScrollX();
                if (hsv_header != null)
                    hsv_header.smoothScrollTo(scrollX, 0);
            }
        });
    }

    public void setEventData(List<ModelHeaderData> listHeader, List<ModelEventData> listEvents) {
        if (headerList.size() > 0)
            headerList.clear();
        if (contentList.size() > 0)
            contentList.clear();

        headerList.addAll(listHeader);
        contentList.addAll(listEvents);
        initView();
    }

    private void addLeftTimeRows() {
        LinearLayout layout_content_time = (LinearLayout) rootView.findViewById(R.id.layout_content_time);
        if (layout_content_time != null) {
            layout_content_time.removeAllViews();
            for (int i = startTime; i <= endTime; i++) {
                View layout_content_cell_left = inflate(ctx, R.layout.layout_content_cell_left, null);

                TextView tv_hour = (TextView) layout_content_cell_left.findViewById(R.id.tv_hour);
                LinearLayout layout_time_cell_minute = (LinearLayout) layout_content_cell_left.findViewById(R.id.layout_time_cell_minute);
                tv_hour.setText((("" + i).length() >= 2) ? "" + i : "0" + i);
                layout_time_cell_minute.removeAllViews();

                View layout_content_child_cell_left;
                layout_content_child_cell_left = inflate(ctx, R.layout.layout_content_child_cell_left, null);
                TextView tv_minutes;
                tv_minutes = (TextView) layout_content_child_cell_left.findViewById(R.id.tv_minute);
                if (i == currentHours && currentMinute < 15)
                    tv_minutes.setBackgroundColor(ContextCompat.getColor(ctx, R.color.current_time));
                tv_minutes.setText("00");
                layout_time_cell_minute.addView(layout_content_child_cell_left);

                layout_content_child_cell_left = inflate(ctx, R.layout.layout_content_child_cell_left, null);
                tv_minutes = (TextView) layout_content_child_cell_left.findViewById(R.id.tv_minute);
                if (i == currentHours && currentMinute >= 15 && currentMinute < 30)
                    tv_minutes.setBackgroundColor(ContextCompat.getColor(ctx, R.color.current_time));
                tv_minutes.setText("15");
                layout_time_cell_minute.addView(layout_content_child_cell_left);

                layout_content_child_cell_left = inflate(ctx, R.layout.layout_content_child_cell_left, null);
                tv_minutes = (TextView) layout_content_child_cell_left.findViewById(R.id.tv_minute);
                if (i == currentHours && currentMinute >= 30 && currentMinute < 45)
                    tv_minutes.setBackgroundColor(ContextCompat.getColor(ctx, R.color.current_time));
                tv_minutes.setText("30");
                layout_time_cell_minute.addView(layout_content_child_cell_left);

                layout_content_child_cell_left = inflate(ctx, R.layout.layout_content_child_cell_left, null);
                tv_minutes = (TextView) layout_content_child_cell_left.findViewById(R.id.tv_minute);
                if (i == currentHours && currentMinute >= 45 && currentMinute < 60)
                    tv_minutes.setBackgroundColor(ContextCompat.getColor(ctx, R.color.current_time));
                tv_minutes.setText("45");
                layout_time_cell_minute.addView(layout_content_child_cell_left);


                layout_content_time.addView(layout_content_cell_left);
            }
        }
    }

    private void addRightHeaderRows() {
        LinearLayout layout_header = (LinearLayout) rootView.findViewById(R.id.layout_header);
        if (layout_header != null) {
            layout_header.removeAllViews();
            View layout_header_cell_left = inflate(ctx, R.layout.layout_header_cell_left, null);
            layout_header.addView(layout_header_cell_left);

            View layout_header_row_right = inflate(ctx, R.layout.layout_header_row_right, null);
            LinearLayout layout_header_row_cell = (LinearLayout) layout_header_row_right.findViewById(R.id.layout_header_row_cell);
            if (layout_header_row_cell != null)
                layout_header_row_cell.removeAllViews();
            if (headerList.size() > 0) {
                ViewGroup.LayoutParams rowParams = null;
                if (headerList.size() == 1)
                    rowParams = new ViewGroup.LayoutParams((deviceWidth - MyUtils.dpToPx(headerLeftWidthInDp)), MyUtils.dpToPx(headerLeftHeightInDp));
                else if (headerList.size() == 2)
                    rowParams = new ViewGroup.LayoutParams((deviceWidth - MyUtils.dpToPx(headerLeftWidthInDp)) / 2, MyUtils.dpToPx(headerLeftHeightInDp));
                else
                    rowParams = new ViewGroup.LayoutParams(MyUtils.dpToPx(columnDefaultWidthInDp), MyUtils.dpToPx(headerLeftHeightInDp));

                for (int i = 0; i < headerList.size(); i++) {
                    View layout_header_cell_right = inflate(ctx, R.layout.layout_header_cell_right, null);
                    TextView tv_title = (TextView) layout_header_cell_right.findViewById(R.id.tv_title);
                    tv_title.setText("" + headerList.get(i).getTitle());
                    layout_header_row_cell.addView(layout_header_cell_right, rowParams);
                }
                layout_header.addView(layout_header_row_right);
            }
        }
    }

    private void addRightContentRows() {
        LinearLayout layout_content_right = (LinearLayout) rootView.findViewById(R.id.layout_content_right);
        if (layout_content_right != null)
            layout_content_right.removeAllViews();

        if (headerList.size() > 0) {
            RelativeLayout.LayoutParams rowParams = null;
            if (headerList.size() == 1)
                rowParams = new RelativeLayout.LayoutParams((deviceWidth - MyUtils.dpToPx(headerLeftWidthInDp)), ViewGroup.LayoutParams.MATCH_PARENT);
            else if (headerList.size() == 2)
                rowParams = new RelativeLayout.LayoutParams((deviceWidth - MyUtils.dpToPx(headerLeftWidthInDp)) / 2, ViewGroup.LayoutParams.MATCH_PARENT);
            else
                rowParams = new RelativeLayout.LayoutParams(MyUtils.dpToPx(columnDefaultWidthInDp), ViewGroup.LayoutParams.MATCH_PARENT);
            for (int i = 0; i < headerList.size(); i++) {
                RelativeLayout layout_content_row_right = (RelativeLayout) View.inflate(ctx, R.layout.layout_content_row_right, null);
                RelativeLayout layout_content_row_cell = (RelativeLayout) layout_content_row_right.findViewById(R.id.layout_content_row_cell);

                layout_content_row_right.setTag("" + headerList.get(i).getID());
                if (layout_content_row_cell != null)
                    layout_content_row_cell.removeAllViews();

                layout_content_row_right.setLayoutParams(rowParams);

                int maxDrawCell = ((endTime - startTime) + 1) * 4;
                LinearLayout lastCell = null;

                for (int j = 1; j <= maxDrawCell; j++) {
                    LogUtil.Print(TAG, "j=====" + j);
                    LinearLayout layout_cell = (LinearLayout) View.inflate(ctx, R.layout.layout_content_child_cell_right, null);
                    TextView tv_minute = (TextView) layout_cell.findViewById(R.id.tv_minute);
                    int currentTimeSlot = (currentHours * 4);
                    if (currentMinute < 15)
                        currentTimeSlot++;
                    else if (currentMinute >= 15 && currentMinute < 30)
                        currentTimeSlot = currentTimeSlot + 2;
                    else if (currentMinute >= 30 && currentMinute < 45)
                        currentTimeSlot = currentTimeSlot + 3;
                    else if (currentMinute >= 45 && currentMinute < 60)
                        currentTimeSlot = currentTimeSlot + 4;
                    if (j == currentTimeSlot)
                        tv_minute.setBackgroundColor(ContextCompat.getColor(ctx, R.color.current_time));
                    tv_minute.setId(j);
                    tv_minute.setTag(i);
                    tv_minute.setOnClickListener(this);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    if (lastCell != null) {
                        params.addRule(RelativeLayout.BELOW, lastCell.getId());
                        layout_content_row_cell.addView(layout_cell, params);
                    } else {
                        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                        layout_content_row_cell.addView(layout_cell, params);
                    }
                    lastCell = layout_cell;
                    lastCell.setId(j);
                    lastCell.setTag("" + (j));

                }
                layout_content_right.addView(layout_content_row_right);
            }
        }
    }

    public void addEventSlot() {
        LinearLayout layout_content_right = (LinearLayout) rootView.findViewById(R.id.layout_content_right);

        if (layout_content_right != null) {
            for (int i = 0; i < layout_content_right.getChildCount(); i++) {
                RelativeLayout layout_content_row_right = (RelativeLayout) layout_content_right.getChildAt(i);
                RelativeLayout layout_content_row_cell = (RelativeLayout) layout_content_row_right.findViewById(R.id.layout_content_row_cell);

                for (int j = 0; j < contentList.size(); j++) {
                    if (contentList.get(j).getHeaderID().equals(layout_content_row_right.getTag())) {
                        int hour = contentList.get(j).getEventHour();
                        int minute = contentList.get(j).getEventMinute();
                        int startPosition = (hour != 0) ? (hour * 4) : hour;

                        if ((hour == 0) && (minute == 0))
                            startPosition = 0;
                        else if (minute == 0)
                            startPosition = startPosition - 1;
                        else if (minute == 15)
                            startPosition = startPosition + 0;
                        else if (minute == 30)
                            startPosition = startPosition + 1;
                        else if (minute == 45)
                            startPosition = startPosition + 2;


                        int endPosition = (contentList.get(j).getDuration() / 15) + startPosition + 1;


                        RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

                        View layout_event_view;
                        if (contentList.get(j).getEventType().equals("1")) {
                            layout_event_view = View.inflate(ctx, R.layout.layout_event_view, null);
                        } else if (contentList.get(j).getEventType().equals("2")) {
                            layout_event_view = View.inflate(ctx, R.layout.layout_event_view_unavailable, null);
                        } else {
                            layout_event_view = View.inflate(ctx, R.layout.layout_event_view, null);
                        }
                        try {
                            if (contentList.get(j).getBgColorHexCode().equals(""))
                                layout_event_view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorPrimary));
                            else
                                layout_event_view.setBackgroundColor(Color.parseColor("" + contentList.get(j).getBgColorHexCode()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            layout_event_view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorPrimary));
                        }
                        TextView tv_event_title = (TextView) layout_event_view.findViewById(R.id.tv_event_title);
                        TextView tv_event_desc = (TextView) layout_event_view.findViewById(R.id.tv_event_desc);

                        if (tv_event_title != null)
                            tv_event_title.setText("" + contentList.get(j).getTitle());

                        tv_event_desc.setText("" + contentList.get(j).getDesc());
                        if (startPosition <= layout_content_row_cell.getChildCount() && endPosition <= layout_content_row_cell.getChildCount()) {

                            if ((hour == 0) && (minute == 0)) {
                                params5.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                                params5.addRule(RelativeLayout.ABOVE, layout_content_row_cell.getChildAt((endPosition - 1)).getId());
                            } else {
                                params5.addRule(RelativeLayout.BELOW, layout_content_row_cell.getChildAt(startPosition).getId());
                                params5.addRule(RelativeLayout.ABOVE, layout_content_row_cell.getChildAt(endPosition).getId());
                            }
                            //layout_event_view.setTag(contentList.get(j).getID());
                            layout_event_view.setTag(j);
                            layout_event_view.setOnClickListener(this);
                            layout_content_row_cell.addView(layout_event_view, params5);
                        }

                    }
                }

            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view instanceof TextView) {
            int clickPosition = (int) view.getId();
            int clickedHour = (clickPosition == 0) ? clickPosition : ((clickPosition - 1) / 4);
            double currentCell = (clickPosition / 4.0);
            String[] splitString = ("" + currentCell).split("\\.");
            int minute;
            try {
                minute = Integer.valueOf(splitString[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                minute = 0;
            }

            if (minute == 25)
                minute = 0;
            else if (minute == 5)
                minute = 15;
            else if (minute == 75)
                minute = 30;
            else if (minute == 0)
                minute = 45;

            if (mCellClickListener != null)
                mCellClickListener.onCellClicked(view, clickedHour, minute);
        } else {
            int clickPosition = (int) view.getTag();
            if (mEventClickListener != null) {
                if (contentList.size() > clickPosition)
                    mEventClickListener.onEventClicked(view, "" + contentList.get(clickPosition).getID());
            }
        }
    }

    public void setOnCellClickListener(onCellClickListener listener) {
        mCellClickListener = listener;
    }

    public void setOnEventClickListener(onEventClickListener listener) {
        mEventClickListener = listener;
    }
}