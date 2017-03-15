package press.wein.home.dao;

import press.wein.home.model.PrintShop;

public interface PrintShopMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrintShop record);

    int insertSelective(PrintShop record);

    PrintShop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrintShop record);

    int updateByPrimaryKey(PrintShop record);
}