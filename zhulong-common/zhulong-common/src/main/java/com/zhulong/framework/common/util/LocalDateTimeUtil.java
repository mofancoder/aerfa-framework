package com.zhulong.framework.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 日期转化工具类
 * @author xxc
 * @time 2019-1-7 14:32
 */
public class LocalDateTimeUtil {

    private LocalDateTimeUtil(){}

    /**
     * 将时间戳转为 yyyy-MM-dd 格式 的字符串
     * @author xxc
     * @return java.lang.String
     * @param timestamp
     * @time 2019-1-7 14:41
     */
    public static String longToDateShortString(Long timestamp){
        if(timestamp==null){
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Instant instant = Instant.ofEpochMilli(timestamp);

        LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 将时间戳转为 yyyy-MM-dd HH:mm:ss 格式 的字符串
     * @author xxc
     * @return java.lang.String
     * @param timestamp
     * @time 2019-1-7 14:41
     */
    public static String longToDateString(Long timestamp){
        if(timestamp==null){
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Instant instant = Instant.ofEpochMilli(timestamp);

        LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 将时间戳转为自定义的字符串时间
     * @author xxc
     * @return java.lang.String
     * @param timestamp
     * @param format
     * @time 2019-1-7 14:41
     */
    public static String longToDateString(Long timestamp,String format){
        if(timestamp==null||StrUtils.isBlank(format)){
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);

        Instant instant = Instant.ofEpochMilli(timestamp);

        LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 将 yyyy-MM-dd HH:mm:ss 格式的字符串时间转换为时间戳
     * @author xxc
     * @return java.lang.Long
     * @param dateTimeStr
     * @time 2019-1-7 14:55
     */
    public static Long dateStringToLong(String dateTimeStr){
        if(StrUtils.isBlank(dateTimeStr)){
            return null;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);

        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将自定义的字符串时间转换为时间戳
     * @author xxc
     * @return java.lang.Long
     * @param dateTimeStr
     * @param format
     * @time 2019-1-7 14:55
     */
    public static Long dateStringToLong(String dateTimeStr,String format){
        if(StrUtils.isBlank(dateTimeStr) || StrUtils.isBlank(format)){
            return null;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);

        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    public static void main(String[] args) {

        System.out.println(longToDateShortString(System.currentTimeMillis()));
        System.out.println(longToDateString(System.currentTimeMillis()));
        System.out.println(longToDateString(System.currentTimeMillis(),"yyyy年MM月dd日 HH:mm:ss"));
        System.out.println(dateStringToLong("2019-01-07 15:01:39"));
        System.out.println(dateStringToLong("2019年01月07日 15:01:39","yyyy年MM月dd日 HH:mm:ss"));

    }
}
