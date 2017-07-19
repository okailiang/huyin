package press.wein.home.enumerate;

/**
 * @author oukailiang
 * @create 2017-07-19 上午12:52
 */
public interface CityEnum {

    enum Type {
        ZERO(0, "省"),
        ONE(1, "直辖市"),
        TWO(2, "省城"),
        THREE(3, "市"),
        FOUR(4, "县（区）");

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
}
