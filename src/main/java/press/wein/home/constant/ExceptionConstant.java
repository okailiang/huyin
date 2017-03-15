package press.wein.home.constant;

/**
 * 日志中使用到的常量
 *
 * @author oukailiang
 * @create 2017-03-08 下午8:46
 */

public class ExceptionConstant {
    public static final String SYSTEM_ERROR = "系统错误，请联系管理员！";
    //=================start登录===========================
    public static final String KAPTCHA_CODE_ERROR = "验证码输入不正确！";
    public static final String ACCOUNT_PASSWORD_ERROR = "账户名和密码不匹配！";
    public static final String PASSWORD_ERROR_TIME_TIP = "输入密码错误%d次，%s分钟后再试！";

    public static final Integer PASSWORD_ERROR_TIME = 3;



    //=================end登录===========================
}
