package press.wein.home.dao;

import press.wein.home.model.ChinaAddress;

public interface ChinaAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChinaAddress record);

    int insertSelective(ChinaAddress record);

    ChinaAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChinaAddress record);

    int updateByPrimaryKey(ChinaAddress record);
}