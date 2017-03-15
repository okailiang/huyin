package press.wein.home.service;

import press.wein.home.common.Paper;
import press.wein.home.model.Menu;
import press.wein.home.model.vo.MenuVo;

import java.util.List;

/**
 * 菜单service层
 *
 * @author oukailiang
 * @create 2017-03-06 下午7:54
 */

public interface MenuService {

    /**
     * 保存菜单信息
     *
     * @param menuVo
     * @return
     */
    String saveMenu(MenuVo menuVo);

    /**
     * 更新菜单信息
     *
     * @param menuVo
     * @return
     */
    String updateMenu(MenuVo menuVo);

    /**
     * 根据菜单id逻辑查询
     *
     * @param id
     * @return
     */
    String removeMenuById(long id);

    /**
     * 根据名称查询菜单
     *
     * @param menu
     * @return
     */
    Menu getMenuByMenuName(Menu menu);

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    Menu getMenuById(String id);

    /**
     * 根据条件查询菜单
     *
     * @param menuVo
     * @return
     */
    Menu getMenu(MenuVo menuVo);

    /**
     * 根据查询条件分页查询
     *
     * @param menuVo
     * @return
     */
    Paper<MenuVo> listMenusWithPage(Paper<MenuVo> paper, MenuVo menuVo);

    /**
     * 获得所有的菜单
     *
     * @return
     */
    List<Menu> listAllMenus();

    /**
     * 根据查询条件查询
     *
     * @param menuVo
     * @return
     */
    List<Menu> listMenus(MenuVo menuVo);

    /**
     * 根据roleId查询
     *
     * @param roleId
     * @return
     */
    List<Menu> listMenusByRoleId(int roleId);

    /**
     * 根据菜单id列表查询
     *
     * @param ids
     * @return
     */
    List<Menu> listMenusByIds(List<Long> ids);
}
