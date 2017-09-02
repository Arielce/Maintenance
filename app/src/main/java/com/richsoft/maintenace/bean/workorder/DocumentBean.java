package com.richsoft.maintenace.bean.workorder;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class DocumentBean implements Serializable {
    @DatabaseField(id = true, unique = true)
    private String fileName;//文档名字
    @DatabaseField
    private String fileType;//文档类型
    @DatabaseField
    private String filePath;//文档路径
    @DatabaseField
    private Long fileLength;
    @DatabaseField
    private int progress;// 已下载的百分比
    @DatabaseField
    private int state;
    @DatabaseField
    private String savePath;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}