package press.wein.home.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * 业务异常
 *
 * @author oukailiang
 * @create 2017-02-27 下午11:33
 */

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -8013200853461709878L;
    private String errCode;

    private String[] params;

    private String message;

    public BusinessException() {
        // Do nothing
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String errCode) {
        this.errCode = errCode;
        if (message == null) {
            this.message = errCode;
        }
    }

    public BusinessException(String errCode, List<String> params) {
        this.errCode = errCode;
        this.params = (String[]) params.toArray();
        if (message == null) {
            this.message = errCode;
        }
    }

    public BusinessException(String errCode, String... params) {
        this.errCode = errCode;
        this.params = params;
        if (message == null) {
            this.message = errCode;
        }
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
        if (message == null) {
            this.message = errCode;
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}