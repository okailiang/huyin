package press.wein.home.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderFile {
    private Long id;

    private Long orderId;

    private Long fileId;

    private Integer perFileCopies;

    private Byte printType;

    private BigDecimal perFilePrice;

    private Byte compactPrint;

    private String fileName;

    private Integer fileSize;

    private String filePath;

    private Integer filePages;

    private Byte imageSize;

    private Byte isDeleted;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getPerFileCopies() {
        return perFileCopies;
    }

    public void setPerFileCopies(Integer perFileCopies) {
        this.perFileCopies = perFileCopies;
    }

    public Byte getPrintType() {
        return printType;
    }

    public void setPrintType(Byte printType) {
        this.printType = printType;
    }

    public BigDecimal getPerFilePrice() {
        return perFilePrice;
    }

    public void setPerFilePrice(BigDecimal perFilePrice) {
        this.perFilePrice = perFilePrice;
    }

    public Byte getCompactPrint() {
        return compactPrint;
    }

    public void setCompactPrint(Byte compactPrint) {
        this.compactPrint = compactPrint;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFilePages() {
        return filePages;
    }

    public void setFilePages(Integer filePages) {
        this.filePages = filePages;
    }

    public Byte getImageSize() {
        return imageSize;
    }

    public void setImageSize(Byte imageSize) {
        this.imageSize = imageSize;
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
}