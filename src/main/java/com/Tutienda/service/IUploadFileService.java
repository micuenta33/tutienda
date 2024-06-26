package com.Tutienda.service;

import com.Tutienda.entity.ImageUrl;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IUploadFileService {
    Resource load(String filename) throws MalformedURLException;
    ImageUrl save(MultipartFile file) throws IOException;
    boolean delete(String filename);
}
