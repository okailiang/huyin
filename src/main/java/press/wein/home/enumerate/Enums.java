package press.wein.home.enumerate;

/**
 * 共用枚举
 *
 * @author oukailiang
 * @create 2017-02-16 下午7:39
 */
public interface Enums {

    /**
     * 共用状态
     */
    enum UserRole {
        ADMIN(1, "系统管理员"),
        PRINTER(2, "打印管理员"),
        USER(3, "普通用户"),
        ANONYMOUS(4,"匿名用户");

        private Integer value;
        private String name;

        UserRole(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getNameByValue(int value) {
            for (UserRole item : UserRole.values()) {
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

    /**
     * 共用状态
     */
    enum Status {
        ALLOW(0, "启用"),
        DENY(1, "禁用");

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

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 菜单级别
     */
    enum MenuLevel {
        ZERO_LEVEL(0, "菜单标题"),
        ONE_LEVEL(1, "一级菜单"),
        TWO_LEVEL(2, "二级菜单"),
        THREE_LEVEL(3, "三级菜单"),
        FOUR_LEVEL(4, "四级菜单");

        private Integer value;
        private String name;

        MenuLevel(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getNameByValue(int value) {
            for (MenuLevel item : MenuLevel.values()) {
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
