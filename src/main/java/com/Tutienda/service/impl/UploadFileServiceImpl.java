package com.Tutienda.service.impl;

import com.Tutienda.entity.ImageUrl;
import com.Tutienda.service.IImageUrlService;
import com.Tutienda.service.IUploadFileService;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UploadFileServiceImpl implements IUploadFileService {
    private final IImageUrlService iImageUrlService;
    private final static String BUCKET_NAME = "pruebajwt-8d391.appspot.com";
    private final static String privateKeyFirebase = "private-key-firebase.json";


    public UploadFileServiceImpl(IImageUrlService iImageUrlService) {
        this.iImageUrlService = iImageUrlService;
    }

    @Override
    public Resource load(String filename) throws MalformedURLException {
      return null;
    }
    @Override
    public ImageUrl save(MultipartFile image) throws IOException {
        InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream(privateKeyFirebase);
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

        InputStream inputStream = image.getInputStream();
        String ext = Objects.requireNonNull(image.getOriginalFilename()).substring(image.getOriginalFilename().lastIndexOf("."));
        String nameImage = UUID.randomUUID().toString() + ext;

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        BlobId blobId = BlobId.of(BUCKET_NAME, nameImage);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        Blob blob = storage.create(blobInfo, inputStream);
        // Construir la URL de Firebase Storage manualmente
        String firebaseUrl = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/" + nameImage + "?alt=media";
        ImageUrl imageURL = new ImageUrl();
        imageURL.setUrl(firebaseUrl);
        return iImageUrlService.save(imageURL);
    }

    @Override
    public boolean delete(String filename) {
        // Implementa la lógica para eliminar la imagen del Firebase Storage
        return false;
    }
}
