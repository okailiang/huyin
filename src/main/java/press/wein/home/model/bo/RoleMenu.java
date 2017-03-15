package press.wein.home.model.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 页面根据角色加载菜单
 *
 * @author oukailiang
 * @create 2017-03-09 下午10:46
 */

public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -6497600966583704664L;
    private String heading;
    private String text;
    private String sref;
    private String icon;
    private String alert;
    private String label;
    private List<RoleMenu> submenu;
    private String translate;

    public RoleMenu() {
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSref() {
        return sref;
    }

    public void setSref(String sref) {
        this.sref = sref;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<RoleMenu> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<RoleMenu> submenu) {
        this.submenu = submenu;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}
