package press.wein.home.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.ClassUtils;

/**
 * bean属性复制工具类
 *
 * @author oukailiang
 * @create 2017-02-28 下午9:50
 */

public class BeanUtil {

    public static void beanCopier(Object src, Object dest) {
        beanCopier(src, dest, null);
    }

    /**
     * @param src
     * @param dest
     * @param convert
     * @see BeanCopier
     */
    public static void beanCopier(Object src, Object dest, Converter convert) {
        BeanCopier beanCopier = BeanCopier.create(src.getClass(), dest.getClass(), false);
        beanCopier.copy(src, dest, convert);
    }

    public static String extractLShortName(Class<?> clz) {
        String shortName = ClassUtils.getShortName(clz);
        return Character.toLowerCase(shortName.charAt(0)) + shortName.substring(1);
    }

    public static String getNotNullStr(String value) {

        return (value != null) ? value : "";
    }

    public static String getNotNullStr(Object obj) {

        return (obj != null) ? obj.toString() : "";
    }
}
