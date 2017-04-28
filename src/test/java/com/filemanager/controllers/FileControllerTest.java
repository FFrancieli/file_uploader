package com.filemanager.controllers;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileControllerTest {

    MultipartFile file;

    @Test
    public void returnsHttpStatusOkWhenFileIsUploaded() throws Exception {
        FileController controller = new FileController();

        ResponseEntity response = controller.uploadFile(file);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}