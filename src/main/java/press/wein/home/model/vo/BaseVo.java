package press.wein.home.model.vo;

import org.springframework.format.annotation.DateTimeFormat;
import press.wein.home.common.Paper;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础类
 *
 * @author oukailiang
 * @create 2017-02-16 下午5:44
 */

public class BaseVo extends Paper implements Serializable {
    private static final long serialVersionUID = 4617372132750613663L;

    private Byte isDeleted;

    private Date createTime;

    private Date modifyTime;

    private Long creatorId;

    private String creator;

    private String modifier;

    private Long modifierId;

    /**
     * 用于查询时间段开始
     */
    private Date createTimeFrom;
    /**
     * 用于查询时间段结束
     */
    private Date createTimeTo;


    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Date getCreateTimeFrom() {
        return createTimeFrom;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreateTimeFrom(Date createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public Date getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(Date createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }
}
