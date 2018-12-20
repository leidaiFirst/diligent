package com.dl.diligent.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author 余焕【yuh@3vjia.com】
 * @since 2018/5/21 11:32
 **/
public class DateHelper {

    public final static String DEFAULT_PATTERN = DatePattern.DATETIME_PATTERN_LINE.pattern;

    /**
     * 解析日期，默认日期格式 （yyyy-MM-dd HH:mm:ss）
     * @param dateStr 日期字符串
     * @return 格式化日期
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一：</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void parse(){
     *          String date = "2018-07-27 09:20:12";
     *          Date result = DateHelper.parse(date);//result:Fri Jul 27 09:20:12 CST 2018
     *      }
     *  }</pre></blockquote>
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, DEFAULT_PATTERN);
    }

    /**
     * 解析日期
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return Data 格式化的日期
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一：</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void parse(){
     *          String date = date = "2018-07-27 09:20:12";
     *          Date result = DateHelper.parse(date,"yyyy-MM-dd");//result:Fri Jul 27 00:00:00 CST 2018
     *
     *          date = "2018/07/27";
     *          result = DateHelper.parse(date,"yyyy/MM/dd");//Fri Jul 27 00:00:00 CST 2018
     *
     *          date = "2018/07/27 09:20:12";
     *          result = DateHelper.parse(date,"yyyy/MM/dd");//Fri Jul 27 00:00:00 CST 2018
     *
     *          date = "2018/07/27 09:20:12";
     *          result = DateHelper.parse(date,"yyyy/MM/dd HH:mm:ss");//Fri Jul 27 09:20:12 CST 2018
     *
     *          date = "2018-07-27 09:20:12";
     *          result = DateHelper.parse(date,"yyyy-MM-dd HH:mm:ss");//Fri Jul 27 09:20:12 CST 2018
     *
     *      }
     *  }</pre></blockquote>
     */
    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        try {
            return sd.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 解析日期
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     */
    public static Date parse(String dateStr, DatePattern pattern) {
        SimpleDateFormat sd = new SimpleDateFormat(pattern.pattern);
        try {
            return sd.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 生成日期
     * @param year +：未来 -：以前
     * @return 生成日期
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一：</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void getDate(){
     *          Date result = DateHelper.getDate(18); //2018+18=2036  result:Sun Jul 27 09:52:32 CST 2036
     *
     *          result = DateHelper.getDate(2018);//2018+2018=4036  result:Sun Jul 27 09:53:04 CST 4036
     *
     *          result = DateHelper.getDate(-18);//2018-18=2000  result:SThu Jul 27 10:02:56 CST 2000
     *
     *          result = DateHelper.getDate(0);//result:Fri Jul 27 09:57:34 CST 2018  当前时间
     *      }
     *  }</pre></blockquote>
     */
    public static Date getDate(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     *
     * 生成日期
     * @param field  field 操作的对象Calendar.DATE，Calendar.YEAR，Calendar.MONTH......
     * @param amount 以当前时间为基准进行日期的加减（+：未来 -：以前）
     * @return Data 计算后的日期
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一：生成时间是以当前时间为基准的</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void getDate(){
     *          Date result = DateHelper.getDate(Calendar.DATE,1); //日期+1  result：Sat Jul 28 10:46:32 CST 2018
     *
     *          result = DateHelper.getDate(Calendar.YEAR,1);//年份+1  result：Sat Jul 27 10:46:32 CST 2019
     *
     *          result = DateHelper.getDate(Calendar.MONTH,1);//月份+1  result：Mon Aug 27 10:46:32 CST 2018

     *          result = DateHelper.getDate(Calendar.HOUR,1);//小时+1  result：Fri Jul 27 11:41:47 CST 2018
     *
     *          result = DateHelper.getDate(null,0);//当前时间  result：Fri Jul 27 10:46:32 CST 2018
     *
     *          result = DateHelper.getDate(Calendar.DATE,-1); //日期-1  result：Thu Jul 26 10:46:32 CST 2018
     *
     *          result = DateHelper.getDate(Calendar.YEAR,-1);//年份-1  result：Thu Jul 27 10:46:32 CST 2017
     *
     *          result = DateHelper.getDate(Calendar.MONTH,-1);//月份-1  result：Wed Jun 27 10:46:32 CST 2018

     *          result = DateHelper.getDate(Calendar.HOUR,-1);//小时-1  result：Fri Jul 27 09:46:32 CST 2018
     *    }}</pre></blockquote>
     */
    public static Date getDate(Integer field, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (field == null) {
            field = Calendar.DATE;
        }
        calendar.add(field, amount);
        return calendar.getTime();
    }


    /**
     * 生成日期
     * @param date 基准日期
     * @param field 操作对象Calendar.DATE，Calendar.YEAR，Calendar.MONTH......
     * @param amount 以date为基准进行日期的加减（+：未来 -：以前）
     * @return 生成日期
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一：生成时间是以当前时间为基准的</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void getDate(){
     *          Date base = new Date();//base:Fri Jul 27 10:46:32 CST 2018
     *
     *          Date result = DateHelper.getDate(base,Calendar.DATE,1); //日期+1  result:Sat Jul 28 10:46:32 CST 2018
     *
     *          result = DateHelper.getDate(base,Calendar.YEAR,1);//年份+1  result:Sat Jul 27 10:46:32 CST 2019
     *
     *          result = DateHelper.getDate(base,Calendar.MONTH,1);//月份+1  result:Mon Aug 27 10:46:32 CST 2018
     *
     *          result = DateHelper.getDate(base,Calendar.HOUR,1);//小时+1  result:Fri Jul 27 11:41:47 CST 2018
     *
     *          result = DateHelper.getDate(base,Calendar.DATE,-1); //日期-1  result:Thu Jul 26 10:46:32 CST 2018
     *
     *          result = DateHelper.getDate(base,Calendar.YEAR,-1);//年份-1  result:Thu Jul 27 10:46:32 CST 2017
     *
     *          result = DateHelper.getDate(base,Calendar.MONTH,-1);//月份-1  result:Wed Jun 27 10:46:32 CST 2018
     *
     *          result = DateHelper.getDate(base,Calendar.HOUR,-1);//小时-1  result:Fri Jul 27 09:46:32 CST 2018
     *      }
     * }</pre></blockquote>
     */
    public static Date getDate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 生成开始日期
     * @param date 日期
     * @return beginDate yyyy-MM-dd 00:00:00
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一:</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void getBeginDate(){
     *
     *          Date base = new Date();//Fri Jul 27 10:46:32 CST 2018
     *          Date result = DateHelper.getBeginDate(base);  result:Fri Jul 27 00:00:00 CST 2018
     *          result = DateHelper.getBeginDate(null);//此处异常,不可传null值
     *
     *      }
     * }</pre></blockquote>
     */
    public static Date getBeginDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 生成结束日期
     * @param date 日期
     * @return endDate yyyy-MM-dd 23:59:59
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一:</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void getEndDate(){
     *          Date base = new Date();//Fri Jul 27 10:46:32 CST 2018
     *          Date result = DateHelper.getEndDate(base);//result:Fri Jul 27 23:59:59 CST 2018
     *          result = DateHelper.getBeginDate(null);//不可传null值
     *      }
     * }</pre></blockquote>
     */
    public static Date getEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    /**
     *
     * 格式化日期
     * 默认格式yyyy-MM-dd HH:mm:ss
     * @param date 格式化对象
     * @return 字符串输出
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一:</strong></p>
     * <blockquote>
     * <pre>{@code
     *     public void format(){
     *          Date base = new Date();//Fri Jul 27 14:14:56 CST 2018
     *          String result = DateHelper.format(base);//result:2018-07-27 14:14:56
     *     }
     * }</pre></blockquote>
     */
    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    /**
     * @param date 待格式化日期
     * @param pattern 格式化的格式
     * @return  字符串输出
     *
     * <p><strong>示例一:</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void format(){
     *          Date base = new Date();//Sat Jul 28 10:12:35 CST 2018
     *
     *          String result = DateHelper.format(base,DateHelper.DatePattern.DATE_PATTERN_LINE); //result:2018-07-28
     *
     *          result = DateHelper.format(base,DateHelper.DatePattern.DATETIME_PATTERN_NONE); //result:20180728 10:12:35
     *
     *          result = DateHelper.format(base,DateHelper.DatePattern.DATE_PATTERN_NONE); //result:20180728
     *
     *          result = DateHelper.format(base,DateHelper.DatePattern.DATETIME_PATTERN_SLASH);//result:2018/07/28 10:12:35
     *
     *          result = DateHelper.format(base,DateHelper.DatePattern.DATETIME_PATTERN_WITH_MILSEC_NONE);//result:20180728 10:12:35.289
     *      }
     * }</pre></blockquote>
     *
     */
    public static String format(Date date, DatePattern pattern) {
        return format(date, pattern.pattern);
    }

    /**
     *
     * 格式化日志，字符串输出
     * @param date  待格式化日期
     * @param pattern  格式化的格式
     * @return 字符串输出
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     * <p><strong>示例一:</strong></p>
     * <blockquote>
     * <pre>{@code
     *      public void format(){
     *          Date base = new Date();//Sat Jul 28 10:11:33 CST 2018
     *
     *          String result = DateHelper.format(base,"yyyy-MM-dd");//result:2018-07-28
     *
     *          result = DateHelper.format(base,"yyyyMMdd HH:mm:ss");//result:20180728 10:11:33
     *
     *          result = DateHelper.format(base,"yyyy/MM/dd");//result:2018/07/28
     *
     *          result = DateHelper.format(base,"yyyyMMdd HH:mm:ss");//result:20180728 10:11:33
     *
     *          result = DateHelper.format(base,"yyyy/MM/dd HH:mm:ss.SSS");//result:2018/07/28 10:11:33.977
     *      }
     * }</pre></blockquote>
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        return sd.format(date);
    }

    /**
     * 时间格式枚举
     * @author 余焕【yuh@3vjia.com】
     * @since 2018/5/21 11:32
     */
    public enum DatePattern {
        DATE_PATTERN_LINE("yyyy-MM-dd"),
        DATE_SHORT_PATTERN_LINE("yyyy-M-d"),
        DATE_PATTERN_SLASH("yyyy/MM/dd"),
        DATE_SHORT_PATTERN_SLASH("yyyy/M/d"),
        DATE_PATTERN_NONE("yyyyMMdd"),
        DATETIME_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),
        DATETIME_SHORT_PATTERN_LINE("yyyy-M-d HH:mm:ss"),
        DATETIME_SHORT_PATTERN_LINE_T("yyyy-M-d'T'HH:mm:ss"),
        DATETIME_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
        DATETIME_SHORT_PATTERN_SLASH("yyyy/M/d HH:mm:ss"),
        DATETIME_PATTERN_NONE("yyyyMMdd HH:mm:ss"),
        DATETIME_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),
        DATETIME_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),
        DATETIME_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS"),
        DATETIME_PATTERN_WITH_ZONE("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        private String pattern;

        DatePattern(String pattern) {
            this.pattern = pattern;
        }
    }

}
