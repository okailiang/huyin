package press.wein.home.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import press.wein.home.common.SysConfigProperty;
import press.wein.home.constant.Constants;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;

import java.io.*;
import java.util.Base64;

/**
 * wein 相关的文件
 *
 * @author oukailiang
 * @create 2017-07-24 下午4:25
 */

public class FileUtil {
    public static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

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
        //系统配置主目录
        ///Users/oukailiang/Documents/ SysConfigProperty.getProperty("wein.filepath")
        String prefix = "/Users/oukailiang/Documents/";
        sb.append(prefix);
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


    public static void saveImage(String imgStr, String path) throws ServiceException {
        if (imgStr == null) {
            return;
        }

        OutputStream out = null;
        try {
            imgStr = imgStr.replaceAll(IMAGE_BASE64_HEADER, "");
            path = SysConfigProperty.getProperty(Constants.WEIN_FILEPATH) + path;
            path = path.replaceAll("//", FILE_SEPARATOR).replaceAll("/", FILE_SEPARATOR);
            // 解密
            byte[] b = Base64.getDecoder().decode(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            LOG.error("FileUtil.saveImage IOException inputParam = [imgStr: {}, path: {}]", imgStr, path, e);
            throw ExceptionUtil.createServiceException(ExceptionCode.SYS_ERROR);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw ExceptionUtil.createServiceException(ExceptionCode.SYS_ERROR);
                }
            }
        }
    }

    /**
     * @param imagePath 图片在文件系统中的路径
     * @return
     */
    public static String imageToBase64(String imagePath) throws ServiceException {
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
            LOG.error("FileUtil.imageToBase64 IOException inputParam = [imagePath: {}]", imagePath, e);
            throw ExceptionUtil.createServiceException(ExceptionCode.SYS_ERROR);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    LOG.error("FileUtil.imageToBase64 IOException inputParam = [imagePath: {}]", imagePath, e);
                    throw ExceptionUtil.createServiceException(ExceptionCode.SYS_ERROR);
                }
            }

        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getShopImagePath(null));

        String str = "data:image/jpeg;base64,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";

//        saveImage(str.replaceAll(IMAGE_BASE64_HEADER, ""), getShopImagePath(null));
    }

    private FileUtil() {
    }
}
