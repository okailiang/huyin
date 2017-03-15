package press.wein.home.dao;

import press.wein.home.model.Price;

public interface PriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
}