package press.wein.home.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 日期工具类
 */
public class DateUtil {


    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_PATTERN_MINUTES = "yyyyMMddHHmm";
    public static final String DEFAULT_PATTERN_MS = "yyyyMMddHHmmssSSS";
    public static final String DEFAULT_PATTERN_DAY = "yyyy-MM-dd";
    private static final Pattern PARSE_DAYS = Pattern.compile("^([0-9]+)d$");
    private static final Pattern PARSE_HOURS = Pattern.compile("^([0-9]+)h$");
    private static final Pattern PARSE_MINUTES = Pattern.compile("^([0-9]+)mi?n$");
    private static final Pattern PARSE_SECONDS = Pattern.compile("^([0-9]+)s$");

    private DateUtil() {
    }

    public static String defaultFormatDay(Date date) {
        if (null == date)
            return null;
        return getDate(date, DEFAULT_PATTERN_DAY);
    }

    // 根据yyyyMMddHHmmssSSS将date类型转化为string
    public static String defaultFormatMSDate(Date date) {
        if (null == date)
            return null;
        return getDate(date, DEFAULT_PATTERN_MS);
    }

    // 根据yyyyMMddHHmm将date类型转化为string
    public static String defaultFormatMinutesDate(Date date) {
        if (null == date)
            return null;
        return getDate(date, DEFAULT_PATTERN_MINUTES);
    }

    // 根据yyyy-MM-dd HH:mm:ss 将date类型转化为string
    public static String defaultFormatDate(Date date) {
        if (null == date)
            return null;
        return getDate(date, DEFAULT_PATTERN);
    }

    // 根据yyyy-MM-dd HH:mm:ss 将date类型转化为string
    public static String defaultFormatDateDAY(Date date) {
        if (null == date)
            return null;
        return getDate(date, DEFAULT_PATTERN_DAY);
    }

    public static String getDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return formatStr
     * @throws ParseException
     */
    public static Date strToDate(String str, String formatStr) throws ParseException {
        if (formatStr == null) {
            formatStr = DEFAULT_PATTERN_DAY;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.parse(str);
    }

    /**
     * 字符串转换成日期时间
     *
     * @param str
     * @return date
     * @throws ParseException
     */
    public static Date strToDateTime(String str) throws ParseException {
        return strToDateTime(str, null);
    }

    /**
     * 字符串转换成日期时间
     *
     * @param str
     * @return formatStr
     * @throws ParseException
     */
    public static Date strToDateTime(String str, String formatStr) throws ParseException {
        if (formatStr == null) {
            formatStr = DEFAULT_PATTERN;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.parse(str);
    }

    /**
     * 设置给定日期的时分秒
     *
     * @param c      需要调整的日期
     * @param hour   设定的小时值
     * @param minute 设定的分钟值
     * @param second 设定的秒值
     */
    public static void setHMS(Calendar c, int hour, int minute, int second) {
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
    }

    /**
     * 设置给定日期的时分秒毫秒
     *
     * @param c           需要调整的日期
     * @param hour        设定的小时值
     * @param minute      设定的分钟值
     * @param second      设定的秒值
     * @param milliSecond 设定的毫秒秒值
     */
    public static void setHMSM(Calendar c, int hour, int minute, int second, int milliSecond) {
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        c.set(Calendar.MILLISECOND, milliSecond);
    }

    /**
     * <pre>
     * 取得N天前的日期
     * </pre>
     *
     * @param days
     * @return
     */
    public static Date getDateBefore(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -days);
        setHMSM(c, 0, 0, 0, 0);
        return c.getTime();
    }

    /**
     * <pre>
     * 取得N天后的日期
     * </pre>
     *
     * @param days
     * @return
     */
    public static Date getDateAfterDay(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        setHMSM(c, 0, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 获取日期时分秒为0
     *
     * @param date
     * @return
     */
    public static Date getDateWithoutHMS(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        setHMSM(c, 0, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 取得某天前 x天、y小时、z分钟、s的日期
     *
     * @param date
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date getDateBefore(Date date, int day, int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -day);
        c.add(Calendar.HOUR_OF_DAY, -hour);
        c.add(Calendar.MINUTE, -minute);
        c.add(Calendar.SECOND, -second);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * <pre>
     * 取得当天日期
     * </pre>
     *
     * @return
     */
    public static Date getToday() {
        Calendar c = Calendar.getInstance();
        setHMSM(c, 0, 0, 0, 0);
        return c.getTime();
    }

    /**
     * <pre>
     * 取得当天日期
     * </pre>
     *
     * @return
     */
    public static Date getDayOfWeekEnd() {
        Calendar c = Calendar.getInstance();
        setHMSM(c, 0, 0, 0, 0);
        int w = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (0 == w) {
            w = 7;
        }
        c.add(Calendar.DATE, 7 - w);
        return c.getTime();
    }


    /**
     * <pre>
     * 取得当天日期
     * </pre>
     *
     * @return
     */
    public static Date getTodayHHmmss() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    /**
     * <pre>
     * 取得明天日期
     * </pre>
     *
     * @return
     */
    public static Date getTomorrow() {
        return getDateBefore(-1);
    }

    /**
     * 时间转换
     *
     * @param d
     * @return
     */
    public static Date addHour(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.HOUR, 23);
        c.add(Calendar.MINUTE, 59);
        c.add(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static boolean isSameYearMonthDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 获得当月天数
     */
    public static int getDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getNowDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }

    /**
     * <pre>
     * 取得N月后的日期
     * </pre>
     *
     * @return
     */
    public static Date getDateAfter(int months) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, months);
        setHMSM(c, 0, 0, 0, 0);
        return c.getTime();
    }

    /**
     * <pre>
     * 取得当月1日
     * </pre>
     *
     * @return
     */
    public static Date getMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        setHMSM(c, 0, 0, 0, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * <pre>
     * 取得当月16日
     * </pre>
     *
     * @return
     */
    public static Date getMonthSixteenDay() {
        Calendar c = Calendar.getInstance();
        setHMSM(c, 0, 0, 0, 0);
        c.set(Calendar.DAY_OF_MONTH, 16);
        return c.getTime();
    }

    /**
     * <pre>
     * 取得下个月1日
     * </pre>
     *
     * @return
     */
    public static Date getNextMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        setHMSM(c, 0, 0, 0, 0);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * <pre>
     * 取得下个月16日
     * </pre>
     *
     * @return
     */
    public static Date getNextMonthSixteenDay() {
        Calendar c = Calendar.getInstance();
        setHMSM(c, 0, 0, 0, 0);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 16);
        return c.getTime();
    }

    /**
     * <pre>
     * 取得N月后的当天日期
     * </pre>
     *
     * @return
     */
    public static Date getNowDateAfter(int months, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        setHMSM(c, 0, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 截取日期yyyy-mm-dd
     *
     * @param date
     * @return
     */
    public static Date formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN_DAY);
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            return date;
        }
    }

    /**
     * 增加时间
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date add(Date date, Integer field, Integer amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }


    /**
     * <pre>
     * 根据传入的日期得到天
     * </pre>
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
    }

    public static String getWeek(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static long getNow12() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

    public static boolean isTomorrow(Date date) {
        Calendar today = Calendar.getInstance();
        Calendar old = Calendar.getInstance();
        old.setTime(date);

        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        old.set(Calendar.HOUR_OF_DAY, 0);
        old.set(Calendar.MINUTE, 0);
        old.set(Calendar.SECOND, 0);
        old.set(Calendar.MILLISECOND, 0);

        long intervalMilli = old.getTimeInMillis() - today.getTimeInMillis();
        int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        if (1 >= xcts) {
            return true;
        }
        return false;

    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        //subYear==0,说明是同一年
        if (subYear == 0 && cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
            return true;
        }
        return false;
    }


    public static Date setTimeToBeginningOfDay(Date date) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 判断时间的时分秒是否为0
     *
     * @param date
     * @return
     */
    public static boolean isTimeZero(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        if (hour == 0 && minute == 0 && second == 0) {
            return true;
        }
        return false;
    }

    public static long getDayBetweenDate(Date startTime, Date endTime) {
        return (getDateWithoutHMS(endTime).getTime() / 86400000 - getDateWithoutHMS(startTime).getTime() / 86400000);
    }

    /**
     * 判断两周之后的时间
     *
     * @return
     */
    public static Date getDayOf2WeekEnd() {
        Calendar c = Calendar.getInstance();
        setHMSM(c, 0, 0, 0, 0);
        int w = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (0 == w) {
            w = 7;
        }
        c.add(Calendar.DATE, 14 - w);
        return c.getTime();
    }

    /**
     * 获取当前时间的小时
     *
     * @return
     */
    public static String getNowHour() {
        String now = defaultFormatDate(new Date());
        return now.substring(11, 13);
    }


    /**
     * 获取当前时间的分钟
     *
     * @return
     */
    public static String getNowMinute() {
        String now = defaultFormatDate(new Date());
        return now.substring(14, 15);
    }

    /**
     * 获得当天的最后一秒，即23:59:59
     *
     * @param date
     * @return
     */
    public static Date getLastSecondOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        setHMS(c, 23, 59, 59);
        return c.getTime();
    }

    public static Date getNextMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1 + 7);
        setHMSM(c, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getNextSunday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7 + 7);
        setHMSM(c, 0, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 获取当前日期属于周几:如"周二"、"下周一"
     *
     * @param date
     * @return
     */
    public static String getDateLabel(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        String weekDay = weekDays[w];
        Date nextMonday = getNextMonday(new Date());
        Date nextNextMonday = getNextMonday(nextMonday);
        String dateFlag = "";
        if (nextMonday.compareTo(date) <= 0 && nextNextMonday.compareTo(date) > 0) {
            dateFlag = "下";
        }

        return dateFlag + weekDay;
    }

    public static boolean isTomorrowOrBefore(Date date) {
        Calendar today = Calendar.getInstance();
        Calendar old = Calendar.getInstance();
        old.setTime(date);

        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        old.set(Calendar.HOUR_OF_DAY, 0);
        old.set(Calendar.MINUTE, 0);
        old.set(Calendar.SECOND, 0);
        old.set(Calendar.MILLISECOND, 0);

        long intervalMilli = old.getTimeInMillis() - today.getTimeInMillis();
        int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        if (1 >= xcts) {
            return true;
        }
        return false;

    }

}
