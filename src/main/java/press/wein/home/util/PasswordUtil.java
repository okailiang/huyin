package press.wein.home.util;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author oukailiang
 * @create 2017-07-22 下午11:06
 */

public class PasswordUtil {

    /*大写*/
    private static final char[] UPPER_ENGLISH_ARRAY = new char[26];
    /*小写*/
    private static final char[] LOWER_ENGLISH_ARRAY = new char[26];
    /*数字*/
    private static final char[] DIGIT_ARRAY = new char[10];
    /*特殊字符   20-47  58-64 91-96 123-126*/
    private static final char[] SPECIAL_ARRAY = {'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/', '?'};

    static {
        int firstEnglishCharIndex = (int) 'A';
        int lastEnglishCharIndex = (int) 'Z';
        int upperIndex = 0;
        int lowerIndex = 0;
        for (int i = firstEnglishCharIndex; i <= lastEnglishCharIndex; i++) {
            UPPER_ENGLISH_ARRAY[upperIndex++] = (char) i;
            LOWER_ENGLISH_ARRAY[lowerIndex++] = (char) (i + 32);
        }
        //数字48-57
        int zero = 48;
        for (int i = 0; i < 10; i++) {
            DIGIT_ARRAY[i] = (char) (zero + i);
        }
    }

    /**
     * 产生随机密码
     *
     * @return
     */
    public static String getRandomPassword() {
        char[] password = new char[8];
        int i = 0;
        for (; i < 2; i++) {
            password[i] = UPPER_ENGLISH_ARRAY[RandomUtils.nextInt(0, 26)];
        }
        for (; i < 4; i++) {
            password[i] = LOWER_ENGLISH_ARRAY[RandomUtils.nextInt(0, 26)];
        }
        for (; i < 6; i++) {
            password[i] = DIGIT_ARRAY[RandomUtils.nextInt(0, 10)];
        }
        for (; i < 8; i++) {
            password[i] = SPECIAL_ARRAY[RandomUtils.nextInt(0, SPECIAL_ARRAY.length)];
        }
        //打乱顺序
        return String.valueOf(upsetOrder(password));
    }

    private static char[] upsetOrder(char[] password) {
        int len = password.length;
        char[] result = new char[len];
        int index = 0;
        while (index < len) {
            //剩余的数组长度
            int position = len - index;
            int random = RandomUtils.nextInt(0, position);
            result[index++] = password[random];
            password[random] = password[position - 1];
        }
        return result;
    }

    /**
     * 包含字母数字特殊字符,6-20
     *
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        //包含空格直接返回FALSE
        if (StringUtils.isEmpty(password) || password.contains(" ")) {
            return false;
        }
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\p{Punct}+]).{6,20}$";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(password);
        return m.find();
    }

    /**
     * 包含字母数字特殊字符,6-20
     *
     * @param password
     * @return
     */
    public static boolean checkPassword1(String password) {
        //包含空格直接返回FALSE
        if (StringUtils.isEmpty(password) || password.contains(" ")) {
            return false;
        }
        if (password.length() < 6 || password.length() > 20) {
            return false;
        }
        Pattern pattern1 = Pattern.compile("[a-z]*");
        Pattern pattern2 = Pattern.compile("[A-Z]*");
        Pattern pattern3 = Pattern.compile("[0-9]*");
        Pattern pattern4 = Pattern.compile("\\p{Punct}+");
        if (!pattern1.matcher(password).find()) {
            return false;
        }
        if (!pattern2.matcher(password).find()) {
            return false;
        }
        if (!pattern3.matcher(password).find()) {
            return false;
        }
        if (!pattern4.matcher(password).find()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkPassword1("TApss>"));
        for (int i = 0; i < 100000; i++) {
            String str = getRandomPassword();
            System.out.println(str);
            boolean result = checkPassword(str);
            System.out.println(result);
            if (!result) {
                System.out.println(str + "=>>>>>>>>>>>" + result);
            }
        }
    }
}
