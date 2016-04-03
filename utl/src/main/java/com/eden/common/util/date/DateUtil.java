package com.eden.common.util.date;

import com.eden.common.util.common.CommonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2015/10/16.
 */
public class DateUtil {
    /**
     * @param date format should be yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", getCurrentLocale())
                .parse(date);
    }

    /**
     * @param date format should be yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd", getCurrentLocale()).parse(date);
    }

    /**
     * @param time12Hours 09:14 PM
     * @return Date
     * @throws ParseException
     */
    public static Date parseTime12Hours(String time12Hours) throws ParseException {
        return new SimpleDateFormat("hh:mm a", getCurrentLocale())
                .parse(time12Hours);
    }

    public static Date parseISO1806GMTDate(String iso1806String) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", getCurrentLocale()).parse(iso1806String);
    }

    // yyyy-MM-dd'T'HH:mm:ssZ

    public static String formatISO1806GMTDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", getCurrentLocale()).format(date);
    }

    /**
     * @param date
     * @return formated date YYYY-MM-DD
     */
    public static String formatDate(Date date) {
        return String.format(getCurrentLocale(), "%1$tY-%1$tm-%1$td", date);
    }

    /**
     * YYYY-MM-DD HH:MM
     */
    public static String formatDateTimeHHMM(Date date) {
        return String.format(getCurrentLocale(), "%1$tY-%1$tm-%1$td    %1$TR", date);
    }

    /**
     * YYYY-MM-DD
     */
    public static String formatDateTimeYYMMDD(Date date) {
        return String.format(getCurrentLocale(), "%1$tY-%1$tm-%1$td", date);
    }

    /**
     * YYYY-MM-DD HH:MM:SS
     */
    public static String formatDateTime(Date date) {
        return String.format(getCurrentLocale(), "%1$tY-%1$tm-%1$td %1$TT", date);
    }

    /**
     * HH:MM AM/PM
     */
    public static String formatTime(Date time) {
        return String.format(getCurrentLocale(), "%1$Tr", time);
    }

    /**
     * MM-DD HH:MM
     */
    public static String formatDateTime24(Date time) {
        return String.format(getCurrentLocale(), "%1$tm-%1$td %1$TR", time);
    }

    /**
     * HH:MM
     */
    public static String formatTime24(Date time) {
        return String.format(getCurrentLocale(), "%1$TR", time);
    }

    /**
     * Saturday Sunday Monday etc
     */
    public static String formatDayOfWeek(Date day) {
        return String.format(getCurrentLocale(), "%1$TA", day);
    }

    private static Locale getCurrentLocale() {
        return CommonUtil.isZn() ? Locale.CHINA : Locale.ENGLISH;
    }


}
