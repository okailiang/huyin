package press.wein.home.enumerate;

/**
 * @author oukailiang
 * @create 2017-07-19 下午6:25
 */
public interface FeedbackEnum {

    enum Type {
        ADVISE(1, "建议"),
        PROBLEM(2, "问题"),
        LOVE(3, "喜欢");

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

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    enum Status {
        NO_HANDLE(0, "未处理"),
        HANDLED(1, "已处理");

        private Integer value;
        private String name;

        Status(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getNameByValue(int value) {
            for (Status item : Status.values()) {
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
