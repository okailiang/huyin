package press.wein.home.service;

import press.wein.home.common.Page;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Printer;
import press.wein.home.model.vo.PrinterVo;

import java.util.List;

/**
 * 打印管理员
 *
 * @author oukailiang
 * @create 2017-07-20 下午9:51
 */
public interface PrinterService {

    /**
     * 保存打印管理员信息
     *
     * @param printerVo
     * @return
     */
    long savePrinter(PrinterVo printerVo) throws ServiceException;

    /**
     * 更新打印管理员信息
     *
     * @param printerVo
     * @return
     */
    int updatePrinter(PrinterVo printerVo) throws ServiceException;

    /**
     * 禁用打印管理员
     *
     * @param printerVo
     * @return
     * @throws ServiceException
     */
    int enablePrinter(PrinterVo printerVo) throws ServiceException;

    /**
     * 禁用打印管理员
     *
     * @param printerVo
     * @return
     * @throws ServiceException
     */
    int disablePrinter(PrinterVo printerVo) throws ServiceException;

    /**
     * 重置密码
     *
     * @param printerVo
     * @return
     * @throws ServiceException
     */
    int resetPassword(PrinterVo printerVo) throws ServiceException;

    /**
     * 根据打印管理员id逻辑查询
     *
     * @param printerVo
     * @return
     */
    int removePrinter(PrinterVo printerVo) throws ServiceException;

    /**
     * 根据名称查询打印管理员
     *
     * @param printerVo
     * @return
     */
    PrinterVo getPrinterByNameOrEmailOrPhone(PrinterVo printerVo) throws ServiceException;

    /**
     * 获得Printer
     *
     * @param id
     * @return
     */
    PrinterVo getPrinter(Long id) throws ServiceException;


    /**
     * 根据查询条件分页查询
     *
     * @param printerVo
     * @return
     */
    Page<PrinterVo> listPrintersWithPage(Page<PrinterVo> page, PrinterVo printerVo) throws ServiceException;

    /**
     * 根据查询条件查询
     *
     * @param printerVo
     * @return
     */
    List<Printer> listPrinters(PrinterVo printerVo) throws ServiceException;

    /**
     * 根据打印管理员id列表查询
     *
     * @param ids
     * @return
     */
    List<Printer> listPrintersByIds(List<Long> ids) throws ServiceException;
}
