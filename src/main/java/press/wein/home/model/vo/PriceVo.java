package press.wein.home.model.vo;

import java.math.BigDecimal;

public class PriceVo extends BaseVo {
    private Long id;

    private Long printerId;

    private Long printShopId;

    private String printShopName;

    private Byte priceType;

    private Byte fileType;

    private BigDecimal price;

    private BigDecimal discount;

    private Byte status;

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

    public Long getPrintShopId() {
        return printShopId;
    }

    public void setPrintShopId(Long printShopId) {
        this.printShopId = printShopId;
    }

    public String getPrintShopName() {
        return printShopName;
    }

    public void setPrintShopName(String printShopName) {
        this.printShopName = printShopName;
    }

    public Byte getPriceType() {
        return priceType;
    }

    public void setPriceType(Byte priceType) {
        this.priceType = priceType;
    }

    public Byte getFileType() {
        return fileType;
    }

    public void setFileType(Byte fileType) {
        this.fileType = fileType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}