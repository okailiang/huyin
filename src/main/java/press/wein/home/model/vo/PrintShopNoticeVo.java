package press.wein.home.model.vo;

public class PrintShopNoticeVo extends BaseVo {
    private Long id;

    private Long printShopId;

    private String notice;

    private Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrintShopId() {
        return printShopId;
    }

    public void setPrintShopId(Long printShopId) {
        this.printShopId = printShopId;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}