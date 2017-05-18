package press.wein.home.model;

import java.util.Date;

public class SysRole {
    private Integer id;

    private String roleName;

    private Byte status;

    private Byte isDeleted;

    private Date createTime;

    private Date modifyTime;

    private Long creatorId;

    private String creator;

    private String modifier;

    private Long modifierId;

    private Short dataLevel;

    private Byte smsVerify;

    private Byte assignAuthority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public Short getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(Short dataLevel) {
        this.dataLevel = dataLevel;
    }

    public Byte getSmsVerify() {
        return smsVerify;
    }

    public void setSmsVerify(Byte smsVerify) {
        this.smsVerify = smsVerify;
    }

    public Byte getAssignAuthority() {
        return assignAuthority;
    }

    public void setAssignAuthority(Byte assignAuthority) {
        this.assignAuthority = assignAuthority;
    }
}