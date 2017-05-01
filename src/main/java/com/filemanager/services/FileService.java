package com.filemanager.services;

import com.filemanager.input.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    private static final String UPLOAD_TO_FOLDER = System.getProperty("user.dir") + "/tmp" ;

    private FileUploader fileUploader;

    @Autowired
    public FileService(FileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }

    public ResponseEntity uploadFile(MultipartFile file) throws IOException {
        fileUploader.upload(file, UPLOAD_TO_FOLDER);

        return new ResponseEntity("uploaded", HttpStatus.OK);
    }
}