package com.HarmyIndustries.Hitube.controller;

import com.HarmyIndustries.Hitube.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    private FileUploadService fileUploadService;


    @GetMapping("/files")
    public String getPage(){
        return "upload";
    }

    @PostMapping("/file")
    public String uploadFile(Model model, @RequestParam MultipartFile file) throws IOException {
        fileUploadService.uploadFile(file);
        return "upload";
    }
}
