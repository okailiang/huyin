package press.wein.home.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author oukailiang
 * @create 2017-07-13 下午9:03
 */

public class CityVo implements Serializable {
    private static final long serialVersionUID = 2167413548608860377L;

    private Integer id;

    private Integer districtCode;

    private String name;

    private Byte level;

    private Byte type;

    private String pinyin;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Integer parentId;

    private String abbr;

    private String fullName;

    public CityVo() {
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

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        return "CityVo{" +
                "id=" + id +
                ", districtCode=" + districtCode +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", type=" + type +
                ", pinyin='" + pinyin + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", parentId=" + parentId +
                ", abbr='" + abbr + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
