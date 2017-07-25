package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.constant.Constants;
import press.wein.home.dao.PrinterMapper;
import press.wein.home.dao.SysRoleUserMapper;
import press.wein.home.enumerate.Enums;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Printer;
import press.wein.home.model.vo.PrinterVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.PrinterService;
import press.wein.home.service.RoleService;
import press.wein.home.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author oukailiang
 * @create 2017-07-20 下午10:11
 */
@Service(value = "printerService")
public class PrinterServiceImpl extends BaseService implements PrinterService {

    public static final Logger LOG = LoggerFactory.getLogger(PrintShopServiceImpl.class);

    @Autowired
    private PrinterMapper printerMapper;

    /**
     * 保存打印店管理员信息
     *
     * @param printerVo
     * @return 返回id
     */
    @Override
    public long savePrinter(PrinterVo printerVo) throws ServiceException {
        //check param
        checkParamNull(printerVo.getEmail(), printerVo.getPhoneNo(), printerVo.getUserName(), printerVo.getPassword());

        if (!PasswordUtil.checkPassword(printerVo.getPassword())) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PASSWORD_RULE);
        }

        Printer printer = new Printer();
        BeanUtil.beanCopier(printerVo, printer);
        //检验重名
        this.checkRepeat(printer);
        printer.setRole(Enums.UserRole.PRINTER.getValue().byteValue());
        printer.setPassword(MD5Util.md5Hex(printer.getPassword()));
        printerMapper.insertSelective(printer);
        return printer.getId();
    }

    /**
     * 更新打印店管理员信息
     *
     * @param printerVo
     * @return
     */
    @Override
    public int updatePrinter(PrinterVo printerVo) throws ServiceException {
        //check param
        checkParamNull(printerVo.getId(), printerVo.getEmail(), printerVo.getPhoneNo());

        Printer printer = printerMapper.selectByPrimaryKey(printerVo.getId());
        if (printer == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NOT_EXIST);
        }
        BeanUtil.beanCopier(printerVo, printer);

        //检验重名
        this.checkRepeat(printer);
        return printerMapper.updateByPrimaryKeySelective(printer);
    }

    /**
     * 禁用打印店管理员
     *
     * @param printerVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int enablePrinter(PrinterVo printerVo) throws ServiceException {
        //check param
        checkParamNull(printerVo.getId(), printerVo.getStatus());
        if (Enums.Status.ALLOW.getValue() != printerVo.getStatus().intValue()) {
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        Printer printer = new Printer();
        BeanUtil.beanCopier(printerVo, printer);
        return printerMapper.updateByPrimaryKeySelective(printer);
    }

    /**
     * 禁用打印店管理员
     *
     * @param printerVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int disablePrinter(PrinterVo printerVo) throws ServiceException {
        //check param
        checkParamNull(printerVo.getId(), printerVo.getStatus());
        if (Enums.Status.DENY.getValue() != printerVo.getStatus().intValue()) {
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        Printer printer = new Printer();
        BeanUtil.beanCopier(printerVo, printer);
        return printerMapper.updateByPrimaryKeySelective(printer);
    }

    /**
     * 重置密码
     *
     * @param printerVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int resetPassword(PrinterVo printerVo) throws ServiceException {
        //check param
        checkParamNull(printerVo.getId(), printerVo.getPassword());

        Printer printer = new Printer();
        BeanUtil.beanCopier(printerVo, printer);
        printer.setPassword(MD5Util.md5Hex(printerVo.getPassword()));
        return printerMapper.updateByPrimaryKeySelective(printer);
    }

    /**
     * 根据打印店管理员id逻辑查询
     *
     * @param printerVo
     * @return
     */
    @Override
    public int removePrinter(PrinterVo printerVo) throws ServiceException {
        this.checkParamNull(printerVo, printerVo.getId());
        Printer printer = new Printer();
        BeanUtil.beanCopier(printerVo, printer);
        printer.setIsDeleted(Constants.IS_DELETED);
        return printerMapper.updateByPrimaryKeySelective(printer);
    }

    /**
     * 根据名称查询打印店管理员
     *
     * @param printerVo
     * @return
     */
    @Override
    public PrinterVo getPrinterByNameOrEmailOrPhone(PrinterVo printerVo) throws ServiceException {
        if (printerVo == null || CommonUtil.isAllBlank(printerVo.getUserName(), printerVo.getEmail(), printerVo.getPhoneNo())) {
            return null;
        }
        Printer printer = printerMapper.getPrinterByNameOrEmailOrPhone(CollectionUtil.objectToMap(printerVo));
        if (printer == null) {
            return null;
        }
        BeanUtil.beanCopier(printer, printerVo);
        return printerVo;
    }

    /**
     * 获得Printer
     *
     * @param id
     * @return
     */
    @Override
    public PrinterVo getPrinter(Long id) throws ServiceException {
        checkParamNull(id);
        Printer printer = printerMapper.selectByPrimaryKey(id);
        if (printer == null) {
            return null;
        }
        PrinterVo printerVo = new PrinterVo();
        BeanUtil.beanCopier(printer, printerVo);
        return printerVo;
    }

    /**
     * 根据查询条件分页查询
     *
     * @param printerVo
     * @return
     */
    @Override
    public Page<PrinterVo> listPrintersWithPage(Page<PrinterVo> page, PrinterVo printerVo) throws ServiceException {
        //查询条件
        Map<String, Object> searchParam = CollectionUtil.objectToMap(printerVo);
        //分页参数
        setPageParam(searchParam, page);

        List<PrinterVo> printerVoList = new ArrayList<>();
        long totalCount = printerMapper.countPrinters(searchParam);
        if (totalCount <= 0) {
            return new Page<>(0, printerVoList);
        }
        List<Printer> printerList = printerMapper.listPrintersByPage(searchParam);
        if (CollectionUtil.isNotEmpty(printerList)) {
            printerVoList = CollectionUtil.copyToDescList(printerList, PrinterVo.class);
        }
        return new Page<>(totalCount, printerVoList);
    }

    /**
     * 根据查询条件查询
     *
     * @param printerVo
     * @return
     */
    @Override
    public List<Printer> listPrinters(PrinterVo printerVo) throws ServiceException {
        if (printerVo == null) {
            return new ArrayList<>();
        }
        Printer printer = new Printer();
        BeanUtil.beanCopier(printerVo, printer);

        return printerMapper.listPrinters(printer);
    }

    /**
     * 根据打印店管理员id列表查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<Printer> listPrintersByIds(List<Long> ids) throws ServiceException {
        if (CollectionUtil.isNullOrEmpty(ids)) {
            return new ArrayList<>();
        }
        return printerMapper.listPrintersByIds(ids);
    }

    private void checkRepeat(Printer printer) throws ServiceException {
        Printer newUser = new Printer();
        newUser.setId(printer.getId());
        newUser.setUserName(printer.getUserName());
        if (printerMapper.checkRepeatName(newUser) > 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NAME_EXISTED);
        }
        newUser.setUserName(null);
        newUser.setPhoneNo(printer.getPhoneNo());
        if (printerMapper.checkRepeatName(newUser) > 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PHONE_EXISTED);
        }
        newUser.setPhoneNo(null);
        newUser.setEmail(printer.getEmail());
        if (printerMapper.checkRepeatName(newUser) > 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.EMAIL_EXISTED);
        }
    }
}
