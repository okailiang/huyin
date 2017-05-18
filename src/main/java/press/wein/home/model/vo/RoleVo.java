package press.wein.home.model.vo;

import java.util.Date;

/**
 * @author oukailiang
 * @create 2017-05-16 下午11:47
 */

public class RoleVo extends BaseVo {

    private Integer id;

    private String roleName;

    private Byte status;

    private Short dataLevel;

    private Byte smsVerify;

    private Byte assignAuthority;

    public RoleVo() {
    }

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

    @Override
    public String toString() {
        return "RoleVo{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", dataLevel=" + dataLevel +
                ", smsVerify=" + smsVerify +
                ", assignAuthority=" + assignAuthority +
                '}';
    }
}
