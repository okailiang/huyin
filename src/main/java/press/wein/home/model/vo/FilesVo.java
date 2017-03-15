package press.wein.home.model.vo;

public class FilesVo extends BaseVo {
    private Long id;

    private Long userId;

    private String userName;

    private String fileName;

    private Byte fileType;

    private Integer fileSize;

    private String filePath;

    private Integer filePages;

    private Byte imageSize;

    private Byte isOrdered;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Byte getFileType() {
        return fileType;
    }

    public void setFileType(Byte fileType) {
        this.fileType = fileType;
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

    public Byte getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(Byte isOrdered) {
        this.isOrdered = isOrdered;
    }
}