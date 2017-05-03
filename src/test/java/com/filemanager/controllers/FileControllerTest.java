package com.filemanager.controllers;

import com.filemanager.services.FileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileControllerTest {

    @Mock
    private FileService service;

    private MultipartFile file;
    private FileController controller;
    private final Long userId = 1L;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        when(service.uploadFile(file, userId)).thenReturn(new ResponseEntity("{\"id\": 2}", HttpStatus.OK));

        controller = new FileController(service);
    }

    @Test
    public void returnsHttpStatusOkWhenFileIsUploaded() throws Exception {
        ResponseEntity<String> response = controller.uploadFile(file, userId);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void uploadsFile() throws Exception {
        controller.uploadFile(file, userId);

        verify(service, times(1)).uploadFile(file, userId);
    }

    @Test
    public void returnsFileUploadedId() throws Exception {
        ResponseEntity<String> response = controller.uploadFile(file, userId);

        assertThat(response.getBody(), is("{\"id\": 2}"));
    }
}