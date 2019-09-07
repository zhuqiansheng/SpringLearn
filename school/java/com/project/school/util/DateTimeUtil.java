package com.project.school.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.Date;

/**时间处理的工具类X
 * @author YWD
 */
public class DateTimeUtil {

    //joda-time

    //str->Date
    //Date->str
    /**
     * 设置标准格式
     */
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";



    public static Date strToDate(String dateTimeStr,String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date,String formatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date strToDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    public static String getDatePoor(Date endDate, Date nowDate) {
        //每天毫秒数
        long nd = 1000 * 24 * 60 * 60;
        //每小时毫秒数
        long nh = 1000 * 60 * 60;
        //每分钟毫秒数
        long nm = 1000 * 60;
        //每秒毫秒数
        long ns=1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        long second=diff % nd % nh % nm/ns;
        StringBuilder time=new StringBuilder();
        if(day!=0){
            time.append(day).append("天");
        }
        if(hour!=0){
            time.append(hour).append("小时");
        }
        if(min!=0){
            time.append(min).append("分钟");
        }
        if(second!=0){
            time.append(second).append("秒");
        }
        return time.toString();

    }

    public static void main(String[] args) {
        System.out.println(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));

    }


}
