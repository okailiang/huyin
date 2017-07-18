package press.wein.home.controller;

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
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Menu;
import press.wein.home.model.bo.UserSession;
import press.wein.home.model.vo.RoleVo;
import press.wein.home.service.MenuService;
import press.wein.home.service.RoleService;
import press.wein.home.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private MenuService menuService;

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
            LOG.error("RoleController.listRolesWithPage inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.listRolesWithPage inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(roleVoListPage);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/findAllRoles", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> findAllRoles(RoleVo roleVo) throws ServiceException {
        List<RoleVo> roleVoList;
        try {
            roleVoList = roleService.listRoles(roleVo);
        } catch (ServiceException e) {
            LOG.error("RoleController.findAllRoles inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.findAllRoles inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(roleVoList);
    }

    /**
     * 通过角色id获得对应的菜单
     *
     * @return
     */
    @RequestMapping(value = "/listRoleMenusByRoleId", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listRoleMenusByRoleId(RoleVo roleVo) throws ServiceException {
        List<Menu> menus;
        try {
            menus = menuService.listMenusByRoleId(roleVo.getId());
        } catch (ServiceException e) {
            LOG.error("RoleController.listRoleMenusByRoleId inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.listRoleMenusByRoleId inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(menus);
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
            LOG.error("RoleController.saveRole inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.saveRole inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.SAVE_SUCCESS);
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
            this.setModifier(roleVo);
            roleService.updateRole(roleVo);
        } catch (ServiceException e) {
            LOG.error("RoleController.updateRole inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.updateRole inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.UPDATE_SUCCESS);
    }

    /* 删除
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
            LOG.error("RoleController.removeRole inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.removeRole inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DELETE_SUCCESS);
    }

    /**
     * 添加菜单权限
     *
     * @param roleVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/addMenuPermission", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> addMenuPermission(@RequestBody RoleVo roleVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setCreatorAndModifier(roleVo);
            roleService.saveMenuPermission(roleVo);
        } catch (ServiceException e) {
            LOG.error("RoleController.addMenuPermission inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.addMenuPermission inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.ADD_MENU_PERMISSION_SUCCESS);
    }

    /**
     * 取消菜单权限
     *
     * @param roleVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/removeMenuPermission", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> removeMenuPermission(@RequestBody RoleVo roleVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(roleVo);
            roleService.removeMenuPermission(roleVo);
        } catch (ServiceException e) {
            LOG.error("RoleController.removeMenuPermission inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("RoleController.removeMenuPermission inputParam = [{}]", roleVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.REMOVE_MENU_PERMISSION_SUCCESS);
    }

    private void setCreatorAndModifier(RoleVo roleVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        roleVo.setCreator(userSession.getName());
        roleVo.setModifier(userSession.getName());
        roleVo.setCreatorId(userSession.getId());
        roleVo.setModifierId(userSession.getId());
    }

    private void setModifier(RoleVo roleVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        roleVo.setModifier(userSession.getName());
        roleVo.setModifierId(userSession.getId());
    }
}
