package press.wein.home.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.common.Page;
import press.wein.home.constant.TipConstants;
import press.wein.home.controller.BaseController;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.bo.UserSession;
import press.wein.home.model.vo.UserVo;
import press.wein.home.service.UserService;
import press.wein.home.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册用户管理
 *
 * @author oukailiang
 * @create 2017-07-04 上午12:45
 */
@Controller
@RequestMapping(value = "/admin/user")
public class AdminUserController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listUsers(Page<UserVo> page, UserVo userVo) throws ServiceException {
        Page<UserVo> resultPage;
        try {
            resultPage = userService.listUsersWithPage(page, userVo);
        } catch (ServiceException e) {
            LOG.error("AdminUserController.listUsers inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("AdminUserController.listUsers inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(resultPage);
    }

    /**
     * 启用
     *
     * @param userVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/enableUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> enableUser(@RequestBody UserVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(userVo);
            userService.enableUser(userVo);
        } catch (ServiceException e) {
            LOG.error("AdminUserController.enableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("AdminUserController.enableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.ENABLE_SUCCESS);
    }

    /**
     * 禁用
     *
     * @param userVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/disableUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> disableUser(@RequestBody UserVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(userVo);
            userService.disableUser(userVo);
        } catch (ServiceException e) {
            LOG.error("AdminUserController.disableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("AdminUserController.disableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DISABLE_SUCCESS);
    }

    /**
     * 禁用
     *
     * @param userVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> resetPassword(@RequestBody UserVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            userVo.setPassword("User@666");
            this.setModifier(userVo);
            userService.resetPassword(userVo);
        } catch (ServiceException e) {
            LOG.error("AdminUserController.resetPassword inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("AdminUserController.resetPassword inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.RESET_PASSWORD_SUCCESS);
    }

    /**
     * 设置创建人和修改人信息
     *
     * @param vo
     */
    private void setCreatorAndModifier(UserVo vo) {
        UserSession userSession = ApplicationUserContext.getUser();
        vo.setCreator(userSession.getName());
        vo.setModifier(userSession.getName());
        vo.setCreatorId(userSession.getId());
        vo.setModifierId(userSession.getId());
    }

    /**
     * 设置修改人信息
     *
     * @param vo
     */
    private void setModifier(UserVo vo) {
        UserSession userSession = ApplicationUserContext.getUser();
        vo.setModifier(userSession.getName());
        vo.setModifierId(userSession.getId());
    }
}
