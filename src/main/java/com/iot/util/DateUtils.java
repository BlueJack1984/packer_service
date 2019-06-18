package com.iot.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类
 *
 */
public class DateUtils {

    public static final String YMD = "yyyyMMdd";
    public static final String YMD_SLASH = "yyyy/MM/dd";
    public static final String YMD_DASH = "yyyy-MM-dd";
    public static final String YM_DASH = "yyyy-MM";
    public static final String YMD_DASH_WITH_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD_WITH_TIME_SECOND = "yyyyMMddHHmmss";

    public static final long DAY = 24 * 60 * 60 * 1000L;
    public static final long HOUR = 60 * 60 * 1000L;

    private static final Map<String, DateFormat> DFS = new HashMap<String, DateFormat>();

    // private static final Log log = LogFactory.getLog(DateUtils.class);

    private DateUtils() {
    }

    private static DateFormat getFormat(String pattern) {
        DateFormat format = DFS.get(pattern);
        if (format == null) {
            format = new SimpleDateFormat(pattern);
            DFS.put(pattern, format);
        }
        return format;
    }

    /**
     * 字符串时间格式转化为日期
     *
     * @param source  字符串时间
     * @param pattern 时间格式
     * @return
     */
    public static Date parse(String source, String pattern) {
        if (source == null) {
            return null;
        }
        Date date;
        try {
            date = getFormat(pattern).parse(source);
        } catch (ParseException e) {
            /*
             * if (log.isDebugEnabled()) { log.debug(source + " doesn't match "
             * + pattern); }
             */
            return null;
        }
        return date;
    }

    /**
     * 日期转化为字符串
     *
     * @param date    日期
     * @param pattern 格式化类型
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return getFormat(pattern).format(date);
    }

    /**
     * @param year  年
     * @param month 月(1-12)
     * @param day   日(1-31)
     * @return 输入的年、月、日是否是有效日期
     */
    public static boolean isValid(int year, int month, int day) {
        if (month > 0 && month < 13 && day > 0 && day < 32) {
            // month of calendar is 0-based (月份从0开始)
            int mon = month - 1;
            Calendar calendar = new GregorianCalendar(year, mon, day);
            if (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) == mon && calendar.get(Calendar.DAY_OF_MONTH) == day) {
                return true;
            }
        }
        return false;
    }

    /**
     * conver date to calendar
     *
     * @param date
     * @return
     */
    private static Calendar convert(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 返回指定年份位移以后的日期(注意日期字符要和日期格式一致)
     *
     * @param dateStr    日期字符 如: 1989-11-21
     * @param Dateformat 日期格式 yyyy-MM-dd
     * @param offsetYear 为正是往后年数, 为负是往前年数
     * @return
     */
    public static Date yearOffset(String dateStr, String Dateformat, int offsetYear) {
        return offsetDate(DateUtils.parse(dateStr, Dateformat), Calendar.YEAR, offsetYear);
    }

    /**
     * 返回指定月数位移后的日期(注意日期字符要和日期格式一致)
     *
     * @param dateStr     日期字符 如: 1989-11-21
     * @param Dateformat  日期格式 yyyy-MM-dd
     * @param offsetMonth 为正是往后月份数, 为负是往前月份数
     * @return
     */
    public static Date monthOffset(String dateStr, String Dateformat, int offsetMonth) {
        return offsetDate(DateUtils.parse(dateStr, Dateformat), Calendar.MONTH, offsetMonth);
    }

    /**
     * 返回指定天数位移后的日期(注意日期字符要和日期格式一致)
     *
     * @param dateStr    日期字符 如: 1989-11-21
     * @param Dateformat 日期格式 yyyy-MM-dd
     * @param offsetDay  为正是往后天数, 为负是往前天数
     * @return
     */
    public static Date dayOffset(String dateStr, String Dateformat, int offsetDay) {
        return offsetDate(DateUtils.parse(dateStr, Dateformat), Calendar.DATE, offsetDay);
    }

    /**
     * 返回指定分钟位移后的日期(注意日期字符要和日期格式一致)
     *
     * @param dateStr    日期字符 如: 1989-11-21
     * @param Dateformat 日期格式 yyyy-MM-dd
     * @param
     * @return
     */
    public static Date minuteOffset(String dateStr, String Dateformat, int offsetMinute) {
        return offsetDate(DateUtils.parse(dateStr, Dateformat), Calendar.MINUTE, offsetMinute);
    }

    /**
     * 返回指定日期相应位移后的日期
     *
     * @param date   参考日期
     * @param field  位移单位，见 {@link Calendar}
     * @param offset 位移数量，正数表示之后的时间，负数表示之前的时间
     * @return 位移后的日期
     */
    public static Date offsetDate(Date date, int field, int offset) {
        Calendar calendar = convert(date);
        calendar.add(field, offset);
        return calendar.getTime();
    }

    /**
     * 返回当月第一天的日期
     */
    public static Date firstDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 返回当月最后一天的日期
     */
    public static Date lastDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 返回两个日期间的差异天数
     *
     * @param date1 参照日期
     * @param date2 比较日期
     * @return 参照日期与比较日期之间的天数差异，正数表示参照日期在比较日期之后，0表示两个日期同天，负数表示参照日期在比较日期之前
     */
    public static int dayDiff(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        return (int) (diff / DAY);
    }

    /**
     * 日期比较
     *
     * @param s
     * @param e
     * @return s>e 返回true 否则返回false
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() > fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前系统时间的字符串格式，以yyyy-MM-dd HH:mm:ss的格式表示
     *
     * @return
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        return format(currentTime, "yyyy-MM-dd HH:mm:ss");
    }

    /***
     * 检查参数时间（yyyy-MM-dd HH:mm:ss的格式表示）是否晚于当前时间
     * @param comparedTime
     * @return
     * @throws Exception
     */
    public static boolean checkAfterCurrentTime(String comparedTime) throws Exception {
        if (comparedTime == null || "".equals(comparedTime)) {
            return false;
        }
        String currentTime = getStringToday();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date a = sdf.parse(currentTime);
        Date b = sdf.parse(comparedTime);
        return b.after(a);
    }

    /***
     * 检查参数时间（yyyy-MM-dd HH:mm:ss的格式表示）是否早于当前时间
     *
     * @param comparedTime
     * @return
     * @throws Exception
     */
    public static boolean checkBeforeCurrentTime(String comparedTime) throws Exception {
        if (comparedTime == null || "".equals(comparedTime)) {
            return false;
        }
        String currentTime = getStringToday();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date a = sdf.parse(currentTime);
        Date b = sdf.parse(comparedTime);
        return b.before(a);
    }

    /**
     * 返回日期相差的月数
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(Date date1, Date date2) throws ParseException {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        if (c1.getTimeInMillis() < c2.getTimeInMillis())
            return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2)
            yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2)
            monthInterval--;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    public static String getUTCTimeStr(DateFormat format) {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return format.format(cal.getTime());
    }

    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        System.err.println(DateUtils.getUTCTimeStr(format));
    }
}

