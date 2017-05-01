package com.filemanager.input;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileUploaderTest {
    private final String FILE_NAME = "file.txt";
    private final String PATH_TO_UPLOAD = System.getProperty("user.dir") + "/tmp";

    private MockMultipartFile fileToUpload;
    private FileUploader fileUploader;

    @Before
    public void setUp() throws Exception {
        String fileName = this.getClass().getClassLoader().getResource(FILE_NAME).getFile();
        File file = new File(fileName);
        fileToUpload = new MockMultipartFile("file", FILE_NAME, "txt" , new FileInputStream(file));

        fileUploader = new FileUploader();
    }

    @Test
    public void uploadsFile() throws Exception {
        Path uploadedFile = fileUploader.upload(fileToUpload, PATH_TO_UPLOAD);

        assertThat(uploadedFile.getFileName().toString(), is(FILE_NAME));
        assertThat(uploadedFile.getParent().toString(), is(PATH_TO_UPLOAD));
    }
}