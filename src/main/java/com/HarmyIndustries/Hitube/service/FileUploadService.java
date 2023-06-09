package com.HarmyIndustries.Hitube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

    @Value("upload.path")
    String uploadPath;

    public void uploadFile(MultipartFile file) throws IOException {

        file.transferTo(new File("D:\\Harmy Industries\\HItube\\HItubeV1demo\\img\\" + file.getOriginalFilename()));

    }

}
