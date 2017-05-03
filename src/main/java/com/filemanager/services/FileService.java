package com.filemanager.services;

import com.filemanager.input.FileUploader;
import com.filemanager.models.FileEntity;
import com.filemanager.models.UploadStatus;
import com.filemanager.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class FileService {
    private static final String UPLOAD_TO_FOLDER = System.getProperty("user.dir") + "/tmp" ;

    private FileUploader fileUploader;
    private FileRepository repository;

    @Autowired
    public FileService(FileUploader fileUploader, FileRepository repository) {
        this.fileUploader = fileUploader;
        this.repository = repository;
    }

    public ResponseEntity uploadFile(MultipartFile file) throws IOException {
        Path path = fileUploader.upload(file, UPLOAD_TO_FOLDER);

        FileEntity entity = new FileEntity(file.getOriginalFilename(), path.getParent().toString(), UploadStatus.COMPLETE);

        repository.save(entity);

        return new ResponseEntity("uploaded", HttpStatus.OK);
    }
}