package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.constant.Constants;
import press.wein.home.dao.MenuMapper;
import press.wein.home.dao.SysRoleMapper;
import press.wein.home.dao.SysRoleMenuMapper;
import press.wein.home.enumerate.Enums;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Menu;
import press.wein.home.model.SysRoleMenu;
import press.wein.home.model.vo.MenuVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.MenuService;
import press.wein.home.service.RoleService;
import press.wein.home.util.BeanUtil;
import press.wein.home.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单service实现层
 *
 * @author oukailiang
 * @create 2017-03-06 下午7:55
 */
@Service(value = "menuService")
public class MenuServiceImpl extends BaseService implements MenuService {

    private static final Logger LOG = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int saveMenu(MenuVo menuVo) throws ServiceException {
        //校验
        this.checkParamNull(menuVo.getMenuName(), menuVo.getLevel(), menuVo.getParentId(), menuVo.getSorting());
        if (menuVo.getLevel() > Enums.MenuLevel.FOUR_LEVEL.getValue().shortValue()) {
            throw ExceptionUtil.createServiceException(ExceptionCode.MENU_LEVEL_MAX);
        }

        Menu menu = new Menu();
        BeanUtil.beanCopier(menuVo, menu);
        //检验重名
        this.checkRepeatName(menuMapper.checkRepeatName(menu));


        return menuMapper.insertSelective(menu);
    }

    @Override
    public int updateMenu(MenuVo menuVo) throws ServiceException {
        Menu menu = new Menu();
        //校验
        this.checkParamNull(menuVo.getId(), menuVo.getMenuName(), menuVo.getLevel(), menuVo.getParentId(), menuVo.getSorting());

        BeanUtil.beanCopier(menuVo, menu);
        //检验重名
        this.checkRepeatName(menuMapper.checkRepeatName(menu));

        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int removeMenu(Integer id) throws ServiceException {
        this.checkParamNull(id);

        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.listRoleMenusByMenuIds(Arrays.asList(id));
        if (CollectionUtil.isNotEmpty(roleMenus)) {
            throw ExceptionUtil.createServiceException(ExceptionCode.MENU_NO_REMOVE);
        }
        Menu menu = new Menu();
        menu.setId(id);
        menu.setIsDeleted(Constants.IS_DELETED);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<MenuVo> listMenusWithPage(Page<MenuVo> page, MenuVo menuVo) {
        return null;
    }

    @Override
    public List<Menu> listAllMenus() {
        List<Menu> menuList = menuMapper.listAllMenus();
        return this.handleMenu(menuList);
    }

    @Override
    public List<MenuVo> listMenus(MenuVo menuVo) {
        List<Menu> menuList = menuMapper.listAllMenus();
        List<MenuVo> menuVoList = new ArrayList<>();
        this.copyToMenuVoList(menuList, menuVoList);
        return menuVoList;
    }

    @Override
    public List<Menu> listMenusByRoleId(Integer roleId) throws ServiceException {
        List<Menu> menuList = menuMapper.listAllMenus();
        if (CollectionUtil.isNullOrEmpty(menuList)) {
            return new ArrayList<>();
        }
        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.listRoleMenusByRoleIds(Arrays.asList(roleId));
        if (CollectionUtil.isNotEmpty(roleMenuList)) {
            Map<Integer, SysRoleMenu> menuIdMap = roleMenuList.stream().collect(Collectors.toMap(rm -> rm.getMenuId(), rm -> rm));
            for (Menu menu : menuList) {
                if (menuIdMap.get(menu.getId()) != null) {
                    menu.setStatus((byte) 1);
                }
            }
        }
        return this.handleMenu(menuList);
    }

    @Override
    public List<MenuVo> listMenusByIds(List<Integer> ids) {
        return null;
    }

    private void copyToMenuVoList(List<Menu> menuList, List<MenuVo> menuVoList) {
        for (Menu menu : menuList) {
            MenuVo menuVo = new MenuVo();
            BeanUtil.beanCopier(menu, menuVo);
            menuVoList.add(menuVo);
        }
    }

    /*  1》获取用户对应的角色
      * 2》获取角色对应的菜单
      * 3》组装出参数据
      * a.获取父菜单
      * b.获取子菜单
      * c.组装父、子菜单
      */
    public List<Menu> handleMenu(List<Menu> menuVoList) {

        //一级菜单
        List<Menu> oneLevelMenuList = new ArrayList<>();
        List<Menu> twoLevelMenuList = new ArrayList<>();
        List<Menu> threeLevelMenuList = new ArrayList<>();
        List<Menu> fourLevelMenuList = new ArrayList<>();
        //按菜单级别分类
        menuVoList.stream().forEach(m -> {
            if (Short.toUnsignedInt(m.getLevel()) == Enums.MenuLevel.ONE_LEVEL.getValue()) {
                oneLevelMenuList.add(m);
            }
            if (Short.toUnsignedInt(m.getLevel()) == Enums.MenuLevel.TWO_LEVEL.getValue()) {
                twoLevelMenuList.add(m);
            }
            if (Short.toUnsignedInt(m.getLevel()) == Enums.MenuLevel.THREE_LEVEL.getValue()) {
                threeLevelMenuList.add(m);
            }
            if (Short.toUnsignedInt(m.getLevel()) == Enums.MenuLevel.FOUR_LEVEL.getValue()) {
                fourLevelMenuList.add(m);
            }
        });
        //将菜单按父菜单id分类
        Map<Integer, List<Menu>> twoMenuMap = twoLevelMenuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        Map<Integer, List<Menu>> threeMenuMap = threeLevelMenuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        Map<Integer, List<Menu>> fourMenuMap = fourLevelMenuList.stream().collect(Collectors.groupingBy(Menu::getParentId));

        oneLevelMenuList.stream().forEach(menu -> {
            menu.setParentMenuName("");
            List<Menu> subTwoMenu = twoMenuMap.get(menu.getId());

            if (CollectionUtil.isNotEmpty(subTwoMenu)) {
                subTwoMenu.stream().forEach(twoMenu -> {
                    twoMenu.setParentMenuName(menu.getMenuName());
                    List<Menu> subThreeMenu = threeMenuMap.get(twoMenu.getId());

                    if (CollectionUtil.isNotEmpty(subThreeMenu)) {
                        subThreeMenu.stream().forEach(threeMenu -> {
                            threeMenu.setParentMenuName(twoMenu.getMenuName());
                            List<Menu> subFourMenu = fourMenuMap.get(threeMenu.getId());

                            if (CollectionUtil.isNotEmpty(subFourMenu)) {
                                for (Menu fourMenu : subFourMenu) {
                                    fourMenu.setParentMenuName(threeMenu.getMenuName());
                                }
                                threeMenu.setChildMenu(subFourMenu);
                            }
                        });
                        twoMenu.setChildMenu(subThreeMenu);
                    }
                });
                menu.setChildMenu(subTwoMenu);
            }
        });

        //给一级菜单添加子菜单个数提示
        oneLevelMenuList.stream().forEach(oneMenu -> {
            if (CollectionUtil.isNotEmpty(oneMenu.getChildMenu())) {
                oneMenu.setAlert(String.valueOf(oneMenu.getChildMenu().size()));
                oneMenu.setLabel("label label-info");
            }
        });
        return oneLevelMenuList;
    }
}
