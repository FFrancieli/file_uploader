package com.filemanager.controllers;

import com.filemanager.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/files")
public class FileController {

    FileService service;

    @Autowired
    public FileController(FileService service) {
        this.service = service;
    }

    @ResponseStatus(OK)
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        ResponseEntity response = service.uploadFile(file, userId);

        return new ResponseEntity(response.getBody(), HttpStatus.OK);
    }
}
