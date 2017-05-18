package press.wein.home.model;

import java.util.Date;

public class SysLog {
    private Long id;

    private String methodName;

    private String className;

    private String serverIp;

    private String ipAddress;

    private Integer levelOneModule;

    private Integer levelTwoModule;

    private Byte status;

    private Byte isDeleted;

    private Date createTime;

    private Long creatorId;

    private String creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getLevelOneModule() {
        return levelOneModule;
    }

    public void setLevelOneModule(Integer levelOneModule) {
        this.levelOneModule = levelOneModule;
    }

    public Integer getLevelTwoModule() {
        return levelTwoModule;
    }

    public void setLevelTwoModule(Integer levelTwoModule) {
        this.levelTwoModule = levelTwoModule;
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
}