package press.wein.home.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片工具类
 */
public class ImageUtil {

    public static final Integer  WRONG_WIDTH = 2;
    public static final Integer  WRONG_HEIGHT = 3;
    public static final Integer  PASS_CHECK = 1;

    /**
     * @category 检查图片type
     * @param imgType
     * @return
     */
    public static boolean checkImgType(String imgType ,String[] allowedTypeArray){
        for(int i=0 ;i< allowedTypeArray.length ; i++){
            if(imgType.toLowerCase().equals(allowedTypeArray[i].toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * 校验图片宽高
     * @param imageFile
     * @return
     * @throws IOException
     */
    public static int checkImgWH(File imageFile,int allowedWidth , int allowedHeight) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        int imageWidth = bufferedImage.getWidth();
        int imageheight = bufferedImage.getHeight();
        if(allowedWidth < imageWidth){
            return WRONG_WIDTH;
        }
        if(allowedHeight < imageheight){
            return WRONG_HEIGHT;
        }
        return PASS_CHECK;
    }

    /**
     * 根据spring方式上传的图片文件进行长宽校验，并且长和宽是定值
     * @param imageFile
     * @param allowedWidth
     * @param allowedHeight
     * @return
     * @throws IOException
     */
    public static int checkImgWHForMultipartFileByEqual(MultipartFile imageFile,int allowedWidth , int allowedHeight) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());
        int imageWidth = bufferedImage.getWidth();
        int imageheight = bufferedImage.getHeight();
        if(allowedWidth !=  imageWidth){
            return WRONG_WIDTH;
        }
        if(allowedHeight !=  imageheight){
            return WRONG_HEIGHT;
        }
        return PASS_CHECK;
    }

    /**
     * 根据spring方式上传的图片文件进行长宽校验，并且长和宽属于某范围
     * @param imageFile
     * @param allowedWidth
     * @param allowedHeight
     * @return
     * @throws IOException
     */
    public static int checkImgWHForMultipartFileByRange(MultipartFile imageFile,int allowedWidth , int allowedHeight) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());
        int imageWidth = bufferedImage.getWidth();
        int imageheight = bufferedImage.getHeight();
        if(allowedWidth < imageWidth){
            return WRONG_WIDTH;
        }
        if(allowedHeight < imageheight){
            return WRONG_HEIGHT;
        }
        return PASS_CHECK;
    }

    /**
     * 校验图片大小
     * @param imageSize
     * @return
     * @throws IOException
     */
    public static boolean  checkImgSize(long imageSize,long alowedSize) throws IOException {
        if(alowedSize < imageSize) {
            return false;
        }
        return true;
    }


}
