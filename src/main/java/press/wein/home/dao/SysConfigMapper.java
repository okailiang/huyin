package press.wein.home.dao;

import press.wein.home.model.SysConfig;

public interface SysConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
}