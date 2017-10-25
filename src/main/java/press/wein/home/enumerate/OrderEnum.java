package press.wein.home.enumerate;

import java.util.LinkedList;

/**
 * 订单相关的枚举类型
 *
 * @author oukailiang
 * @create 2017-02-16 下午7:57
 */
public interface OrderEnum {
    enum OrderStatus {
        NO_PAY(1, "未付款"),
        NO_PRInt(2, "未打印"),
        NO_DELIVERY(3, "待领取"),
        FINISHED(4, "已完成");

        private Integer value;
        private String name;

        OrderStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getNameByValue(int value) {
            for (OrderStatus item : OrderStatus.values()) {
                if (item.getValue() == value) {
                    return item.getName();
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }
}
