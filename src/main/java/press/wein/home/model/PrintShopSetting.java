package press.wein.home.model;

import java.util.Date;

public class PrintShopSetting {
    private Long id;

    private Long printShopId;

    private String notice;

    private Byte status;

    private Byte isDeleted;

    private Date createTime;

    private Date modifyTime;

    private Long creatorId;

    private String creator;

    private String modifier;

    private Long modifierId;

    private Byte isFree;

    private Byte isOfflinePrint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrintShopId() {
        return printShopId;
    }

    public void setPrintShopId(Long printShopId) {
        this.printShopId = printShopId;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

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

    public Byte getIsFree() {
        return isFree;
    }

    public void setIsFree(Byte isFree) {
        this.isFree = isFree;
    }

    public Byte getIsOfflinePrint() {
        return isOfflinePrint;
    }

    public void setIsOfflinePrint(Byte isOfflinePrint) {
        this.isOfflinePrint = isOfflinePrint;
    }
}