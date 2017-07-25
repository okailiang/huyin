package press.wein.home.model.vo;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

public class PrintShopVo extends BaseVo {
    private Long id;

    private Long printerId;

    private String printerName;

    private String address;

    private String detailAddress;

    private String phoneNo;

    private Byte addressType;

    private String addressTypeDesc;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String printShopName;

    private String printShopImage;

    private Byte praise;

    private Integer provinceId;

    private Integer cityId;

    private Integer cityAreaId;

    private String cityName;

    private String provinceCityArea;

    private Byte status;

    private String statusDesc;

    private String userName;

    private String realName;

    private String idNumber;

    private String password;

    private String email;

    private String accountPhone;

    private Byte role;

    private Byte accountStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Long printerId) {
        this.printerId = printerId;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Byte getAddressType() {
        return addressType;
    }

    public void setAddressType(Byte addressType) {
        this.addressType = addressType;
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

    public String getPrintShopName() {
        return printShopName;
    }

    public void setPrintShopName(String printShopName) {
        this.printShopName = printShopName;
    }

    public String getPrintShopImage() {
        return printShopImage;
    }

    public void setPrintShopImage(String printShopImage) {
        this.printShopImage = printShopImage;
    }

    public Byte getPraise() {
        return praise;
    }

    public void setPraise(Byte praise) {
        this.praise = praise;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityAreaId() {
        return cityAreaId;
    }

    public void setCityAreaId(Integer cityAreaId) {
        this.cityAreaId = cityAreaId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    public Byte getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Byte accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAddressTypeDesc() {
        return addressTypeDesc;
    }

    public void setAddressTypeDesc(String addressTypeDesc) {
        this.addressTypeDesc = addressTypeDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCityArea() {
        return provinceCityArea;
    }

    public void setProvinceCityArea(String provinceCityArea) {
        this.provinceCityArea = provinceCityArea;
    }
}