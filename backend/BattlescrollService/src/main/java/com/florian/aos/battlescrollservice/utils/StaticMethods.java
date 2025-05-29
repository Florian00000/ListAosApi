package com.florian.aos.battlescrollservice.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class StaticMethods {

    public static String saveImageBase64(String imageInput) throws IOException {
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

    public static String saveImage(MultipartFile file) throws IOException {
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
