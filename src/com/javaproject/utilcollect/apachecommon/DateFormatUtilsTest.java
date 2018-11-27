package com.javaproject.utilcollect.apachecommon;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 格式化Date,Calendar为指定字符模式
 */
public class DateFormatUtilsTest {
    /**
     * 格式化指定Calendar为指定字符模式
     */
    public static void formatCalendar() {
        Calendar instance = Calendar.getInstance();
        String pattern = "yyyy-MM-dd";
        String formatStr = DateFormatUtils.format(instance, pattern);
        System.out.println(formatStr);
    }

    /**
     * 格式化指定Calendar为指定地区时域为指定字模式
     */
    public static void formatLocalCalendar() {
        Calendar instance = Calendar.getInstance();
        String pattern = "yyyy-MM-dd";
        String formatStr = DateFormatUtils.format(instance,pattern, TimeZone.getDefault(), Locale.ENGLISH);
        System.out.println(formatStr);
    }

    /**
     * 格式化时间为指定字符模式
     */
    public static void formatDate() {
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        String formatStr = DateFormatUtils.format(date,pattern);
        System.out.println(formatStr);
    }

    /**
     * 格式化指定地区时域时间为指定字符模式
     */
    public static void formatLocaleDate() {
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        String formatStr = DateFormatUtils.format(date,pattern, TimeZone.getDefault(), Locale.ENGLISH);
        System.out.println(formatStr);
    }

    /**
     * 将UTC时间格式化为特定格式
     */
    public static void formatUTC() {
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        String formatStr = DateFormatUtils.formatUTC(date, pattern);
        System.out.println(formatStr);
    }


    public static void main(String[] args){
//        formatCalendar();
//        formatLocalCalendar();
//        formatDate();
//        formatLocaleDate();
        formatUTC();

    }

}
