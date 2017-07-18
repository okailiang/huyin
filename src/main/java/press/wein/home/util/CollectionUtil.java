package press.wein.home.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 集合工具类
 */
public class CollectionUtil {
    public static final Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

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
        int pageCount;
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

    public static <T, E> List<E> copyToDescList(List<T> srcList, List<E> descList, Class<E> descClass) {
        if (srcList == null) {
            return new ArrayList<>();
        }
        if (descList == null) {
            descList = new ArrayList<>();
        }

        try {
            for (T t : srcList) {
                E obj = descClass.newInstance();
                BeanUtil.beanCopier(t, obj);
                descList.add(obj);
            }
        } catch (InstantiationException e) {
            LOG.error("copyToDescList class newInstance InstantiationException ", e);
        } catch (IllegalAccessException e) {
            LOG.error("copyToDescList class newInstance IllegalAccessException ", e);
        }

        return descList;
    }

    public static <T, E> List<E> copyToDescList(List<T> srcList, Class<E> descClass) {
        return copyToDescList(srcList, null, descClass);
    }

    /**
     * 对象转为map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                map.put(key, value);
            }
        } catch (Exception e) {
            LOG.error("CollectionUtil objectToMap error:{}", e);
        }
        return map;
    }

    /**
     * 自省实现map转Java对象
     *
     * @param map       map<String,Object>
     * @param beanClass 要转为的类
     * @return 要转化的对象类
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (map == null) {
            return null;
        }

        Object obj = null;
        try {
            obj = beanClass.newInstance();
            Object mapValue;

            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    mapValue = map.get(property.getName());
                    setterValue(property, mapValue, obj);
                }
            }
        } catch (Exception e) {
            LOG.error("CollectionUtil mapToObject error:{}", e);
        }
        return obj;
    }


    /**
     * 给Java对象设置值
     *
     * @param property 属性对象
     * @param mapValue 给属性赋值的值
     * @param object   Java对象
     */
    private static void setterValue(PropertyDescriptor property, Object mapValue, Object object) throws
            InvocationTargetException, IllegalAccessException, ParseException {
        Method setter = property.getWriteMethod();
        if (mapValue == null) {
            setter.invoke(object, mapValue);
            return;
        }

        Class propertyType = property.getPropertyType();
        String type = propertyType.getName();
        String value = mapValue.toString();

        if (type.equals("java.lang.String")) {
            setter.invoke(object, value);
        } else if (type.equals("java.lang.Integer")) {
            setter.invoke(object, Integer.parseInt(value));
        } else if (type.equals("java.lang.Long")) {
            setter.invoke(object, Long.parseLong(value));
        } else if (type.equals("java.math.BigDecimal")) {
            setter.invoke(object, BigDecimal.valueOf(Double.parseDouble(value)));
        } else if (type.equals("java.math.BigInteger")) {
            setter.invoke(object, BigInteger.valueOf(Long.parseLong(value)));
        } else if (type.equals("java.util.Date")) {
            setter.invoke(object, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
        } else if (type.equals("java.lang.Boolean")) {
            setter.invoke(object, Boolean.valueOf(value));
        } else if (type.equals("java.lang.Float")) {
            setter.invoke(object, Float.parseFloat(value));
        } else if (type.equals("java.lang.Double")) {
            setter.invoke(object, Double.parseDouble(value));
        } else if (type.equals("java.lang.byte[]")) {
            setter.invoke(object, value.getBytes());
        } else {
            setter.invoke(object, value);
        }
    }

    private CollectionUtil() {
        //do nothing
    }
}
