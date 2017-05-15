package press.wein.home.model.vo;

import press.wein.home.model.User;

import java.io.Serializable;

/**
 * 用户登录类
 *
 * @author oukailiang
 * @create 2017-02-27 下午8:10
 */

public class UserLoginVo implements Serializable {

    private static final long serialVersionUID = 88898779556979831L;

    /**
     * 是否记住账号
     */
    private boolean checkFlag;
    /**
     * 是否需要验证码验证
     */
    private boolean kaptchaFlag;
    /**
     * 验证码
     */
    private String kaptchaCode;

    /**
     * 账户id
     */
    private Long id;

    /**
     * 统一登录账户
     */
    private String account;

    private String userName;

    /**
     * 前台传递密码
     */
    private transient String password;

    /**
     * 加密后密码
     */
    private transient String passwordMd5;

    private String email;

    private String phoneNo;

    private String wxOpenid;

    private Integer errorTimes;
    /**
     * 1：普通用户 2：系统后台 3：打印店
     */
    private int type;

    private String token;

    private String registerCode;

    public UserLoginVo() {
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public boolean isKaptchaFlag() {
        return kaptchaFlag;
    }

    public void setKaptchaFlag(boolean kaptchaFlag) {
        this.kaptchaFlag = kaptchaFlag;
    }

    public String getKaptchaCode() {
        return kaptchaCode;
    }

    public void setKaptchaCode(String kaptchaCode) {
        this.kaptchaCode = kaptchaCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public Integer getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(Integer errorTimes) {
        this.errorTimes = errorTimes;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }
}