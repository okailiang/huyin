package press.wein.home.enumerate;

/**
 * 价格相关枚举
 *
 * @author oukailiang
 * @create 2017-02-16 下午7:53
 */

public interface PriceEnum {
    enum PriceType {
        BLACK_SINGLE(1, "黑白单面"),
        BLACK_DOUBLE(2, "黑白双面"),
        COLOR_SINGLE(3, "彩色单面"),
        COLOR_DOUBLE(4, "彩色双面");

        private Integer value;
        private String name;

        PriceType(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getNameByValue(int value) {
            for (PriceType item : PriceType.values()) {
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
