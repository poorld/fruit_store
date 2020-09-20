package me.teenyda.fruit_store.common.utils;

import java.util.Calendar;

/**
 * author: teenyda
 * date: 2020/9/20
 * description:
 */
public class CalendarsUtils {

    public static long nextHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTimeInMillis();
    }
}
