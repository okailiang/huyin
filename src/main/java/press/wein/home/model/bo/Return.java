package press.wein.home.model.bo;

import press.wein.home.constant.RetConstant;

import java.io.Serializable;

/**
 * 返回类型值封装
 *
 * @param <OUT>
 */
public class Return<OUT> implements Serializable {

    private static final long serialVersionUID = 4615419905486103620L;

	/*-----------------------------------------  parameter  -----------------------------------------*/

    /**
     * 通用简单Return信息
     */
    public static final Return<Void> OK = new Return<Void>(RetConstant.CODE_OK, "OK");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回提示信息
     */
    private String message;
    /**
     * 返回提示信息
     */
    private String message2;

    /**
     * 返回业务对象，可选
     */
    private OUT out;

	/*-----------------------------------  constructor && method  -----------------------------------*/

    public Return() {
    }

    public Return(int code, OUT out) {
        this.code = code;
        this.out = out;
    }

    public Return(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Return(int code, String message, String message2) {
        this.code = code;
        this.message = message;
        this.message2 = message2;
    }

    public Return(int code, String message, String message2, OUT out) {
        this.code = code;
        this.message = message;
        this.message2 = message2;
        this.out = out;
    }

    public Return(int code, String message, OUT out) {
        this.code = code;
        this.message = message;
        this.out = out;
    }

    public static <OUT> Return<OUT> valueOf(int code, OUT out) {
        return new Return<OUT>(code, out);
    }

    public static <OUT> Return<OUT> valueOf(int code, String message) {
        return new Return<OUT>(code, message);
    }

    public static <OUT> Return<OUT> valueOf(int code, String message, String message2) {
        return new Return<OUT>(code, message, message2);
    }

    public static <OUT> Return<OUT> valueOf(int code, String message, String message2, OUT out) {
        return new Return<OUT>(code, message, message2, out);
    }

    public static <OUT> Return<OUT> valueOf(int code, String message, OUT out) {
        return new Return<OUT>(code, message, out);
    }

    public static Return<Void> valueOfOK(String message) {
        return new Return<Void>(RetConstant.CODE_OK, message);
    }

    public static <OUT> Return<OUT> valueOfOK(OUT out) {
        return new Return<OUT>(RetConstant.CODE_OK, out);
    }

    public static Return<String> valueOfOKForString(String out) {
        Return<String> retNew = new Return<String>();
        retNew.setCode(RetConstant.CODE_OK);
        retNew.setOut(out);
        return retNew;
    }

    public static <OUT> Return<OUT> valueOfOK(String message, OUT out) {
        return new Return<OUT>(RetConstant.CODE_OK, message, out);
    }

    public static <OUT> Return<OUT> valueOfERROR(String message) {
        return new Return<OUT>(RetConstant.SYSTEM_ERROR_CODE, message);
    }

    public static <OUT> Return<OUT> valueOfERROR(String message, OUT out) {
        return new Return<OUT>(RetConstant.SYSTEM_ERROR_CODE, message, out);
    }

    @Override
    public String toString() {
        return "Return [code=" + code + ", message=" + message + "]";
    }

    public boolean isSuccess() {
        return code == 0;
    }

	/*-----------------------------------------  get && set  -----------------------------------------*/

    /**
     * @return 返回码
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code 返回码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return 返回提示信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message 返回提示信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return 返回业务对象
     */
    public OUT getOut() {
        return out;
    }

    /**
     * @param out 返回业务对象
     */
    public void setOut(OUT out) {
        this.out = out;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
