package press.wein.home.model.vo;

import java.io.Serializable;

/**
 * @author oukailiang
 * @create 2017-07-13 下午9:04
 */

public class ProvinceVo implements Serializable {

    private static final long serialVersionUID = -1644749637764036980L;
    private Integer id;

    private Integer districtCode;

    private String name;

    private String pinyin;

    private String region;

    private String abbr;

    private String fullName;

    public ProvinceVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "ProvinceVo{" +
                "id=" + id +
                ", districtCode=" + districtCode +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", region='" + region + '\'' +
                ", abbr='" + abbr + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
