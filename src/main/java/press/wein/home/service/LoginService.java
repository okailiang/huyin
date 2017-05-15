package press.wein.home.service;

import press.wein.home.exception.BusinessException;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.vo.UserLoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录service层
 *
 * @author oukailiang
 * @create 2017-02-27 下午8:05
 */

public interface LoginService {

    /**
     * 用户登录
     *
     * @param userLoginVo
     * @param request
     * @param response
     * @return
     */
    String login(UserLoginVo userLoginVo, HttpServletRequest request,
                 HttpServletResponse response);

    /**
     * 用户注册
     *
     * @param userLoginVo
     * @return
     * @throws BusinessException
     * @throws ServiceException
     */
    String register(UserLoginVo userLoginVo) throws BusinessException, ServiceException;

    /**
     * 重置密码
     *
     * @param userLoginVo
     * @return
     * @throws BusinessException
     * @throws ServiceException
     */
    String resetPassword(UserLoginVo userLoginVo) throws BusinessException, ServiceException;
}
