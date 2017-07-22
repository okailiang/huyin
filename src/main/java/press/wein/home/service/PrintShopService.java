package press.wein.home.service;

import press.wein.home.common.Page;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.vo.PrintShopVo;

import java.util.List;

/**
 * 打印店信息
 *
 * @author oukailiang
 * @create 2017-07-20 下午7:08
 */
public interface PrintShopService {

    /**
     * 保存打印店信息
     *
     * @param printShopVo
     * @return
     */
    int savePrintShop(PrintShopVo printShopVo) throws ServiceException;

    /**
     * 更新打印店信息
     *
     * @param printShopVo
     * @return
     */
    int updatePrintShop(PrintShopVo printShopVo) throws ServiceException;

    /**
     * 禁用打印店
     *
     * @param printShopVo
     * @return
     * @throws ServiceException
     */
    int enablePrintShop(PrintShopVo printShopVo) throws ServiceException;

    /**
     * 禁用打印店
     *
     * @param printShopVo
     * @return
     * @throws ServiceException
     */
    int disablePrintShop(PrintShopVo printShopVo) throws ServiceException;

    /**
     * 根据打印店id逻辑查询
     *
     * @param printShopVo
     * @return
     */
    int removePrintShop(PrintShopVo printShopVo) throws ServiceException;

    /**
     * 根据名称查询打印店
     *
     * @param printShopVo
     * @return
     */
    PrintShopVo getPrintShopByName(PrintShopVo printShopVo) throws ServiceException;

    /**
     * 获得PrintShop
     *
     * @param id
     * @return
     */
    PrintShopVo getPrintShop(Long id) throws ServiceException;


    /**
     * 根据查询条件分页查询
     *
     * @param printShopVo
     * @return
     */
    Page<PrintShopVo> listPrintShopsWithPage(Page<PrintShopVo> page, PrintShopVo printShopVo) throws ServiceException;

    /**
     * 根据查询条件查询
     *
     * @param printShopVo
     * @return
     */
    List<PrintShopVo> listPrintShops(PrintShopVo printShopVo) throws ServiceException;

    /**
     * 根据打印店id列表查询
     *
     * @param ids
     * @return
     */
    List<PrintShopVo> listPrintShopsByIds(List<Long> ids) throws ServiceException;

    /**
     * 通过打印店主id获得打印店
     *
     * @param printerId
     * @return
     * @throws ServiceException
     */
    PrintShopVo getPrintShopByPrinterId(Long printerId) throws ServiceException;

    /**
     * 通过打印店主id列表获得打印店
     *
     * @param printerIds
     * @return
     * @throws ServiceException
     */
    List<PrintShopVo> listPrintShopsByPrinterIds(List<Long> printerIds) throws ServiceException;

    /**
     * 通过城市获得打印店
     *
     * @param areaId
     * @return
     */
    List<PrintShopVo> listPrintShopsByAreaId(Integer areaId) throws ServiceException;

    /**
     * 通过城市获得打印店
     *
     * @param areaIds
     * @return
     */
    List<PrintShopVo> listPrintShopsByAreaIds(List<Integer> areaIds) throws ServiceException;
}
