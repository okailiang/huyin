package press.wein.home.dao;

import press.wein.home.model.PrintShop;

import java.util.List;
import java.util.Map;

public interface PrintShopMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrintShop record);

    int insertSelective(PrintShop record);

    PrintShop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrintShop record);

    int updateByPrimaryKey(PrintShop record);

    long countPrintShops(Map<String, Object> paramMap);

    List<PrintShop> listPrintShopsByPage(Map<String, Object> paramMap);

    List<PrintShop> listPrintShops(PrintShop printShop);

    List<PrintShop> listPrintShopsByIds(List<Long> printShopIds);

    int checkRepeatName(PrintShop printShop);

    PrintShop getPrintShopByName(String printShopName);

    PrintShop getPrintShopByPrinterId(Long printerId);

    List<PrintShop> listPrintShopsByPrinterIds(List<Long> printerIds);

    List<PrintShop> listPrintShopsByAreaId(Integer areaId);

    List<PrintShop> listPrintShopsByAreaIds(List<Integer> areaIds);
}