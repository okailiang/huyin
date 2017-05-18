package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.dao.MenuMapper;
import press.wein.home.model.Menu;
import press.wein.home.model.vo.MenuVo;
import press.wein.home.service.MenuService;

import java.util.List;

/**
 * 菜单service实现层
 *
 * @author oukailiang
 * @create 2017-03-06 下午7:55
 */
@Service(value = "menuService")
public class MenuServiceImpl implements MenuService {

    private static final Logger LOG = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public String saveMenu(MenuVo menuVo) {
        return null;
    }

    @Override
    public String updateMenu(MenuVo menuVo) {
        return null;
    }

    @Override
    public String removeMenuById(long id) {
        return null;
    }

    @Override
    public Menu getMenuByMenuName(Menu menu) {
        return null;
    }

    @Override
    public Menu getMenuById(String id) {
        return null;
    }

    @Override
    public Menu getMenu(MenuVo menuVo) {
        return null;
    }

    @Override
    public Page<MenuVo> listMenusWithPage(Page<MenuVo> page, MenuVo menuVo) {
        return null;
    }

    @Override
    public List<Menu> listAllMenus() {
        return menuMapper.listAllMenus();
    }

    @Override
    public List<Menu> listMenus(MenuVo menuVo) {
        return null;
    }

    @Override
    public List<Menu> listMenusByRoleId(int roleId) {
        return null;
    }

    @Override
    public List<Menu> listMenusByIds(List<Long> ids) {
        return null;
    }
}
