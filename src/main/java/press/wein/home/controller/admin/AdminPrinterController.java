package press.wein.home.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.common.Page;
import press.wein.home.constant.TipConstants;
import press.wein.home.controller.BaseController;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.bo.UserSession;
import press.wein.home.model.vo.PrinterVo;
import press.wein.home.service.PrinterService;
import press.wein.home.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统用户管理
 *
 * @author oukailiang
 * @create 2017-07-05 下午6:27
 */
@Controller
@RequestMapping(value = "/admin/printer")
public class AdminPrinterController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminPrinterController.class);

    @Autowired
    private PrinterService printerService;

    /**
     * 分页获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listPrintersWithPage(Page<PrinterVo> page, PrinterVo printerVo) throws ServiceException {
        Page<PrinterVo> printerVoListPage;
        try {
            printerVoListPage = printerService.listPrintersWithPage(page, printerVo);
        } catch (ServiceException e) {
            LOG.error("PrinterController.listPrintersWithPage inputParam = [{}]", printerVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrinterController.listPrintersWithPage inputParam = [{}]", printerVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(printerVoListPage);
    }


    /**
     * 获取系统用户
     *
     * @return
     */
    @RequestMapping(value = "/getPrinter", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> getMenu(@RequestParam(value = "id") Long id) throws ServiceException {
        return ResponseUtils.success(printerService.getPrinter(id));
    }

    /**
     * 新增
     *
     * @param printerVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> savePrinter(@RequestBody PrinterVo printerVo, HttpServletRequest request) throws ServiceException {

        try {
            printerVo.setPassword("Printer@666");
            this.setCreatorAndModifier(printerVo);
            printerService.savePrinter(printerVo);
        } catch (ServiceException e) {
            LOG.error("PrinterController.savePrinter inputParam = [{}]", printerVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrinterController.savePrinter inputParam = [{}]", printerVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.SAVE_SUCCESS);
    }

    /**
     * 更新
     *
     * @param printerVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updatePrinter(@RequestBody PrinterVo printerVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(printerVo);
            printerService.updatePrinter(printerVo);
        } catch (ServiceException e) {
            LOG.error("PrinterController.updatePrinter inputParam = [{}]", printerVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrinterController.updatePrinter inputParam = [{}]", printerVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.UPDATE_SUCCESS);
    }


    /* 删除
     * 更新
     *
     * @param printerVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> removePrinter(@RequestBody PrinterVo printerVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(printerVo);
            printerService.removePrinter(printerVo);
        } catch (ServiceException e) {
            LOG.error("PrinterController.removePrinter inputParam = [{}]", printerVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrinterController.removePrinter inputParam = [{}]", printerVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DELETE_SUCCESS);
    }

    /**
     * 启用
     *
     * @param userVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/enableUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> enableUser(@RequestBody PrinterVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(userVo);
            printerService.enablePrinter(userVo);
        } catch (ServiceException e) {
            LOG.error("PrinterController.enableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrinterController.enableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.ENABLE_SUCCESS);
    }

    /**
     * 禁用
     *
     * @param userVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/disableUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> disableUser(@RequestBody PrinterVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(userVo);
            printerService.disablePrinter(userVo);
        } catch (ServiceException e) {
            LOG.error("PrinterController.disableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrinterController.disableUser inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DISABLE_SUCCESS);
    }

    /**
     * 禁用
     *
     * @param userVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> resetPassword(@RequestBody PrinterVo userVo, HttpServletRequest request) throws ServiceException {

        try {
            userVo.setPassword("Printer@666");
            this.setModifier(userVo);
            printerService.resetPassword(userVo);
        } catch (ServiceException e) {
            LOG.error("PrinterController.resetPassword inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrinterController.resetPassword inputParam [{}]", userVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.RESET_PASSWORD_SUCCESS);
    }


    private void setCreatorAndModifier(PrinterVo printerVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        printerVo.setCreator(userSession.getName());
        printerVo.setModifier(userSession.getName());
        printerVo.setCreatorId(userSession.getId());
        printerVo.setModifierId(userSession.getId());
    }

    private void setModifier(PrinterVo printerVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        printerVo.setModifier(userSession.getName());
        printerVo.setModifierId(userSession.getId());
    }

}