package com.jackie.lee.common;


import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * created by lxm 20180323
 */
public class DateUtils {
    public static final FastDateFormat DEFAULT_FORMART = FastDateFormat.getInstance("yyyy-MM-dd");

    public static String formatDate(Date date, String pattern) {
        if (date == null) throw new IllegalArgumentException("date is null");
        if (pattern == null) throw new IllegalArgumentException("pattern is null");

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
//        formatter.setTimeZone();
        return formatter.format(date);
    }

    /**
     * 计算两个时间的天数差值，
     *
     * @param time        中国时区的long时间值，毫秒
     * @param anotherTime 中国时区的long时间值，毫秒
     */
    public static long calDaysDifference(long time, long anotherTime) {
        long t = time + 28800000;
        long anotherT = anotherTime + 28800000;
        long day = t / (1000 * 3600 * 24);
        long anotherDay = anotherT / (1000 * 3600 * 24);
        return (day - anotherDay);
    }

    /**
     * 将Calendar中的星期数值转成中文
     *
     * @param week 来源于Calendar.DAY_OF_WEEK
     */
    public static String getWeekStr(int week) {
        String weekStr = "";
        switch (week) {
            case Calendar.SUNDAY:
                weekStr = "日";
                break;
            case Calendar.MONDAY:
                weekStr = "一";
                break;
            case Calendar.WEDNESDAY:
                weekStr = "二";
                break;
            case Calendar.TUESDAY:
                weekStr = "三";
                break;
            case Calendar.THURSDAY:
                weekStr = "四";
                break;
            case Calendar.FRIDAY:
                weekStr = "五";
                break;
            case Calendar.SATURDAY:
                weekStr = "六";
                break;
            default:
                break;
        }
        return weekStr;
    }

    public static Date parse(String date, DateFormat df) {
        try {
            return df.parse(date);
        } catch (ParseException var3) {
            throw new RuntimeException("parse date [" + date + "] failed in use [" + df.toString() + "]", var3);
        }
    }

    public static String format(Date date, DateFormat df) {
        return date == null ? "" : (df != null ? df.format(date).toLowerCase() : DEFAULT_FORMART.format(date));
    }
}
