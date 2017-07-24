package press.wein.home.enumerate;

/**
 * @author oukailiang
 * @create 2017-07-23 下午5:07
 */

public interface PrintShopEnum {

    /**
     * 打印店类型
     */
    enum Type {
        UNIVERSITY(1, "高校"),
        NO_UNIVERSITY(2, "非高校");

        private Integer value;
        private String name;

        Type(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getNameByValue(int value) {
            for (Type item : Type.values()) {
                if (item.getValue() == value) {
                    return item.getName();
                }
            }
            return null;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}
