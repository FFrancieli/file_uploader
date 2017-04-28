package com.filemanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/files")
public class FileController {

    @ResponseStatus(OK)
    @PostMapping
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
