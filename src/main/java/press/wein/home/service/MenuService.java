package press.wein.home.service;

import press.wein.home.common.Page;
import press.wein.home.exception.ServiceException;
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
    int saveMenu(MenuVo menuVo) throws ServiceException;

    /**
     * 更新菜单信息
     *
     * @param menuVo
     * @return
     */
    int updateMenu(MenuVo menuVo) throws ServiceException;

    /**
     * 根据菜单id逻辑查询
     *
     * @param id
     * @return
     */
    int removeMenu(Integer id) throws ServiceException;

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    Menu getMenuById(Integer id) throws ServiceException;

    /**
     * 根据查询条件分页查询
     *
     * @param menuVo
     * @return
     */
    Page<MenuVo> listMenusWithPage(Page<MenuVo> page, MenuVo menuVo) throws ServiceException;

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
    List<MenuVo> listMenus(MenuVo menuVo) throws ServiceException;

    /**
     * 根据roleId查询
     *
     * @param roleId
     * @return
     */
    List<MenuVo> listMenusByRoleId(Integer roleId) throws ServiceException;

    /**
     * 根据菜单id列表查询
     *
     * @param ids
     * @return
     */
    List<MenuVo> listMenusByIds(List<Integer> ids) throws ServiceException;
}
