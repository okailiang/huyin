package press.wein.home.model.bo;

import press.wein.home.model.Menu;
import press.wein.home.model.vo.MenuVo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户session
 *
 * @author oukailiang
 * @create 2017-02-22 下午4:56
 */

public class UserSession implements Serializable {

    private static final long serialVersionUID = 1566258646353777221L;

    // 用户id
    private Long id;
    // 姓名
    private String name;
    // 登录名
    private String loginName;
    // 最后续命时间
    private Date lastExtension;
    //登陆ip地址
    private String ipAddress;
    //用户类型
    private Integer userType;

    private List<Menu> menuList;

    //权限码列表（功能权限用）
    private List<String> permissionCodeList;

    public UserSession() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getLastExtension() {
        return lastExtension;
    }

    public void setLastExtension(Date lastExtension) {
        this.lastExtension = lastExtension;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<String> getPermissionCodeList() {
        return permissionCodeList;
    }

    public void setPermissionCodeList(List<String> permissionCodeList) {
        this.permissionCodeList = permissionCodeList;
    }
}
