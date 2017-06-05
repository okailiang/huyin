package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.dao.MenuMapper;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Menu;
import press.wein.home.model.vo.MenuVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.MenuService;
import press.wein.home.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public int saveMenu(MenuVo menuVo) throws ServiceException {
        //校验
        this.checkParamNull(menuVo.getMenuName(), menuVo.getLevel(), menuVo.getParentId(), menuVo.getSorting());
        Menu menu = new Menu();
        BeanUtil.beanCopier(menuVo, menu);
        return menuMapper.insertSelective(menu);
    }

    @Override
    public int updateMenu(MenuVo menuVo) throws ServiceException {
        //校验
        this.checkParamNull(menuVo.getId(), menuVo.getMenuName(), menuVo.getLevel(), menuVo.getParentId(), menuVo.getSorting());
        Menu menu = new Menu();
        BeanUtil.beanCopier(menuVo, menu);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int removeMenuById(Integer id) throws ServiceException {
        this.checkParamNull(id);
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public MenuVo getMenuById(Integer id) {
        return null;
    }

    @Override
    public Page<MenuVo> listMenusWithPage(Page<MenuVo> page, MenuVo menuVo) {
        return null;
    }

    @Override
    public List<Menu> listAllMenus() {
//        List<Menu> menuList = menuMapper.listAllMenus();
//        List<MenuVo> menuVoList = new ArrayList<>();
//        this.copyToMenuVoList(menuList, menuVoList);
        return  menuMapper.listAllMenus();
    }

    @Override
    public List<MenuVo> listMenus(MenuVo menuVo) {
        List<Menu> menuList = menuMapper.listAllMenus();
        List<MenuVo> menuVoList = new ArrayList<>();
        this.copyToMenuVoList(menuList, menuVoList);
        return menuVoList;
    }

    @Override
    public List<MenuVo> listMenusByRoleId(Integer roleId) {
        return null;
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
}
