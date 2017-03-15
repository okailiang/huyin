package press.wein.home.dao;

import press.wein.home.model.OrderFile;

public interface OrderFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderFile record);

    int insertSelective(OrderFile record);

    OrderFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderFile record);

    int updateByPrimaryKey(OrderFile record);
}