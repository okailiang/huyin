package press.wein.home.controller;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.common.Page;
import press.wein.home.constant.TipConstants;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.bo.UserSession;
import press.wein.home.model.vo.FeedbackInfoVo;
import press.wein.home.service.FeedbackInfoService;
import press.wein.home.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 反馈管理
 *
 * @author oukailiang
 * @create 2017-07-19 下午6:46
 */
@Controller
@RequestMapping(value = "/")
public class FeedbackInfoController extends BaseController {
    public static final Logger LOG = LoggerFactory.getLogger(FeedbackInfoController.class);

    @Autowired
    private FeedbackInfoService feedbackInfoService;

    /**
     * 更新反馈信息状态
     *
     * @param vo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/user/feedback/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> saveFeedbackInfo(@RequestBody FeedbackInfoVo vo, HttpServletRequest request) throws ServiceException {

        try {
            this.setCreatorAndModifier(vo);
            feedbackInfoService.saveFeedbackInfo(vo);
        } catch (ServiceException e) {
            LOG.error("FeedbackInfoController.saveFeedbackInfo ServiceException inputParam = [{}]", vo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("FeedbackInfoController.saveFeedbackInfo Exception inputParam = [{}]", vo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.FEEDBACK_SUCCESS);
    }

    /**
     * 分页获取城市列表
     *
     * @return
     */
    @RequestMapping(value = "/admin/feedback/listWithPage", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listFeedbackWithPage(Page<FeedbackInfoVo> page, FeedbackInfoVo vo) throws ServiceException {
        Page<FeedbackInfoVo> voPage;
        try {
            voPage = feedbackInfoService.listFeedbackInfosWithPage(page, vo);
        } catch (ServiceException e) {
            LOG.error("FeedbackInfoController.listFeedbackWithPage ServiceException inputParam = [{}]", vo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("FeedbackInfoController.listFeedbackWithPage Exception inputParam = [{}]", vo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(voPage);
    }

    /**
     * 分页获取城市列表
     *
     * @return
     */
    @RequestMapping(value = "/admin/feedback/getById", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> getFeedback(@Param(value = "id") Long id) throws ServiceException {
        FeedbackInfoVo vo;
        try {
            vo = feedbackInfoService.getFeedbackInfo(id);
        } catch (ServiceException e) {
            LOG.error("FeedbackInfoController.listFeedbackWithPage ServiceException inputParam = [feedbackId:{}]", id, e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("FeedbackInfoController.listFeedbackWithPage Exception inputParam = [feedbackId:{}]", id, e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(vo);
    }

    /**
     * 更新反馈信息状态
     *
     * @param vo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/admin/feedback/updateStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updateFeedbackInfoStatus(@RequestBody FeedbackInfoVo vo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(vo);
            feedbackInfoService.updateFeedbackInfoStatus(vo);
        } catch (ServiceException e) {
            LOG.error("FeedbackInfoController.updateFeedbackInfoStatus ServiceException inputParam = [{}]", vo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("FeedbackInfoController.updateFeedbackInfoStatus Exception inputParam = [{}]", vo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.UPDATE_SUCCESS);
    }

    /**
     * 删除
     *
     * @param vo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/admin/feedback/remove", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> removeFeedback(@RequestBody FeedbackInfoVo vo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(vo);
            feedbackInfoService.removeFeedbackInfo(vo);
        } catch (ServiceException e) {
            LOG.error("FeedbackInfoController.removeFeedback ServiceException inputParam = [{}]", vo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("FeedbackInfoController.removeFeedback Exception inputParam = [{}]", vo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.DELETE_SUCCESS);
    }

    private void setCreatorAndModifier(FeedbackInfoVo vo) {
        UserSession userSession = ApplicationUserContext.getUser();
        vo.setCreator(userSession.getName());
        vo.setModifier(userSession.getName());
        vo.setCreatorId(userSession.getId());
        vo.setModifierId(userSession.getId());
    }

    private void setModifier(FeedbackInfoVo vo) {
        UserSession userSession = ApplicationUserContext.getUser();
        vo.setModifier(userSession.getName());
        vo.setModifierId(userSession.getId());
        vo.setModifyTime(new Date());
    }
}
