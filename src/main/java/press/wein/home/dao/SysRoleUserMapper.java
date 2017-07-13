package press.wein.home.dao;

import press.wein.home.model.SysRoleUser;

import java.util.List;

public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);

    List<SysRoleUser> listRoleUsersByRoleIds(List<Long> roleIds);

    List<SysRoleUser> listRoleUsersByUserId(List<Long> userIds);

    SysRoleUser getRoleUserByUserId(Long userId);

    int removeRoleUser(Long id);
}