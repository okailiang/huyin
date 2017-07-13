package press.wein.home.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.common.Page;
import press.wein.home.constant.TipConstants;
import press.wein.home.controller.BaseController;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.bo.UserSession;
import press.wein.home.model.vo.SysUserVo;
import press.wein.home.model.vo.UserVo;
import press.wein.home.service.SysUserService;
import press.wein.home.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统用户管理
 *
 * @author oukailiang
 * @create 2017-07-05 下午6:27
 */
@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listSysUsersWithPage(Page<SysUserVo> page, SysUserVo sysUserVo) throws ServiceException {
        Page<SysUserVo> sysUserVoListPage;
        try {
            sysUserVoListPage = sysUserService.listSysUsersWithPage(page, sysUserVo);
        } catch (ServiceException e) {
            LOG.error("SysUserController.listSysUsersWithPage inputParam = [{}]", sysUserVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("SysUserController.listSysUsersWithPage inputParam = [{}]", sysUserVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(sysUserVoListPage);
    }


    /**
     * 获取系统用户
     *
     * @return
     */
    @RequestMapping(value = "/getSysUser", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> getMenu(@RequestParam(value = "id") Long id) throws ServiceException {
        return ResponseUtils.success(sysUserService.getSysUser(id));
    }

    /**
     * 新增
     *
     * @param sysUserVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> saveSysUser(@RequestBody SysUserVo sysUserVo, HttpServletRequest request) throws ServiceException {

        try {
            sysUserVo.setPassword("SysUser@666");
            this.setCreatorAndModifier(sysUserVo);
            sysUserService.saveSysUser(sysUserVo);
        } catch (ServiceException e) {
            LOG.error("SysUserController.saveSysUser inputParam = [{}]", sysUserVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("SysUserController.saveSysUser inputParam = [{}]", sysUserVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.SAVE_SUCCESS);
    }

    /**
     * 更新
     *
     * @param sysUserVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updateSysUser(@RequestBody SysUserVo sysUserVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(sysUserVo);
            sysUserService.updateSysUser(sysUserVo);
        } catch (ServiceException e) {
            LOG.error("SysUserController.updateSysUser inputParam = [{}]", sysUserVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("SysUserController.updateSysUser inputParam = [{}]", sysUserVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.UPDATE_SUCCESS);
    }


    /* 删除
     * 更新
     *
     * @param sysUserVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> removeSysUser(@RequestBody SysUserVo sysUserVo, HttpServletRequest request) throws ServiceException {

        try {
            sysUserService.removeSysUser(sysUserVo.getId());
        } catch (ServiceException e) {
            LOG.error("SysUserController.removeSysUser inputParam = [{}]", sysUserVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("SysUserController.removeSysUser inputParam = [{}]", sysUserVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DELETE_SUCCESS);
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
    public ResponseEntity<Object> enableUser(@RequestBody SysUserVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(userVo);
            sysUserService.enableSysUser(userVo);
        } catch (ServiceException e) {
            LOG.error("SysUserController.enableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("SysUserController.enableUser inputParam [{}]", userVo.toString(), e);
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
    public ResponseEntity<Object> disableUser(@RequestBody SysUserVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(userVo);
            sysUserService.disableSysUser(userVo);
        } catch (ServiceException e) {
            LOG.error("SysUserController.disableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("SysUserController.disableUser inputParam [{}]", userVo.toString(), e);
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
    public ResponseEntity<Object> resetPassword(@RequestBody SysUserVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            userVo.setPassword("SysUser@666");
            this.setModifier(userVo);
            sysUserService.resetPassword(userVo);
        } catch (ServiceException e) {
            LOG.error("SysUserController.resetPassword inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("SysUserController.resetPassword inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.RESET_PASSWORD_SUCCESS);
    }


    private void setCreatorAndModifier(SysUserVo sysUserVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        sysUserVo.setCreator(userSession.getName());
        sysUserVo.setModifier(userSession.getName());
        sysUserVo.setCreatorId(userSession.getId());
        sysUserVo.setModifierId(userSession.getId());
    }

    private void setModifier(SysUserVo sysUserVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        sysUserVo.setModifier(userSession.getName());
        sysUserVo.setModifierId(userSession.getId());
    }

}
