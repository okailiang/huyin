package press.wein.home.dao;

import press.wein.home.model.Printer;
import press.wein.home.model.Printer;

import java.util.List;
import java.util.Map;

public interface PrinterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Printer record);

    int insertSelective(Printer record);

    Printer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Printer record);

    int updateByPrimaryKey(Printer record);

    long countPrinters(Map<String, Object> paramMap);

    List<Printer> listPrintersByPage(Map<String, Object> paramMap);

    List<Printer> listPrinters(Printer record);

    List<Printer> listPrintersByIds(List<Long> printerIds);

    int checkRepeatName(Printer printer);

    Printer getPrinterByNameOrEmailOrPhone(Map<String, Object> paramMap);
}