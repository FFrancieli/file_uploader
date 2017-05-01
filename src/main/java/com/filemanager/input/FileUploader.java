package com.filemanager.input;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUploader {

    public Path upload(MultipartFile file, String uploadToPath) throws IOException {
        byte[] bytes = file.getBytes();

        Path uploadFilePath = Paths.get(uploadToPath + File.separator + file.getOriginalFilename());

        return Files.write(uploadFilePath, bytes);
    }
}