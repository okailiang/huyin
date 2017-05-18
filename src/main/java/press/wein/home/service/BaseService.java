package press.wein.home.service;

import org.springframework.stereotype.Service;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.util.CommonUtil;

/**
 * 基本的service
 *
 * @author oukailiang
 * @create 2017-02-27 下午8:08
 */
@Service(value = "baseService")
public class BaseService {

    /**
     * 校验参数
     *
     * @param args
     * @throws ServiceException
     */
    public void checkParamNull(Object... args) throws ServiceException {
        if (CommonUtil.isExistBlank(args)) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PARAM_NULL);
        }
    }

}
