package com.filemanager.repositories;

import com.filemanager.models.FileEntity;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileEntity, Long> {
}
