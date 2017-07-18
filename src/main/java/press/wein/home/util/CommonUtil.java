package press.wein.home.util;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


public class CommonUtil {

    //手机号正则表达式
    private static final Pattern PHONE_NO_PATTERN = Pattern.compile("^1[1-9]\\d{9}$");

    //邮件正则表达式
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");
    //字幕正则表达式
    public static final Pattern LETTER_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 随机获取字符串
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        if (length <= 0) {
            return "";
        }
        char[] randomChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b',
                'n', 'm'};
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar[random.nextInt(Integer.MAX_VALUE) % randomChar.length]);
        }
        return stringBuffer.toString();
    }

    /**
     * 获得六位随机数
     * @return
     */
    public static String getSixRandom() {
        Random random = new Random();
        return String.valueOf(random.nextInt(899999) + 100000);
    }


    /**
     * @param args
     * @return
     * @title: isExistNull
     * @description: 判断是否存在null
     */
    public static boolean isExistNull(Object... args) {
        for (Object arg : args) {
            if (arg == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param args
     * @return
     * @title: isExistEmpty
     * @description: 判断是否存在空，String类型包括""
     */
    public static boolean isExistEmpty(Object... args) {
        for (Object arg : args) {
            if (arg == null || (arg.getClass().equals(String.class) && StringUtils.isEmpty((String) arg))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param args
     * @return
     * @title: isExistBlank
     * @description: 判断是否存在空，String类型包括""、" "
     */
    public static boolean isExistBlank(Object... args) {
        for (Object arg : args) {
            if (arg == null || (arg.getClass().equals(String.class) && StringUtils.isBlank((String) arg))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param args
     * @return
     * @title: isAllNull
     * @description: 判断参数是否全部为null
     */
    public static boolean isAllNull(Object... args) {
        boolean result = true;
        for (Object arg : args) {
            result = result && (null == arg);
        }
        return result;
    }

    /**
     * @param args
     * @return
     * @title: isAllEmpty
     * @description: 判断是否全部为空，String类型包括""
     */
    public static boolean isAllEmpty(Object... args) {
        boolean result = true;
        for (Object arg : args) {
            result = result && (null == arg || (arg.getClass().equals(String.class) && StringUtils.isEmpty(
                    (String) arg)));
        }
        return result;
    }

    /**
     * @param args
     * @return
     * @title: isAllBlank
     * @description: 判断是否全部为空，String类型包括""、" "
     */
    public static boolean isAllBlank(Object... args) {
        boolean result = true;
        for (Object arg : args) {
            result = result && (null == arg || (arg.getClass().equals(String.class) && StringUtils.isBlank(
                    (String) arg)));
        }
        return result;
    }

    /**
     * @param <K> 泛型 key
     * @param <V> 泛型 value
     * @param map
     * @return
     * @title: mapToList
     * @description: Map 转 List
     */
    public static <K, V> List<V> mapToList(Map<K, V> map) {
        List<V> list = new ArrayList<V>();
        Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<K, V> entry = iter.next();
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * @param msgs
     * @return
     * @title: genMsg
     * @description: 生成提示信息
     */
    public static String genMsg(String... msgs) {
        if (msgs == null || msgs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" parameters = [");
        for (String msg : msgs) {
            sb.append(msg).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()).append("]");
        return sb.toString();
    }

    /**
     * @param e
     * @return
     * @title: getThrowableStack
     * @description: 获取异常堆栈信息
     */
    public static String getThrowableStack(Throwable e) {
        StringBuilder sb = new StringBuilder(e.toString()).append("\n");
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (int index = 0; index < stackTraceElements.length; index++) {
            sb.append("at [" + stackTraceElements[index].getClassName() + ",");
            sb.append(stackTraceElements[index].getFileName() + ",");
            sb.append(stackTraceElements[index].getMethodName() + ",");
            sb.append(stackTraceElements[index].getLineNumber() + "]\n");
        }
        return sb.toString();
    }

    /**
     * @param e
     * @param length
     * @return
     * @title: getThrowableStack
     * @description: 根据length获取异常堆栈信息
     */
    public static String getThrowableStack(Throwable e, int length) {
        String errMsg = getThrowableStack(e);
        return ", detailMessage = [\n" + errMsg.substring(0, errMsg.length() > length ? length : errMsg.length()) + "...]";
    }


    /**
     * 生成日期+时分秒
     *
     * @param someDate
     * @param format
     * @return
     */
    public static String getFormatDateTimeString(Date someDate, String format) {
        if (someDate == null) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = "yyyyMMddHHmmss";
        }
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        return simpledateformat.format(someDate).toString();
    }

    /**
     * 去掉时分秒
     *
     * @param date
     * @return
     */
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
     * 计算两个日期之间相差的天数
     *
     * @param from
     * @param to
     * @return
     */
    public static int daysBetween(Date from, Date to) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        long fromTime = cal.getTimeInMillis();

        cal.setTime(to);
        long toTime = cal.getTimeInMillis();
        long days = (toTime - fromTime) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(days));
    }

    /**
     * 计算两个日期之间相差的天数 除去周六与周日
     *
     * @param from
     * @param to
     * @return
     */
    public static int daysBetweenExceptWeekend(Date from, Date to) {
        int days = (int) ((to.getTime() - from.getTime()) / (24 * 60 * 60 * 1000)) + 1;
        int weeks = days / 7;

        int rs;
        if (days % 7 == 0) {
            rs = days - 2 * weeks;
        } else {
            Calendar fromCalendar = Calendar.getInstance();
            Calendar toCalendar = Calendar.getInstance();
            fromCalendar.setTime(from);
            toCalendar.setTime(to);
            int beg = fromCalendar.get(Calendar.DAY_OF_WEEK);
            int end = toCalendar.get(Calendar.DAY_OF_WEEK);
            if (beg > end) {
                rs = days - 2 * (weeks + 1);
            } else if (beg < end) {
                if (end == 7) {
                    rs = days - 2 * weeks - 1;
                } else {
                    rs = days - 2 * weeks;
                }
            } else {
                if (beg == 1 || beg == 7) {
                    rs = days - 2 * weeks - 1;
                } else {
                    rs = days - 2 * weeks;
                }
            }
        }
        return rs;
    }


    /**
     * @param from1
     * @param to1
     * @param from2
     * @param to2
     * @return
     * @description 判断两个日期段，是否有日期冲突（精确到天）
     * @author sunzheng
     */
    public static Boolean isDaysRangeConflict(Date from1, Date to1, Date from2, Date to2) {
        from1 = setTimeToBeginningOfDay(from1);
        to1 = setTimeToBeginningOfDay(to1);
        from2 = setTimeToBeginningOfDay(from2);
        to2 = setTimeToBeginningOfDay(to2);
        if (isExistEmpty(from1, to1, from2, to2)) {
            // 区间不完整
            return true;
        }
        if (from1.after(to1) || from2.after(to2)) {
            // 时间区间始末倒置，返回nullxxxxxx
            return true;
        }

        if (!to1.before(to2) && !from1.before(to2)) {
            return true;
        }
        return false;
    }

    /**
     * 验证手机号是否合法
     *
     * @param phoneNo
     * @return
     */
    public static Boolean isMatchPhoneNo(String phoneNo) {
        if (PHONE_NO_PATTERN.matcher(phoneNo).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 验证邮箱是否合法
     *
     * @param email
     * @return
     */
    public static Boolean isMatchEmail(String email) {
        if (EMAIL_PATTERN.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 验证是否是否是字母
     *
     * @param letter
     * @return
     */
    public static Boolean isLetter(String letter) {
        if (LETTER_PATTERN.matcher(letter).matches()) {
            return true;
        }
        return false;
    }

    private CommonUtil() {
    }

    public static void main(String[] args) {
        long cur = System.currentTimeMillis();
        int days = 29;
        long millise = days * 24 * 60 * 60 * 1000;
        long total = cur - millise;
        Date date = new Date(Long.valueOf(total));
        daysBetweenExceptWeekend(new Date(), date);
    }

}
