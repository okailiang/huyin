package press.wein.home.dao;

import press.wein.home.model.PrintShopNotice;

public interface PrintShopNoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrintShopNotice record);

    int insertSelective(PrintShopNotice record);

    PrintShopNotice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrintShopNotice record);

    int updateByPrimaryKey(PrintShopNotice record);
}