package press.wein.home.dao;

import press.wein.home.model.FeedbackInfo;

public interface FeedbackInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FeedbackInfo record);

    int insertSelective(FeedbackInfo record);

    FeedbackInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FeedbackInfo record);

    int updateByPrimaryKeyWithBLOBs(FeedbackInfo record);

    int updateByPrimaryKey(FeedbackInfo record);
}