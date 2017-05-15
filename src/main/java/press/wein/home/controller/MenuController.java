package press.wein.home.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.constant.Constants;
import press.wein.home.enumerate.Enums;
import press.wein.home.model.Menu;
import press.wein.home.model.bo.RoleMenu;
import press.wein.home.redis.RedisClient;
import press.wein.home.util.CollectionUtil;
import press.wein.home.util.StringUtil;

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
public class MenuController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private RedisClient redisClient;

    /**
     * 通过角色获取菜单
     *
     * @return
     */
    @RequestMapping(value = "/getRoleMenu", method = RequestMethod.GET)
    @ResponseBody
    private String getMenuByRole() {
        List<RoleMenu> roleMenuList = new ArrayList<>();
        RoleMenu mainRoleMenu = new RoleMenu();
        mainRoleMenu.setHeading("true");
        mainRoleMenu.setText("主导航栏");
        mainRoleMenu.setTranslate("sidebar.heading.HEADER");
        roleMenuList.add(mainRoleMenu);

        List<Menu> menuList = ApplicationUserContext.getUser().getMenuList();
        if (CollectionUtil.isNotEmpty(menuList)) {
            convertToRoleMenu(menuList, roleMenuList);
            return JSON.toJSONString(roleMenuList);
        }
        String roleMenu = redisClient.get(Constants.CACHE_MENU_ROLE);
        return roleMenu;
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

            RoleMenu roleMenu = getRoleMenu(menu);
            if (menu.getLevel().intValue() == Enums.MenuLevel.ONE_LEVEL.getValue()) {
                roleMenu.setAlert(String.valueOf(subMenuList.size()));
            }
            if (CollectionUtil.isNotEmpty(subRoleMenuList)) {
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
