package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import press.wein.home.common.Page;
import press.wein.home.common.SysConfigProperty;
import press.wein.home.constant.Constants;
import press.wein.home.dao.PrintShopMapper;
import press.wein.home.dao.PrinterMapper;
import press.wein.home.enumerate.Enums;
import press.wein.home.enumerate.PrintShopEnum;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.PrintShop;
import press.wein.home.model.Printer;
import press.wein.home.model.vo.CityVo;
import press.wein.home.model.vo.PrintShopVo;
import press.wein.home.model.vo.PrinterVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.CityService;
import press.wein.home.service.PrintShopService;
import press.wein.home.service.PrinterService;
import press.wein.home.util.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 打印店
 *
 * @author oukailiang
 * @create 2017-07-20 下午7:09
 */
@Service(value = "printShopService")
public class PrintShopServiceImpl extends BaseService implements PrintShopService {
    public static final Logger LOG = LoggerFactory.getLogger(PrintShopServiceImpl.class);

    @Autowired
    private PrintShopMapper printShopMapper;

    @Autowired
    private PrinterMapper printerMapper;

    @Autowired
    private PrinterService printerService;

    @Autowired
    private CityService cityService;

    /**
     * 保存打印店信息
     *
     * @param printShopVo
     * @return
     */
    @Override
    public int savePrintShop(PrintShopVo printShopVo) throws ServiceException {
        //check param
        checkParamNull(printShopVo.getCityAreaId(), printShopVo.getPhoneNo(), printShopVo.getAddress(), printShopVo.getLatitude(),
                printShopVo.getLongitude(), printShopVo.getPrinterName(), printShopVo.getPrintShopImage(), printShopVo.getAddressType(),
                printShopVo.getShopImageFile());
        this.setAddress(printShopVo);
        //创建该打印店账号
        PrintShop printShop = new PrintShop();
        BeanUtil.beanCopier(printShopVo, printShop);
        //检验重名
        this.checkRepeatName(printShopMapper.checkRepeatName(printShop));

        //存储图片
        this.saveShopImageFile(printShopVo);

        //保存账号
        PrinterVo printerVo = new PrinterVo();

        BeanUtil.beanCopier(printShopVo, printerVo);
        printerVo.setPhoneNo(printShopVo.getAccountPhone());
        long printerId = printerService.savePrinter(printerVo);
        printShop.setPrinterId(printerId);
        printShop.setPrinterName(printerVo.getUserName());
        //
        CityVo cityVo = cityService.getCityById(printShopVo.getCityAreaId());

        return printShopMapper.insertSelective(printShop);
    }

    /**
     * 更新打印店信息
     *
     * @param printShopVo
     * @return
     */
    @Override
    public int updatePrintShop(PrintShopVo printShopVo) throws ServiceException {
        //check param
        checkParamNull(printShopVo.getId(), printShopVo.getCityAreaId(), printShopVo.getPhoneNo(), printShopVo.getAddress(), printShopVo.getLatitude(),
                printShopVo.getLongitude(), printShopVo.getPrinterName(), printShopVo.getPrintShopImage(), printShopVo.getAddressType());
        if (printShopVo.getShopImageFile() != null) {
            this.saveShopImageFile(printShopVo);
        }

        PrintShop printShop = printShopMapper.selectByPrimaryKey(printShopVo.getId());
        if (printShop == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NOT_EXIST);
        }
        this.setAddress(printShopVo);
        BeanUtil.beanCopier(printShopVo, printShop);

        //检验重名
        this.checkRepeatName(printShopMapper.checkRepeatName(printShop));

        //保存账号
        PrinterVo printerVo = new PrinterVo();
        BeanUtil.beanCopier(printShopVo, printerVo);
        printerVo.setPhoneNo(printShopVo.getAccountPhone());
        printerService.updatePrinter(printerVo);
        return printShopMapper.updateByPrimaryKeySelective(printShop);
    }

    /**
     * 禁用打印店
     *
     * @param printShopVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int enablePrintShop(PrintShopVo printShopVo) throws ServiceException {
        //check param
        checkParamNull(printShopVo.getId(), printShopVo.getStatus());
        if (Enums.Status.ALLOW.getValue() != printShopVo.getStatus().intValue()) {
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        PrintShop printShop = new PrintShop();
        BeanUtil.beanCopier(printShopVo, printShop);
        return printShopMapper.updateByPrimaryKeySelective(printShop);
    }

    /**
     * 禁用打印店
     *
     * @param printShopVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int disablePrintShop(PrintShopVo printShopVo) throws ServiceException {
        //check param
        checkParamNull(printShopVo.getId(), printShopVo.getStatus());
        if (Enums.Status.DENY.getValue() != printShopVo.getStatus().intValue()) {
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        PrintShop printShop = new PrintShop();
        BeanUtil.beanCopier(printShopVo, printShop);
        return printShopMapper.updateByPrimaryKeySelective(printShop);
    }

    /**
     * 根据打印店id逻辑查询
     *
     * @param printShopVo
     * @return
     */
    @Override
    public int removePrintShop(PrintShopVo printShopVo) throws ServiceException {
        this.checkParamNull(printShopVo, printShopVo.getId());
        PrintShop printShop = new PrintShop();
        BeanUtil.beanCopier(printShopVo, printShop);
        printShop.setIsDeleted(Constants.IS_DELETED);
        return printShopMapper.updateByPrimaryKeySelective(printShop);
    }

    /**
     * 根据名称查询打印店
     *
     * @param printShopVo
     * @return
     */
    @Override
    public PrintShopVo getPrintShopByName(PrintShopVo printShopVo) throws ServiceException {
        if (printShopVo == null || CommonUtil.isAllBlank(printShopVo.getPrintShopName())) {
            return null;
        }
        PrintShop printShop = printShopMapper.getPrintShopByName(printShopVo.getPrintShopName());
        if (printShop == null) {
            return null;
        }
        BeanUtil.beanCopier(printShop, printShopVo);
        return printShopVo;
    }

    /**
     * 获得PrintShop
     *
     * @param id
     * @return
     */
    @Override
    public PrintShopVo getPrintShop(Long id) throws ServiceException {
        checkParamNull(id);
        PrintShop printShop = printShopMapper.selectByPrimaryKey(id);
        if (printShop == null) {
            return null;
        }
        PrintShopVo printShopVo = new PrintShopVo();
        BeanUtil.beanCopier(printShop, printShopVo);

        this.buildPrintShopVo(printShopVo);

        PrinterVo printerVo = printerService.getPrinter(printShopVo.getPrinterId());
        if (printerVo != null) {
            this.setPrinterVo(printShopVo, printerVo);
        }
        return printShopVo;
    }

    /**
     * 根据查询条件分页查询
     *
     * @param printShopVo
     * @return
     */
    @Override
    public Page<PrintShopVo> listPrintShopsWithPage(Page<PrintShopVo> page, PrintShopVo printShopVo) throws ServiceException {
        //查询条件
        Map<String, Object> searchParam = CollectionUtil.objectToMap(printShopVo);
        //分页参数
        setPageParam(searchParam, page);

        List<PrintShopVo> printShopVoList = new ArrayList<>();
        long totalCount = printShopMapper.countPrintShops(searchParam);
        if (totalCount <= 0) {
            return new Page<>(0, printShopVoList);
        }
        List<PrintShop> printShopList = printShopMapper.listPrintShopsByPage(searchParam);
        if (CollectionUtil.isNotEmpty(printShopList)) {
            printShopVoList = CollectionUtil.copyToDescList(printShopList, PrintShopVo.class);
        }
        for (PrintShopVo shopVo : printShopVoList) {
            this.buildPrintShopVo(shopVo);
        }
        return new Page<>(totalCount, printShopVoList);
    }

    /**
     * 根据查询条件查询
     *
     * @param printShopVo
     * @return
     */
    @Override
    public List<PrintShopVo> listPrintShops(PrintShopVo printShopVo) throws ServiceException {
        if (printShopVo == null) {
            return new ArrayList<>();
        }
        PrintShop printShop = new PrintShop();
        BeanUtil.beanCopier(printShopVo, printShop);

        List<PrintShop> printShopList = printShopMapper.listPrintShops(printShop);

        return CollectionUtil.copyToDescList(printShopList, PrintShopVo.class);
    }

    /**
     * 根据打印店id列表查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<PrintShopVo> listPrintShopsByIds(List<Long> ids) throws ServiceException {
        if (CollectionUtil.isNullOrEmpty(ids)) {
            return new ArrayList<>();
        }
        List<PrintShop> printShopList = printShopMapper.listPrintShopsByIds(ids);
        return CollectionUtil.copyToDescList(printShopList, PrintShopVo.class);
    }

    /**
     * 通过打印店主id获得打印店
     *
     * @param printerId
     * @return
     * @throws ServiceException
     */
    @Override
    public PrintShopVo getPrintShopByPrinterId(Long printerId) throws ServiceException {
        this.checkParamNull(printerId);

        PrintShop printShop = printShopMapper.getPrintShopByPrinterId(printerId);
        if (printShop == null) {
            return null;
        }
        PrintShopVo printShopVo = new PrintShopVo();
        BeanUtil.beanCopier(printShop, printShopVo);
        return printShopVo;
    }

    /**
     * 通过打印店主id列表获得打印店
     *
     * @param printerIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<PrintShopVo> listPrintShopsByPrinterIds(List<Long> printerIds) throws ServiceException {
        if (CollectionUtil.isNullOrEmpty(printerIds)) {
            return Collections.emptyList();
        }
        List<PrintShop> printShopList = printShopMapper.listPrintShopsByPrinterIds(printerIds);
        return CollectionUtil.copyToDescList(printShopList, PrintShopVo.class);
    }

    /**
     * 通过城市获得打印店
     *
     * @param areaId
     * @return
     */
    @Override
    public List<PrintShopVo> listPrintShopsByAreaId(Integer areaId) throws ServiceException {
        if (areaId == null || areaId <= 0) {
            return Collections.emptyList();
        }
        List<PrintShop> printShopList = printShopMapper.listPrintShopsByAreaId(areaId);
        return CollectionUtil.copyToDescList(printShopList, PrintShopVo.class);
    }

    /**
     * 通过城市获得打印店
     *
     * @param areaIds
     * @return
     */
    @Override
    public List<PrintShopVo> listPrintShopsByAreaIds(List<Integer> areaIds) throws ServiceException {
        if (CollectionUtil.isNullOrEmpty(areaIds)) {
            return Collections.emptyList();
        }
        List<PrintShop> printShopList = printShopMapper.listPrintShopsByAreaIds(areaIds);
        return CollectionUtil.copyToDescList(printShopList, PrintShopVo.class);
    }

    private void setPrinterVo(PrintShopVo printShopVo, PrinterVo printerVo) {
        printShopVo.setPrinterId(printerVo.getId());
        printShopVo.setUserName(printerVo.getUserName());
        printShopVo.setEmail(printerVo.getEmail());
        printShopVo.setAccountPhone(printerVo.getPhoneNo());
        printShopVo.setAccountStatus(printerVo.getStatus());
        printShopVo.setRole(printerVo.getRole());
    }

    private void setAddress(PrintShopVo printShopVo) throws ServiceException {
        CityVo cityVo = cityService.getCityById(printShopVo.getCityAreaId());
        if (cityVo == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.CITY_ID_ERROR);
        }
        String provinceCityArea = cityVo.getProvinceCityArea().replaceAll("-", "");
        printShopVo.setAddress(provinceCityArea + " " + printShopVo.getDetailAddress());

    }

    private void buildPrintShopVo(PrintShopVo printShopVo) throws ServiceException {
        printShopVo.setStatusDesc(Enums.Status.getNameByValue(printShopVo.getStatus()));
        printShopVo.setAddressTypeDesc(PrintShopEnum.Type.getNameByValue(printShopVo.getAddressType()));
        //城市
        CityVo cityVo = cityService.getCityById(printShopVo.getCityAreaId());
        if (cityVo != null) {
            printShopVo.setCityName(cityVo.getFullName());
            printShopVo.setProvinceCityArea(cityVo.getProvinceCityArea());
        }
    }

    private void saveShopImageFile(PrintShopVo printShopVo) throws ServiceException {
        MultipartFile shopImageFile = printShopVo.getShopImageFile();
        long shopImageSize = shopImageFile.getSize();

        // 大于2M图片不允许上传
        if (shopImageSize > (2 * (long) 1073741824)) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PRINTSHOP_IMAGE_MAX);
        }

        String shopImagePath = FileUtil.getShopImagePath(null);
        printShopVo.setPrintShopImage(shopImagePath);
        //绝对路径
        shopImagePath = SysConfigProperty.getProperty(Constants.WEIN_FILEPATH) + shopImagePath;
        try {
            File outFile = new File(shopImagePath);
            if (!outFile.exists()) {
                outFile.getParentFile().mkdirs();
                outFile.createNewFile();
            }
            // 将图片拷贝到服务器
            shopImageFile.transferTo(outFile);
        } catch (IOException e) {
            LOG.info("PrintShopServiceImpl.saveShopImageFile IOException inputParam =[{}]", printShopVo.toString(), e);
        }
    }
}
