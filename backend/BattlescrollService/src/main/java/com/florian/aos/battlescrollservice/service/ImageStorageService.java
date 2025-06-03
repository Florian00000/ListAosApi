package com.florian.aos.battlescrollservice.service;

import com.florian.aos.battlescrollservice.entity.charter.Charter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class ImageStorageService {

    public Charter saveImageToCharter (Charter charter) {
        if (charter.getImagePath() != null) {
            try {
                String imagePath = saveImageBase64(charter.getImagePath());
                charter.setImagePath(imagePath);
            }catch (IOException e){
                throw new IllegalArgumentException("Image path could not be saved");
            }
        }
        return charter;
    }

    public Charter saveImageToCharter (Charter charter, MultipartFile imageFile) {
        try {
            String imagePath = saveImage(imageFile);
            charter.setImagePath(imagePath);
        }catch (IOException e){
            throw new IllegalArgumentException("Image path could not be saved");
        }
        return charter;
    }

    public void deleteImage(String imagePath) {
        String rootPath = System.getProperty("user.dir");
        String directoryPath = rootPath + "/public/";
        File file = new File(directoryPath + imagePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new RuntimeException("Failed to delete image at path: " + imagePath);
            }
        }
    }


    private String saveImageBase64(String imageInput) throws IOException {
        if(imageInput == null || imageInput.isBlank()){
            throw new IllegalArgumentException("Image input is null or empty");
        }

        String base64Data = imageInput.contains(",") ? imageInput.split(",")[1] : imageInput;

        byte[] decodedBytes;
        try {
            decodedBytes = Base64.getDecoder().decode(base64Data);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Image input is invalid", e);
        }

        String rootPath = System.getProperty("user.dir");
        String directoryPath = rootPath + "/public/images/";
        File dir = new File(directoryPath);
        if(!dir.exists() && !dir.mkdirs()){
            throw new IOException("Failed to create directory" + directoryPath);
        }

        String filename = UUID.randomUUID() + ".png";
        File file = new File(dir, filename);
        try(FileOutputStream fos = new FileOutputStream(file)){
            fos.write(decodedBytes);
        }
        System.out.println("Image saved at: " + file.getAbsolutePath());
        return "/images/" + filename;
    }

    private String saveImage(MultipartFile file) throws IOException {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        if (originalFilename.contains("..")) {
            throw new IllegalArgumentException("Invalid file path");
        }

        String rootPath = System.getProperty("user.dir"); // racine du projet
        String directory = rootPath + "/public/images/";

        File dir = new File(directory);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Could not create directory: " + directory);
        }

        String fileName = UUID.randomUUID().toString() + "-" + originalFilename;
        File output = new File(directory + fileName);
        file.transferTo(output);
        System.out.println("Saved image to " + output.getAbsolutePath());

        return "/images/" + fileName;
    }
}
