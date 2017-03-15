package press.wein.home.dao;

import press.wein.home.model.Printer;

public interface PrinterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Printer record);

    int insertSelective(Printer record);

    Printer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Printer record);

    int updateByPrimaryKey(Printer record);
}