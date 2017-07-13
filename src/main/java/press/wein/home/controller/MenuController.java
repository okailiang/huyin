package press.wein.home.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.constant.Constants;
import press.wein.home.constant.TipConstants;
import press.wein.home.enumerate.Enums;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Menu;
import press.wein.home.model.SysRoleMenu;
import press.wein.home.model.bo.RoleMenu;
import press.wein.home.model.bo.UserSession;
import press.wein.home.model.vo.MenuVo;
import press.wein.home.redis.RedisClient;
import press.wein.home.service.MenuService;
import press.wein.home.util.CollectionUtil;
import press.wein.home.util.ResponseUtils;
import press.wein.home.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单controller层
 *
 * @author oukailiang
 * @create 2017-03-08 下午12:06
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisClient redisClient;

    /**
     * 通过角色获取菜单
     *
     * @return
     */
    @RequestMapping(value = "/getRoleMenu", method = RequestMethod.GET)
    @ResponseBody
    private String getMenuByMenu() {
        List<RoleMenu> roleMenuList = new ArrayList<>();
        RoleMenu mainRoleMenu = new RoleMenu();
        mainRoleMenu.setHeading("true");
        mainRoleMenu.setText("主导航栏");
        mainRoleMenu.setTranslate("sidebar.heading.HEADER");
        roleMenuList.add(mainRoleMenu);

        List<Menu> menuList = ApplicationUserContext.getUser().getMenuList();
        if (CollectionUtil.isNotEmpty(menuList)) {
            this.convertToRoleMenu(menuList, roleMenuList);
            return JSON.toJSONString(roleMenuList);
        }
        String roleMenu = redisClient.get(Constants.CACHE_MENU_ROLE);
        return roleMenu;
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @RequestMapping(value = "/getAllMenu", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> getAllMenu() {

        return ResponseUtils.success(menuService.listAllMenus());
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> getMenu(@RequestParam(value = "id") Integer id) throws ServiceException {
        return ResponseUtils.success(menuService.getMenuById(id));
    }

    /**
     * 新增
     *
     * @param menuVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> saveMenu(@RequestBody MenuVo menuVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setCreatorAndModifier(menuVo);
            menuService.saveMenu(menuVo);
        } catch (ServiceException e) {
            LOG.error("MenuController.saveMenu ServiceException, inputParam [{}]", menuVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("MenuController.saveMenu Exception, inputParam [{}]", menuVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.SAVE_SUCCESS);
    }

    private void setCreatorAndModifier(MenuVo menuVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        menuVo.setCreator(userSession.getName());
        menuVo.setModifier(userSession.getName());
        menuVo.setCreatorId(userSession.getId());
        menuVo.setModifierId(userSession.getId());
    }

    private void setModifier(MenuVo menuVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        menuVo.setModifier(userSession.getName());
        menuVo.setModifierId(userSession.getId());
    }

    /**
     * 更新
     *
     * @param menuVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updateMenu(@RequestBody MenuVo menuVo, HttpServletRequest request) throws ServiceException {

        try {
            setModifier(menuVo);
            menuService.updateMenu(menuVo);
        } catch (ServiceException e) {
            LOG.error("MenuController.updateMenu ServiceException, inputParam [{}]", menuVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("MenuController.updateMenu Exception, inputParam [{}]", menuVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.UPDATE_SUCCESS);
    }

    /* 删除
     *
     * @param menuVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/removeMenu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> removeMenu(@RequestBody MenuVo menuVo, HttpServletRequest request) throws ServiceException {

        try {
            menuService.removeMenu(menuVo.getId());
        } catch (ServiceException e) {
            LOG.error("MenuController.removeMenu ServiceException, inputParam [{}]", menuVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("MenuController.removeMenu Exception, inputParam [{}]", menuVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DELETE_SUCCESS);
    }

    /**
     * @param menuList
     * @param roleMenuList
     */
    private void convertToRoleMenu(List<Menu> menuList, List<RoleMenu> roleMenuList) {
        if (CollectionUtil.isNullOrEmpty(menuList)) {
            return;
        }
        List<RoleMenu> subRoleMenuList = new ArrayList<RoleMenu>();
        menuList.stream().forEach(menu -> {
            List<Menu> subMenuList = menu.getChildMenu();
            convertToRoleMenu(subMenuList, subRoleMenuList);

            RoleMenu roleMenu = this.getRoleMenu(menu);
            if (menu.getLevel().intValue() == Enums.MenuLevel.ONE_LEVEL.getValue()) {
                roleMenu.setAlert(String.valueOf(subMenuList.size()));
            }
            if (CollectionUtil.isNotEmpty(subRoleMenuList) && menu.getLevel().intValue() < Enums.MenuLevel.TWO_LEVEL.getValue()) {
                roleMenu.setSubmenu(subRoleMenuList);
            }
            roleMenuList.add(roleMenu);
        });

    }

    /**
     * 通过menu过得roleMenu
     *
     * @param menu
     * @return
     */
    private RoleMenu getRoleMenu(Menu menu) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setText(menu.getMenuName());
        roleMenu.setSref(menu.getUrl());
        if (StringUtil.isNotBlank(menu.getIcon())) {
            roleMenu.setIcon(menu.getIcon());
        }
        if (StringUtil.isNotBlank(menu.getLabel()) && menu.getLevel().intValue() == Enums.MenuLevel.ONE_LEVEL.getValue()) {
            roleMenu.setLabel(menu.getLabel());
        }
        if (StringUtil.isNotBlank(menu.getTranslate())) {
            roleMenu.setTranslate(menu.getTranslate());
        }

        return roleMenu;
    }
}
