package press.wein.home.dao;

import press.wein.home.model.PrintShopSetting;

public interface PrintShopSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrintShopSetting record);

    int insertSelective(PrintShopSetting record);

    PrintShopSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrintShopSetting record);

    int updateByPrimaryKey(PrintShopSetting record);
}