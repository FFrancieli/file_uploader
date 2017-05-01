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

    public FileEntity(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
