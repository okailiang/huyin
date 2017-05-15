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
    private String code;

    private String[] params;

    private String message;

    public BusinessException() {
        // Do nothing
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String code) {
        this.code = code;
        if (message == null) {
            this.message = code;
        }
    }

    public BusinessException(String code, List<String> params) {
        this.code = code;
        this.params = (String[]) params.toArray();
        if (message == null) {
            this.message = code;
        }
    }

    public BusinessException(String code, String... params) {
        this.code = code;
        this.params = params;
        if (message == null) {
            this.message = code;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        if (message == null) {
            this.message = code;
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