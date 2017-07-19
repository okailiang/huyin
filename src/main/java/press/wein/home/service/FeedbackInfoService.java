package press.wein.home.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.FeedbackInfo;
import press.wein.home.model.vo.FeedbackInfoVo;

import java.util.List;

/**
 * 反馈信息
 *
 * @author oukailiang
 * @create 2017-07-19 下午5:20
 */

public interface FeedbackInfoService {

    /**
     * 保存反馈信息
     *
     * @param feedbackInfoVo
     * @return
     * @throws ServiceException
     */
    int saveFeedbackInfo(FeedbackInfoVo feedbackInfoVo) throws ServiceException;

    /**
     * 修改反馈信息
     *
     * @param feedbackInfoVo
     * @return
     * @throws ServiceException
     */
    int updateFeedbackInfoStatus(FeedbackInfoVo feedbackInfoVo) throws ServiceException;

    /**
     * 通过id删除反馈信息
     *
     * @param feedbackInfoVo
     * @return
     * @throws ServiceException
     */
    int removeFeedbackInfo(FeedbackInfoVo feedbackInfoVo) throws ServiceException;

    /**
     * 通过id获得反馈信息
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    FeedbackInfoVo getFeedbackInfo(Long id) throws ServiceException;

    /**
     * 分页查询反馈信息
     *
     * @param page
     * @param feedbackInfoVo
     * @return
     * @throws ServiceException
     */
    Page<FeedbackInfoVo> listFeedbackInfosWithPage(Page<FeedbackInfoVo> page, FeedbackInfoVo feedbackInfoVo) throws ServiceException;
}
