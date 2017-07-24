package press.wein.home.util;

import press.wein.home.common.SysConfigProperty;
import press.wein.home.constant.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * wein 相关的文件
 *
 * @author oukailiang
 * @create 2017-07-24 下午4:25
 */

public class FileUtil {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String WEIN_USER_FILE_PATH = "/wein-file/user-file/";

    public static final String WEIN_USER_IMAGE_PATH = "/wein-file/user-image/";

    public static final String WEIN_SHOP_IMAGE_PATH = "/wein-file/printShop-image/";

    public static final String DEFAULT_SHOP_IMAGE_TYPE = ".png";

    public static final String S_PLACEHOLDER = "%s";

    public static final String IMAGE_BASE64_HEADER = "data:image/jpeg;base64,";


    /**
     * 用户打印文件路径
     *
     * @param type
     * @return
     */
    public static String getUserFilePath(String type) {
        String shopImagePath = getFilePath();
        shopImagePath = String.format(shopImagePath, WEIN_USER_FILE_PATH, type);
        shopImagePath = shopImagePath.replaceAll("//", FILE_SEPARATOR).replaceAll("/", FILE_SEPARATOR);
        return shopImagePath;
    }

    /**
     * 用户打印图片路径
     *
     * @param type
     * @return
     */
    public static String getUserImagePath(String type) {
        String shopImagePath = getFilePath();
        shopImagePath = String.format(shopImagePath, WEIN_USER_IMAGE_PATH, type);
        shopImagePath = shopImagePath.replaceAll("//", FILE_SEPARATOR).replaceAll("/", FILE_SEPARATOR);
        return shopImagePath;
    }

    /**
     * 打印店图片文件路径
     *
     * @param type
     * @return
     */
    public static String getShopImagePath(String type) {
        if (StringUtil.isBlank(type)) {
            type = DEFAULT_SHOP_IMAGE_TYPE;
        }
        String shopImagePath = getFilePath();
        shopImagePath = String.format(shopImagePath, WEIN_SHOP_IMAGE_PATH, type);
        shopImagePath = shopImagePath.replaceAll("//", FILE_SEPARATOR).replaceAll("/", FILE_SEPARATOR);
        return shopImagePath;
    }

    /**
     * 文件目录和文件名命名规则
     *
     * @return
     */
    private static String getFilePath() {
        StringBuilder sb = new StringBuilder();
        ///Users/oukailiang/Documents/ SysConfigProperty.getProperty("wein.filepath")
//        String prefix = SysConfigProperty.getProperty(Constants.WEIN_FILEPATH);
//        sb.append(prefix);
        sb.append(S_PLACEHOLDER).append(FILE_SEPARATOR);
        //文件名
        String fileName = GenerateNumber.getNewFileName();
        //文件路径规则
        sb.append(fileName.substring(0, 4)).append(FILE_SEPARATOR).append(fileName.substring(4, 6))
                .append(FILE_SEPARATOR).append(fileName.substring(6, 8)).append(FILE_SEPARATOR);
        //添加文件名
        sb.append(fileName).append(S_PLACEHOLDER);
        return sb.toString();
    }

    /**
     * @param imagePath 图片在文件系统中的路径
     * @return
     */
    public static String imageToBase64(String imagePath) {
        String result = "";
        if (StringUtil.isBlank(imagePath)) {
            return result;
        }
        imagePath = SysConfigProperty.getProperty(Constants.WEIN_FILEPATH) + imagePath;
        imagePath = imagePath.replaceAll("//", FILE_SEPARATOR).replaceAll("/", FILE_SEPARATOR);
        File file = new File(imagePath);
        if (!file.exists()) {
            return result;
        }
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] imageByte = new byte[fis.available()];
            fis.read(imageByte);
            result = IMAGE_BASE64_HEADER + Base64.getEncoder().encodeToString(imageByte);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getShopImagePath(null));
    }

    private FileUtil() {
    }
}
