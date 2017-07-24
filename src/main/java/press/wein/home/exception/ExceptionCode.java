package press.wein.home.exception;

/**
 * 异常码和异常信息
 *
 * @author oukailiang
 * @create 2017-05-14 下午3:58
 */

public enum ExceptionCode {

/* ============================================ 业务异常开始 =========================================== */

    /* ==================================== 基础异常 ==================================== */
    SYS_ERROR("SYS_ERROR", "系统异常，请联系管理员"),
    RESPONSE_ERROR("RESPONSE_ERROR", "服务异常"),
    PERMISSION_DENIED("PERMISSION_DENIED", "无此操作权限"),
    INVALID_PARAM("INVALID_PARAM", "参数无效"),
    OTHER_SERVICE_ERROR("INVALID_PARAM", "其他服务异常"),
    PARAM_NULL("PARAM_NULL", "必要参数不能为空"),
    DATA_PARSE_ERROR("DATA_PARSE_ERROR", "数据解析异常"),
    EXPORT_ERROR("EXPORT_ERROR", "Excel导出失败"),
    FILES_COUNT_ERROR("FILES_COUNT_ERROR", "附件最多10个"),
    FILES_SIZE_ERROR("FILES_SIZE_ERROR", "附件不能超过20M"),
    DOWNLOAD_ERROR("DOWNLOAD_ERROR", "附件下载失败"),
    TIME_INVALID("TIME_INVALID", "请求时间不合法"),
    SERVICE_DOWNGRADING("SERVICE_DOWNGRADING", "服务关闭"),
    NOT_FIND_OBJECT("NOT_FIND_OBJECT", "找不到对象"),
    NOT_EXIST("NOT_EXIST", "该条数据已不存在"),
    NAME_EXISTED("NAME_EXISTED", "该名称已经存在，请换个名吧"),
    INVALID_STATUS_VALUE("INVALID_STATUS_VALUE", "传入状态值不正确"),

    /* ==================================== 注册登录 ==================================== */
    PHONE_ERROR("PHONE_ERROR", "手机号格式不正确"),
    EMAIL_ERROR("EMAIL_ERROR", "邮箱格式不正确"),
    ACCOUNT_EXIST("ACCOUNT_EXIST", "该账户已存在"),
    ACCOUNT_NOT_EXIST("ACCOUNT_NOT_EXIST", "该账户不存在"),
    PHONE_EXIST("PHONE_EXIST", "该手机号已注册"),
    EMAIL_EXIST("EMAIL_EXIST", "该邮箱已注册"),
    EMAIL_NOT_EXIST("EMAIL_NOT_EXIST", "该邮箱还未注册"),
    USERNAME_EXIST("USERNAME_EXIST", "该用户名已存在"),
    KAPTCHA_CODE_ERROR("KAPTCHA_CODE_ERROR", "验证码不正确或已失效"),
    KAPTCHA_CODE_INVALID("KAPTCHA_CODE_INVALID", "验证码失效"),
    ACCOUNT_PASSWORD_ERROR("ACCOUNT_PASSWORD_ERROR", "账户名和密码不匹配"),
    PASSWORD_RULE("PASSWORD_RULE", "密码必须包括大小写字母、数字、特殊字符，长度6-20"),
    PASSWORD_ERROR_TIME_TIP("PASSWORD_ERROR_TIME_TIP", "输入密码错误%d次，%s分钟后再试"),
    SESSION_INVALID("SESSION_INVALID", "登录失效，请重新登录"),
    PHONE_EXISTED("PHONE_EXISTED", "该手机号已经存在，请换个名吧"),
    EMAIL_EXISTED("EMAIL_EXISTED", "该邮箱已经存在，请换个名吧"),
    USER_DENY("USER_DENY", "该用户被禁用，请联系管理员"),

    /* ==================================== 菜单 ==================================== */
    MENU_LEVEL_MAX("MENU_LEVEL_MAX", "最大只允许添加四级菜单"),
    NO_REMOVE_ROLE("NO_REMOVE_ROLE", "该角色已经关联用户不能删除"),
    MENU_NO_REMOVE("MENU_NO_REMOVE", "该菜单已经关联角色不能删除"),

    /* ==================================== 打印店 ==================================== */
    PRINTSHOP_IMAGE_MAX("PRINTSHOP_IMAGE_MAX", "请上传不大于2M的图片"),
    CITY_ID_ERROR("CITY_ID_ERROR", "该城市id不存在"),


	/* ==================================== 消息异常 ==================================== */

    CREATE_MESSAGE_ERR("CREATE_MESSAGE_ERR", "创建消息失败"),
    DELETE_MESSAGE_ERR("DELETE_MESSAGE_ERR", "删除消息失败"),
    MESSAGE_NOT_BELONG_AGENT("MESSAGE_NOT_BELONG_AGENT", "无该消息查看权限"),
    ATTACHMENT_NOT_BELONG_AGENT("ATTACHMENT_NOT_BELONG_AGENT", "无该附件查看权限"),
    DELETE_MESSAGE_ATTACHMENT_ERR("DELETE_MESSAGE_ERR", "删除消息失败"),

    /* ==================================== 文件管理异常 ==================================== */

    FILE_UPLOAD_ERROR("FILE_UPLOAD_ERROR", "文件上传失败"),
    FILE_GET_ERROR("FILE_GET_ERROR", "文件读取失败"),
    FILE_DOWNLOAD_ERROR("FILE_DOWNLOAD_ERROR", "文件下载失败");


/* ============================================ 系统异常结束 =========================================== */

    private String code;
    private String msg;

    private ExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
