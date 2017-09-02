package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;

public class VideoBean implements Serializable {
    private String fileName;//文档名字
    private String fileType;//文档类型
    private String filePath;//文档路径
    private Long fileLength;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }

}