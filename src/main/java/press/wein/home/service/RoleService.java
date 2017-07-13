package press.wein.home.service;

import press.wein.home.common.Page;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Menu;
import press.wein.home.model.SysRole;
import press.wein.home.model.SysRoleMenu;
import press.wein.home.model.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * 角色service层
 *
 * @author oukailiang
 * @create 2017-05-16 上午12:33
 */

public interface RoleService {

    /**
     * 保存角色
     *
     * @param roleVo
     * @return
     * @throws ServiceException
     */
    int saveRole(RoleVo roleVo) throws ServiceException;

    /**
     * 更新角色信息
     *
     * @param roleVo
     * @return
     * @throws ServiceException
     */
    int updateRole(RoleVo roleVo) throws ServiceException;

    RoleVo getRoleByRoleId(Integer roleId) throws ServiceException;

    /**
     *  通过用户id获得角色
     * @param userId
     * @return
     * @throws ServiceException
     */
    RoleVo getRoleByUserId(Long userId) throws ServiceException;

    /**
     * 分页查询
     *
     * @param page
     * @param record
     * @return
     * @throws ServiceException
     */
    Page<RoleVo> listRolesByPage(Page<RoleVo> page, RoleVo record) throws ServiceException;

    /**
     * 根据条件查询列表
     *
     * @param roleVo
     * @return
     * @throws ServiceException
     */
    List<RoleVo> listRoles(RoleVo roleVo) throws ServiceException;

    /**
     * 通过roleId获得role对应的菜单关系
     *
     * @param roleId
     * @return
     * @throws ServiceException
     */
    Map<Integer, List<SysRoleMenu>> getRoleIdToMenusMap(Integer roleId) throws ServiceException;

    /**
     * 根据id列表查询列表
     *
     * @param roleIds
     * @return
     * @throws ServiceException
     */
    List<RoleVo> listRolesByIds(List<Integer> roleIds) throws ServiceException;

    /**
     * 根据id逻辑删除
     *
     * @param roleId
     * @return
     */
    int removeRole(Integer roleId) throws ServiceException;

    /**
     * 给角色添加菜单权限
     *
     * @param roleVo
     * @return
     */
    int saveMenuPermission(RoleVo roleVo) throws ServiceException;

    /**
     * 取消角色菜单权限
     *
     * @param roleVo
     * @return
     */
    int removeMenuPermission(RoleVo roleVo) throws ServiceException;
}

