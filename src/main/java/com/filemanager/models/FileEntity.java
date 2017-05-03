package com.filemanager.models;

import javax.persistence.*;

@Entity
@Table(schema = "schema_version", name="file")
public class FileEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String location;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "upload_status")
    private UploadStatus uploadStatus;

    public FileEntity(String name, String location, UploadStatus status, Long userId) {
        this.name = name;
        this.location = location;
        this.uploadStatus = status;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public UploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
}
