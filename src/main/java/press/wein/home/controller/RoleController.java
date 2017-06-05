package press.wein.home.controller;

import com.alibaba.fastjson.JSON;
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
import press.wein.home.constant.Constants;
import press.wein.home.constant.TipConstants;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Menu;
import press.wein.home.model.bo.RoleMenu;
import press.wein.home.model.bo.UserSession;
import press.wein.home.model.vo.RoleVo;
import press.wein.home.model.vo.UserLoginVo;
import press.wein.home.service.RoleService;
import press.wein.home.util.CollectionUtil;
import press.wein.home.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色controller
 *
 * @author oukailiang
 * @create 2017-05-16 上午12:32
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
    public static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 分页获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/listRoles", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listRolesWithPage(Page<RoleVo> page, RoleVo roleVo) throws ServiceException {
        Page<RoleVo> roleVoListPage;
        try {
            roleVoListPage = roleService.listRolesByPage(page, roleVo);
        } catch (ServiceException e) {
            LOG.error("RoleController.listRolesWithPage input param [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.listRolesWithPage input param [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(roleVoListPage);
    }

    /**
     * 新增
     *
     * @param roleVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> saveRole(@RequestBody RoleVo roleVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setCreatorAndModifier(roleVo);
            roleService.saveRole(roleVo);
        } catch (ServiceException e) {
            LOG.error("RoleController.saveRole input param [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.saveRole input param [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.SAVE_SUCCESS);
    }

    private void setCreatorAndModifier(RoleVo roleVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        roleVo.setCreator(userSession.getName());
        roleVo.setModifier(userSession.getName());
        roleVo.setCreatorId(userSession.getId());
        roleVo.setModifierId(userSession.getId());
    }

    /**
     * 更新
     *
     * @param roleVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updateRole(@RequestBody RoleVo roleVo, HttpServletRequest request) throws ServiceException {

        try {
            roleService.updateRole(roleVo);
        } catch (ServiceException e) {
            LOG.error("RoleController.updateRole input param [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.updateRole input param [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.UPDATE_SUCCESS);
    }

    /* 删除
     * 更新
     *
     * @param roleVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/removeRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> removeRole(@RequestBody RoleVo roleVo, HttpServletRequest request) throws ServiceException {

        try {
            roleService.removeRole(roleVo.getId());
        } catch (ServiceException e) {
            LOG.error("RoleController.removeRole input param [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.removeRole input param [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DELETE_SUCCESS);
    }
}
