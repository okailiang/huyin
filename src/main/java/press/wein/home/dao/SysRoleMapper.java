package press.wein.home.dao;

import press.wein.home.model.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    long countRoles(Map<String, Object> paramMap);

    List<SysRole> listRolesByPage(Map<String, Object> paramMap);

    List<SysRole> listRoles(SysRole record);

    List<SysRole> listRolesByRoleIds(List<Long> roleIds);

    int deleteRole(Long roleId);

}