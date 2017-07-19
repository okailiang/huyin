package press.wein.home.dao;

import org.apache.ibatis.annotations.Param;
import press.wein.home.model.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    List<SysRoleMenu> listRoleMenusByRoleIds(List<Integer> roleIds);

    List<SysRoleMenu> listRoleMenusByMenuIds(List<Integer> menuIds);

    SysRoleMenu getRoleMenuByRoleIdAndMenuId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    int removeMenuRole(Long id);
}