package com.filemanager.models;

public class RetrieveFileResponse {
    private Long id;
    private String fileName;
    private Long userId;
    private UploadStatus uploadStatus;

    public RetrieveFileResponse(FileEntity entity) {
        this.id = entity.getId();
        this.fileName = entity.getName();
        this.userId = entity.getUserId();
        this.uploadStatus = entity.getUploadStatus();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getUserId() {
        return userId;
    }

    public UploadStatus getUploadStatus() {
        return uploadStatus;
    }
}