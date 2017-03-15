package press.wein.home.enumerate;

/**
 * 文件相关的枚举类型
 *
 * @author oukailiang
 * @create 2017-02-16 下午7:56
 */

public interface FilesEnum {

    enum FileType {
        WORD(1, "doc/docx"),
        POWERPOINT(2, "ppt/pptx"),
        PDF(3, "pdf"),
        IMAGE(4, "png/jpg/jpeg");

        private Integer value;
        private String name;

        FileType(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getNameByValue(int value) {
            for (FileType item : FileType.values()) {
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
