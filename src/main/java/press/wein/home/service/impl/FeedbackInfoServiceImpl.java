package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.constant.Constants;
import press.wein.home.dao.FeedbackInfoMapper;
import press.wein.home.enumerate.FeedbackEnum;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.FeedbackInfo;
import press.wein.home.model.vo.FeedbackInfoVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.FeedbackInfoService;
import press.wein.home.util.BeanUtil;
import press.wein.home.util.CollectionUtil;

import java.util.*;

/**
 * @author oukailiang
 * @create 2017-07-19 下午5:20
 */
@Service(value = "feedbackInfoService")
public class FeedbackInfoServiceImpl extends BaseService implements FeedbackInfoService {
    public static final Logger LOG = LoggerFactory.getLogger(FeedbackInfoServiceImpl.class);

    @Autowired
    private FeedbackInfoMapper feedbackInfoMapper;


    /**
     * 保存反馈信息
     *
     * @param feedbackInfoVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int saveFeedbackInfo(FeedbackInfoVo feedbackInfoVo) throws ServiceException {
        checkParamNull(feedbackInfoVo, feedbackInfoVo.getFeedInfo());
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        BeanUtil.beanCopier(feedbackInfoVo, feedbackInfo);
        return feedbackInfoMapper.insertSelective(feedbackInfo);
    }

    /**
     * 修改反馈信息
     *
     * @param feedbackInfoVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int updateFeedbackInfoStatus(FeedbackInfoVo feedbackInfoVo) throws ServiceException {
        checkParamNull(feedbackInfoVo, feedbackInfoVo.getId(),feedbackInfoVo.getStatus());
        if(FeedbackEnum.Status.HANDLED.getValue().byteValue() != feedbackInfoVo.getStatus()){
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        FeedbackInfo feedbackInfo = feedbackInfoMapper.selectByPrimaryKey(feedbackInfoVo.getId());
        if (feedbackInfo == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NOT_EXIST);
        }
        BeanUtil.beanCopier(feedbackInfoVo, feedbackInfo);
        feedbackInfo.setModifyTime(new Date());
        feedbackInfo.setModifierId(feedbackInfoVo.getModifierId());
        feedbackInfo.setModifier(feedbackInfoVo.getModifier());
        feedbackInfo.setStatus(FeedbackEnum.Status.HANDLED.getValue().byteValue());
        return feedbackInfoMapper.updateByPrimaryKeySelective(feedbackInfo);
    }

    /**
     * 通过id删除反馈信息
     *
     * @param feedbackInfoVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int removeFeedbackInfo(FeedbackInfoVo feedbackInfoVo) throws ServiceException {
        checkParamNull(feedbackInfoVo, feedbackInfoVo.getId());
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        BeanUtil.beanCopier(feedbackInfoVo, feedbackInfo);
        feedbackInfo.setIsDeleted(Constants.IS_DELETED);
        return feedbackInfoMapper.updateByPrimaryKeySelective(feedbackInfo);
    }

    /**
     * 通过id获得反馈信息
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    public FeedbackInfoVo getFeedbackInfo(Long id) throws ServiceException {
        checkParamNull(id);
        FeedbackInfo feedbackInfo = feedbackInfoMapper.selectByPrimaryKey(id);
        if (feedbackInfo == null) {
            return null;
        }
        FeedbackInfoVo feedbackInfoVo = new FeedbackInfoVo();
        BeanUtil.beanCopier(feedbackInfo, feedbackInfoVo);
        feedbackInfoVo.setStatusDesc(FeedbackEnum.Status.getNameByValue(feedbackInfoVo.getStatus()));
        feedbackInfoVo.setTypeDesc(FeedbackEnum.Type.getNameByValue(feedbackInfoVo.getType()));
        return feedbackInfoVo;
    }

    /**
     * 分页查询反馈信息
     *
     * @param page
     * @param feedbackInfoVo
     * @return
     * @throws ServiceException
     */
    @Override
    public Page<FeedbackInfoVo> listFeedbackInfosWithPage(Page<FeedbackInfoVo> page, FeedbackInfoVo feedbackInfoVo) throws ServiceException {
        Map<String, Object> param = CollectionUtil.objectToMap(feedbackInfoVo);
        this.setPageParam(param, page);

        long totalCount = feedbackInfoMapper.countFeedbackInfos(param);
        if (totalCount <= 0) {
            return new Page<>(0, Collections.emptyList());
        }
        List<FeedbackInfo> feedbackInfoList = feedbackInfoMapper.listFeedbackInfosWithPage(param);
        List<FeedbackInfoVo> feedbackInfoVoList = CollectionUtil.copyToDescList(feedbackInfoList, FeedbackInfoVo.class);
        for (FeedbackInfoVo infoVo : feedbackInfoVoList) {
            infoVo.setStatusDesc(FeedbackEnum.Status.getNameByValue(infoVo.getStatus()));
            infoVo.setTypeDesc(FeedbackEnum.Type.getNameByValue(infoVo.getType()));
        }
        return new Page<>(totalCount, feedbackInfoVoList);
    }

}
