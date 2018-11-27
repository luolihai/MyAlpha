package com.javaproject.utilcollect.apachecommon;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 主要是修改时间，比较时间
 */
public class DataUtilsTest {

    //测试用的打印时间
    public static void printDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sf.format(date));
    }

    /**
     * 解析字符为时间
     * @return
     * @throws ParseException
     */
    public static Date parseDate() throws ParseException {
        Date date = DateUtils.parseDate("2018-11-11 23:23:23", "yyyy-MM-dd HH:mm:ss");
        return date;
    }

    /**
     * 解析字符为指定地区时间，感觉没什么两样
     * @return
     * @throws ParseException
     */
    public static Date parseForLocalDate() throws ParseException {
        Date date = DateUtils.parseDate("2018-11-11 23:23:23", Locale.ENGLISH,"yyyy-MM-dd HH:mm:ss");
        return date;
    }

    /**
     * 判断时间是否相等
     * @param date1
     * @param date2
     * @throws ParseException
     */
    public static void isSameTime(Date date1,Date date2) throws ParseException {
        boolean sameDay = DateUtils.isSameDay(date1,date2);
        System.out.println(sameDay);
    }

    /**
     * 以当前时间返回Calendar
     * @param date
     * @return
     * @throws ParseException
     */
    public static Calendar toCalendar(Date date) throws ParseException {
        Calendar calendar = DateUtils.toCalendar(date);
        System.out.println(calendar.getTime());
        return calendar;
    }

    /**
     * 给指定时间重设年份
     * @return
     * @throws ParseException
     */
    public static Date setYearAndReturn() throws ParseException {
        Date date = DateUtils.setYears(parseDate(), 2);
        return date;
    }

    public static Date addYearAndReturn() throws ParseException {
        Date date = DateUtils.addYears(parseDate(), 2);
        return date;
    }

    /**
     * 获取指定时间的指定位置值
     * @throws ParseException
     */
    public static void getFragmentInMDays()  throws ParseException {
        long fragmentInDays = DateUtils.getFragmentInDays(parseDate(), Calendar.MONDAY);
        System.out.println(fragmentInDays);
    }


    public static void main(String[] args) throws ParseException {
//        printDate(parseDate());
//        printDate(parseForLocalDate());
//        isSameTime(parseDate(),new Date());
//        toCalendar(parseForLocalDate());
//        printDate(setYearAndReturn());
//        printDate(addYearAndReturn());
        getFragmentInMDays();


    }
}

