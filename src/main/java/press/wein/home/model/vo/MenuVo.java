package press.wein.home.model.vo;

import press.wein.home.model.RealUrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单显示
 *
 * @author oukailiang
 * @create 2017-03-03 上午10:43
 */

public class MenuVo implements Serializable {

    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单链接
     */
    private String url;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 菜单级别
     */
    private Short level;

    /**
     * 菜单排序
     */
    private String sorting;

    /**
     * 菜单国际化
     */
    private String translate;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 提醒菜单个数
     */
    private String alert;

    /**
     * 提醒标签样式
     */
    private String label;

    private Byte status;

    private Byte isDeleted;

    private Date createTime;

    private Date modifyTime;

    private Long creatorId;

    private String creator;

    private String modifier;

    private Long modifierId;

    // 子菜单
    private List<MenuVo> childMenu = new ArrayList<MenuVo>();

    private List<RealUrl> realUrlList = new ArrayList<RealUrl>();

    public MenuVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public List<MenuVo> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<MenuVo> childMenu) {
        this.childMenu = childMenu;
    }

    public List<RealUrl> getRealUrlList() {
        return realUrlList;
    }

    public void setRealUrlList(List<RealUrl> realUrlList) {
        this.realUrlList = realUrlList;
    }
}
