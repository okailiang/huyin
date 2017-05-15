package press.wein.home.exception;

/**
 * @author oukailiang
 * @create 2017-05-14 下午4:17
 */

public class ExceptionUtil {

    /* 生成业务异常 */
    public static ServiceException createServiceException(ExceptionCode exceptionCode) {
        return new ServiceException(exceptionCode.getCode(), exceptionCode.getMsg());
    }

    /* 生成业务异常，带具体异常信息 */
    public static ServiceException createServiceException(ExceptionCode ec, Exception e) {
        return new ServiceException(ec.getCode(), ec.getMsg(), e);
    }

    /* 根据code和msg生成异常信息 */
    public static ServiceException createServiceException(String code, String msg) {
        return new ServiceException(code, msg);
    }

    public static BusinessException createBusinessException(BusinessException be) {
        return new BusinessException();
    }
}
