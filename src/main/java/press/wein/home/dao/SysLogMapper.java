package press.wein.home.dao;

import press.wein.home.model.SysLog;
import press.wein.home.model.SysLogWithBLOBs;

public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLogWithBLOBs record);

    int insertSelective(SysLogWithBLOBs record);

    SysLogWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysLogWithBLOBs record);

    int updateByPrimaryKey(SysLog record);
}