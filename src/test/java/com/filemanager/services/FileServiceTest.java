package com.filemanager.services;

import com.filemanager.input.FileUploader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileServiceTest {

    private final String UPLOAD_TO_FOLDER = System.getProperty("user.dir") + "/tmp" ;

    FileService service;
    MockMultipartFile fileToUpload;

    @Mock
    FileUploader fileUploader;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        service = new FileService(fileUploader);

        String fileName = this.getClass().getClassLoader().getResource("file.txt").getFile();

        File file = new File(fileName);

        fileToUpload = new MockMultipartFile("file",  new FileInputStream(file));
    }

    @Test
    public void returnsHttpStaus200WhenFileIsUploaded() throws Exception {
        ResponseEntity response = service.uploadFile(fileToUpload);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void uploadsFile() throws Exception {
        service.uploadFile(fileToUpload);

        verify(fileUploader, times(1)).upload(any(MultipartFile.class), anyString());
    }

    @Test
    public void uploadsCorrectFile() throws Exception {
        ArgumentCaptor<MultipartFile> fileCaptor = ArgumentCaptor.forClass(MultipartFile.class);

        service.uploadFile(fileToUpload);

        verify(fileUploader, times(1)).upload(fileCaptor.capture(), anyString());

        assertThat(fileCaptor.getValue(), is(fileToUpload));
    }

    @Test
    public void uploadsFileToCorrectDirectory() throws Exception {
        ArgumentCaptor<String> directoryCaptor = ArgumentCaptor.forClass(String.class);

        service.uploadFile(fileToUpload);

        verify(fileUploader, times(1)).upload(any(MultipartFile.class), directoryCaptor.capture());

        assertThat(directoryCaptor.getValue(), is(UPLOAD_TO_FOLDER));
    }
}