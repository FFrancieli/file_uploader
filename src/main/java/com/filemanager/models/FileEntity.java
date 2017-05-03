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

    public FileEntity(String name, String location, UploadStatus status) {
        this.name = name;
        this.location = location;
        this.uploadStatus = status;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
