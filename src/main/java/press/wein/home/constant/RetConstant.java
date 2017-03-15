package press.wein.home.constant;

/**
 * 返回码
 */
public class RetConstant {
    /**
     * 系统级别返回码，0代表正常
     */
    public static final int CODE_OK = 0;
    //返回错误
    public static final int ERROR = 1;
    //业务错误
    public static final int BUSINESS_ERROR = 2;
    public static final int CODE_ERROR = -1;

    /**
     * 系统级别返回码，-1代表异常
     */
    public static final int SYSTEM_ERROR_CODE = -1;
    public static final String SYSTEM_ERROR_MESSAGE = "系统错误";

    /**
     * 参数校验错误码定义范围1000-1199
     **/
    public static final int PARAM_CODE_SATRT = -1199;
    public static final int PARAM_CODE_END = -1000;

    /**
     * 参数校验相关错误
     */
    public static final int IN_ALL_NULL_CODE = -1010;
    public static final String IN_ALL_NULL_MSG = "必选其一参数全部为空";

    public static final int IN_REQ_NULL_CODE = -1020;
    public static final String IN_REQ_NULL_MSG = "必填参数为空";

    public static final int FAIL_VALIDATE_CODE = -1030;
    public static final String FAIL_VALIDATE_MSG = "验证失败";

    public static final int LIST_SIZE_TOO_LONG_CODE = -1040;
    public static final String LIST_SIZE_TOO_LONG_MSG = "List数据超长";

    public static final int LIST_EMPTY_CODE = -1041;
    public static final String LIST_EMPTY_MSG = "List为空";

    public static final int LIST_SIZE_ERROR_CODE = -1042;
    public static final String LIST_SIZE_ERROR_MSG = "List数量不正确";

    /**
     * 数据库操作相关错误
     */
    public static final int FAIL_DB_INSERT_CODE = -1210;
    public static final String FAIL_DB_INSERT_MSG = "数据插入失败";

    public static final int FAIL_DB_UPDATE_CODE = -1220;
    public static final String FAIL_DB_UPDATE_MSG = "数据更新失败";

    public static final int FAIL_DB_DELETE_CODE = -1230;
    public static final String FAIL_DB_DELETE_MSG = "数据删除失败";

    public static final int FAIL_DB_SELECT_CODE = -1240;
    public static final String FAIL_DB_SELECT_MSG = "数据查询异常";

    /**
     * 基本运算相关的错误
     */
    public static final int DIVISOR_IS_ZERO_CODE = -1310;
    public static final String DIVISOR_IS_ZERO__MSG = "除数为零异常";

    /**
     * 日期解析错误
     */
    public static final int PRASE_DATE_CODE = -1320;
    public static final String PRASE_DATE_MSG = "日期解析异常";

    /**
     * 超过时间限制
     */
    public static final int OVER_ITME_CODE = -1321;
    public static final String OVER_ITME_MSG = "超过时间限制";

    public static final int PARAM_ERROR_CODE = -1322;
    public static final String PARAM_ERROR_MSG = "传入参数错误";

    public static final int REDIS_ERROR_CODE = -1323;
    public static final String REDIS_ERROR_MSG = "redis服务异常";

    public static final int NOT_LOGIN_ERROR_CODE = -1324;
    public static final String NOT_LOGIN_ERROR_MSG = "用户未登录或者传入用户ID错误";

    public static final int SPRING_ERROR = -2;
    public static final String SPRING_ERROR_MSG = "注入失败";

    public static final String OUT_BASE_MESSAGE = "出错啦，请稍后再试~";

    public static final int OUTTER_SERVICE_ERROR_CODE = -998;//第三方接口错误
    public static final String OUTTER_SERVICE_ERROR_MSG = "调用第三方接口错误";
    /**
     * 返回给用户的提示语
     */
    public static final String USER_ERROR_MESSAGE = "出错啦，请稍后再试";

    /**
     * 系统级别返回码，-1代表异常
     */
    public static final int INTERFACE_ERROR_CODE = -3;
    public static final String INTERFACE_ERROR_MESSAGE = "接口调用失败";

}
