package press.wein.home.dao;

import press.wein.home.model.FeedbackInfo;

import java.util.List;
import java.util.Map;

public interface FeedbackInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FeedbackInfo record);

    int insertSelective(FeedbackInfo record);

    FeedbackInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FeedbackInfo record);

    int updateByPrimaryKey(FeedbackInfo record);

    long countFeedbackInfos(Map<String, Object> param);

    List<FeedbackInfo> listFeedbackInfosWithPage(Map<String, Object> param);
}