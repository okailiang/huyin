package press.wein.home.model.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderVo extends BaseVo {
    private Long id;

    private Long userId;

    private Long printerId;

    private String orderNo;

    private Long deliverAddressId;

    private Long printShopId;

    private Byte orderState;

    private Date payTime;

    private BigDecimal totalPrice;

    private Integer copies;

    private Date printTime;

    private Date finishTime;

    private Byte deliveryWay;

    private String deliveryTime;

    private Integer pages;

    private Byte payWay;

    private Byte directPrint;

    private Byte orderType;

    private String remark;

    private BigDecimal discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Long printerId) {
        this.printerId = printerId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getDeliverAddressId() {
        return deliverAddressId;
    }

    public void setDeliverAddressId(Long deliverAddressId) {
        this.deliverAddressId = deliverAddressId;
    }

    public Long getPrintShopId() {
        return printShopId;
    }

    public void setPrintShopId(Long printShopId) {
        this.printShopId = printShopId;
    }

    public Byte getOrderState() {
        return orderState;
    }

    public void setOrderState(Byte orderState) {
        this.orderState = orderState;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Byte getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(Byte deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Byte getPayWay() {
        return payWay;
    }

    public void setPayWay(Byte payWay) {
        this.payWay = payWay;
    }

    public Byte getDirectPrint() {
        return directPrint;
    }

    public void setDirectPrint(Byte directPrint) {
        this.directPrint = directPrint;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}