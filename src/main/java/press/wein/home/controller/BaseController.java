package press.wein.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import press.wein.home.exception.BusinessException;
import press.wein.home.exception.ServiceException;
import press.wein.home.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 所有controller共用
 *
 * @author oukailiang
 * @create 2017-05-14 上午10:39
 */
@Controller
public abstract class BaseController {

    public static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public @ResponseBody
    ResponseEntity<Object> handleMissingServletRequestParameterException(HttpServletRequest req, MissingServletRequestParameterException exception) {
        LOG.error("[me.ele.bpm.family]Request:{}, exception:{}", req.getRequestURL(), exception);
        return ResponseUtils.fail("必要请求参数不能为空");
    }

    @ExceptionHandler(TypeMismatchException.class)
    public @ResponseBody
    ResponseEntity<Object> handleMissingServletRequestParameterException(HttpServletRequest req, TypeMismatchException exception) {
        LOG.error("[me.ele.bpm.family]Request:{}, exception:{}", req.getRequestURL(), exception);
        return ResponseUtils.fail("请求参数类型异常");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody
    ResponseEntity<Object> handleJsonMappingException(HttpServletRequest req, HttpMessageNotReadableException exception) {
        LOG.error("[me.ele.bpm.family]Request:{}, exception:{}", req.getRequestURL(), exception);
        return ResponseUtils.fail("请求参数解析异常");
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<Object> handleThrowable(HttpServletRequest req, Throwable exception) {
        LOG.error("[press.wein.home] Request:{}, exception:{}", req.getRequestURL(), exception);
        return ResponseUtils.error("未知异常");
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleBusinessException(HttpServletRequest request, BusinessException e) {
        LOG.error("[press.wein.home] Request:{},message:{},error_code: {} ", request.getRequestURL(), e.getCode(), e.getMessage());
        return ResponseUtils.fail(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseEntity<Object> handleServiceException(HttpServletRequest request, ServiceException e) {
        LOG.error("[press.wein.home] Request:{},message:{},error_code: {} ", request.getRequestURL(), e.getCode(), e.getMessage());
        return ResponseUtils.fail(e.getMessage());
    }
}
