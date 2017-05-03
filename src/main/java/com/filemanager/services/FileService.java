package com.filemanager.services;

import com.filemanager.input.FileUploader;
import com.filemanager.models.FileEntity;
import com.filemanager.models.RetrieveFileResponse;
import com.filemanager.models.UploadStatus;
import com.filemanager.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

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

    public ResponseEntity uploadFile(MultipartFile file, Long userId) throws IOException {
        try {
            Path path = fileUploader.upload(file, UPLOAD_TO_FOLDER);
            FileEntity entity = new FileEntity(file.getOriginalFilename(), path.getParent().toString(), UploadStatus.COMPLETE, userId);
            repository.save(entity);

        } catch (IOException exception) {
            FileEntity entity = new FileEntity(file.getOriginalFilename(), "", UploadStatus.FAILED, userId);
            repository.save(entity);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<Set> getAllFiles(){
        Iterable<FileEntity> response = repository.findAll();
        return new ResponseEntity<>(entityToResponseList(response), HttpStatus.OK);
    }

    private Set<RetrieveFileResponse> entityToResponseList(Iterable<FileEntity> entities){
        Set<RetrieveFileResponse> response = new HashSet<>();

        entities.forEach(entity -> response.add(new RetrieveFileResponse(entity)));

        return response;
    }
}