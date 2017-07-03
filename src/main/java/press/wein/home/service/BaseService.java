package press.wein.home.service;

import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.util.BeanUtil;
import press.wein.home.util.CommonUtil;

import java.util.List;
import java.util.Map;

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

    /**
     * 校验重名
     *
     * @param count
     * @throws ServiceException
     */
    public void checkRepeatName(int count) throws ServiceException {
        if (count > 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NAME_EXISTED);
        }
    }

    /**
     * 设置分页查询参数
     *
     * @throws ServiceException
     */
    public void setPageParam(Map<String, Object> queryParam, Page page) throws ServiceException {
        queryParam.put("start", page.getStart());
        queryParam.put("pageSize", page.getPageSize());
    }
}
