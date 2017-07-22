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
import press.wein.home.model.vo.PrintShopVo;
import press.wein.home.service.PrintShopService;
import press.wein.home.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 打印店
 *
 * @author oukailiang
 * @create 2017-07-22 下午6:18
 */
@Controller
@RequestMapping(value = "/admin/printShop")
public class PrintShopController extends BaseController {
    public static final Logger LOG = LoggerFactory.getLogger(PrintShopController.class);

    @Autowired
    private PrintShopService printShopService;

    /**
     * 分页获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/listWithPage", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listPrintShopsWithPage(Page<PrintShopVo> page, PrintShopVo printShopVo) throws ServiceException {
        Page<PrintShopVo> printShopVoListPage;
        try {
            printShopVoListPage = printShopService.listPrintShopsWithPage(page, printShopVo);
        } catch (ServiceException e) {
            LOG.error("PrintShopController.listPrintShopsWithPage inputParam = [{}]", printShopVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrintShopController.listPrintShopsWithPage inputParam = [{}]", printShopVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(printShopVoListPage);
    }


    /**
     * 获取系统用户
     *
     * @return
     */
    @RequestMapping(value = "/getPrintShop", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> getMenu(@RequestParam(value = "id") Long id) throws ServiceException {
        return ResponseUtils.success(printShopService.getPrintShop(id));
    }

    /**
     * 新增
     *
     * @param printShopVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> savePrintShop(@RequestBody PrintShopVo printShopVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setCreatorAndModifier(printShopVo);
            printShopService.savePrintShop(printShopVo);
        } catch (ServiceException e) {
            LOG.error("PrintShopController.savePrintShop inputParam = [{}]", printShopVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrintShopController.savePrintShop inputParam = [{}]", printShopVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.SAVE_SUCCESS);
    }

    /**
     * 更新
     *
     * @param printShopVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updatePrintShop(@RequestBody PrintShopVo printShopVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(printShopVo);
            printShopService.updatePrintShop(printShopVo);
        } catch (ServiceException e) {
            LOG.error("PrintShopController.updatePrintShop inputParam = [{}]", printShopVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrintShopController.updatePrintShop inputParam = [{}]", printShopVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.UPDATE_SUCCESS);
    }


    /* 删除
     * 更新
     *
     * @param printShopVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> removePrintShop(@RequestBody PrintShopVo printShopVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(printShopVo);
            printShopService.removePrintShop(printShopVo);
        } catch (ServiceException e) {
            LOG.error("PrintShopController.removePrintShop inputParam = [{}]", printShopVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrintShopController.removePrintShop inputParam = [{}]", printShopVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DELETE_SUCCESS);
    }

    /**
     * 启用
     *
     * @param printShopVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/enable", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> enablePrintShop(@RequestBody PrintShopVo printShopVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(printShopVo);
            printShopService.enablePrintShop(printShopVo);
        } catch (ServiceException e) {
            LOG.error("PrintShopController.enablePrintShop inputParam [{}]", printShopVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrintShopController.enablePrintShop inputParam [{}]", printShopVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.ENABLE_SUCCESS);
    }

    /**
     * 禁用
     *
     * @param printShopVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/disable", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> disablePrintShop(@RequestBody PrintShopVo printShopVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(printShopVo);
            printShopService.disablePrintShop(printShopVo);
        } catch (ServiceException e) {
            LOG.error("PrintShopController.disablePrintShop inputParam [{}]", printShopVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("PrintShopController.disablePrintShop inputParam [{}]", printShopVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DISABLE_SUCCESS);
    }

    private void setCreatorAndModifier(PrintShopVo printShopVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        printShopVo.setCreator(userSession.getName());
        printShopVo.setModifier(userSession.getName());
        printShopVo.setCreatorId(userSession.getId());
        printShopVo.setModifierId(userSession.getId());
    }

    private void setModifier(PrintShopVo printShopVo) {
        UserSession userSession = ApplicationUserContext.getUser();
        printShopVo.setModifier(userSession.getName());
        printShopVo.setModifierId(userSession.getId());
    }

}
