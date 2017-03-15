package press.wein.home.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * 共用静态常量
 *
 * @author oukailiang
 * @create 2017-02-17 下午8:14
 */

public class Constants {
    /**
     * 字符集编码
     */
    public static final String ENCODING = "utf-8";

    /**
     * 成功
     */
    public static final String SUCCESS = "success";

    /**
     * 失败
     */
    public static final String FAIL = "fail";
    /**
     * domain
     */
    public static final String DOMAIN_NAME = "domain.name";
    /**
     * googleAnalyticsId
     */
    public static final String GOOGLE_ANALYTICS_ID = "google.analytics.id";
    /**
     * session key
     */
    public static final String USER_SESSION_KEY = "USER_SESSION_KEY";
    /**
     * 登录密码错误次数
     */
    public static final String USER_ERROR_TIMES = "USER_ERROR_TIMES";
    /**
     * 登入密码
     */
    public static final String USER_PASS_WORD = "USER_PASSWORD";

    /**
     * session过期时间 分钟为单位
     */
    public static final int SESSION_EXPIRE = 60;

    /**
     * credit2 login 名称(用户当前登录时间)
     */
    public static final String CREDIT_LOGIN_COOKIE_NAME = "loginNameCookie";
    /**
     * wein cookie名称(用户当前登录时间)
     */
    public static final String CREDIT_USERINFO_COOKIE_NAME = "weinUserCookie";


    /**
     * 菜单角色缓存
     */
    public static final String CACHE_MENU_ROLE = "menu_role_";

    /**
     *
     */
    public static final String HTTP_HEADERS = "Content-Type,X-Requested-With,Accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers, HTTP_CONSUMER_KEY, HTTP_DEVICE_ID, HTTP_SIGNATURE, HTTP_ACCESS_TOKEN, HTTP_TIMESTAMP";

    /**
     * 登录页面url
     */
    public static final String LOGIN_PAGE_URI = "/index";

    /**
     * 访问无权限页面
     */

    public static final String NO_PERMISSON_URI = "/noPermisson";
    /**
     * 异步页面访问无权限页面
     */
    public static final String AJAX_NO_PERMISSON_URI = "/ajax_noPermisson";

    /**
     * 登录页面url
     */
    public static final String UPLOADFILE_URI = "/apply/uploadAttachment";

    /**
     * 登录验证url
     */
    public static final String LOGIN_VERIFY_URI = "/user/login";

    /**
     * 健康检查url
     */
    public static final String HEALTH_CHECK_URI = "/healthCheck";

    /**
     * 不需要登录验证的uri列表
     */
    private static final Set<String> notAuthFilterURIList;

    static {
        notAuthFilterURIList = new HashSet();
        notAuthFilterURIList.add(LOGIN_PAGE_URI);
        notAuthFilterURIList.add(LOGIN_VERIFY_URI);
        notAuthFilterURIList.add(UPLOADFILE_URI);
        notAuthFilterURIList.add(HEALTH_CHECK_URI);
    }

    public static Set<String> getNotAuthFilterURIList() {
        return notAuthFilterURIList;
    }


    private Constants() {
        //do nothing
    }
}
