package press.wein.home.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合工具类
 */
public class CollectionUtil {

    public static boolean isNullOrEmpty(Collection<?> c) {
        return (null == c || c.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return (null != c && c.size() > 0);
    }

    /**
     * list 分组
     *
     * @param inputList
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> inputList, int pageSize) {
        List<List<T>> resultList;
        int pageCount = 0;
        if (inputList.size() % pageSize == 0) {
            pageCount = inputList.size() / pageSize;
            resultList = new ArrayList<List<T>>(pageCount);
        } else {
            pageCount = inputList.size() / pageSize + 1;
            resultList = new ArrayList<List<T>>(pageCount);
        }
        int start = 0;
        for (int j = 1; j <= pageCount; j++) {
            List<T> subList;
            if (j == pageCount) {
                subList = inputList.subList(start, inputList.size());
            } else {
                subList = inputList.subList(start, start + pageSize);
            }
            resultList.add(subList);
            start += pageSize;
        }
        return resultList;
    }
}
