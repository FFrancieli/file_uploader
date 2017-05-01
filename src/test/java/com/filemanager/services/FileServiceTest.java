package com.filemanager.services;

import com.filemanager.input.FileUploader;
import com.filemanager.models.FileEntity;
import com.filemanager.repositories.FileRepository;
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
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileServiceTest {

    public static final String FILE_NAME = "file.txt";
    private final String UPLOAD_TO_FOLDER = System.getProperty("user.dir") + "/tmp" ;

    FileService service;
    MockMultipartFile fileToUpload;

    @Mock
    FileUploader fileUploader;

    @Mock
    FileRepository repository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        service = new FileService(fileUploader, repository);

        String fileName = this.getClass().getClassLoader().getResource(FILE_NAME).getFile();
        File file = new File(fileName);
        fileToUpload = new MockMultipartFile("file", FILE_NAME, "txt" , new FileInputStream(file));

        when(fileUploader.upload(fileToUpload, UPLOAD_TO_FOLDER)).thenReturn(Paths.get(UPLOAD_TO_FOLDER, FILE_NAME));
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

    @Test
    public void savesFileInformationOnDatabase() throws Exception {
        service.uploadFile(fileToUpload);

        verify(repository, times(1)).save(any(FileEntity.class));
    }

    @Test
    public void savesFileWithCorrectInformationOnDatabase() throws Exception {
        FileService service = new FileService(new FileUploader(), repository);

        ArgumentCaptor<FileEntity> entityCaptor = ArgumentCaptor.forClass(FileEntity.class);

        service.uploadFile(fileToUpload);

        verify(repository, times(1)).save(entityCaptor.capture());

        FileEntity savedInformation = entityCaptor.getValue();
        assertThat(savedInformation.getName(), is(FILE_NAME));
        assertThat(savedInformation.getLocation(), is(UPLOAD_TO_FOLDER));
    }
}