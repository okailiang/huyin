package press.wein.home.dao;

import press.wein.home.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    long countSysUsers(Map<String, Object> paramMap);

    List<SysUser> listSysUsersByPage(Map<String, Object> paramMap);

    List<SysUser> listSysUsers(SysUser record);

    List<SysUser> listSysUsersByIds(List<Long> sysUserIds);

    int checkRepeatName(SysUser sysUser);
}