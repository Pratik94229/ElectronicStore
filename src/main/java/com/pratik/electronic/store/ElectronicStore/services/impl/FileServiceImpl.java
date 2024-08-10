package com.pratik.electronic.store.ElectronicStore.services.impl;

import com.pratik.electronic.store.ElectronicStore.expections.BadApiRequestException;
import com.pratik.electronic.store.ElectronicStore.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String uploadFile(MultipartFile file, String path) throws IOException {

        // Original file name
        String originalFilename = file.getOriginalFilename();
        logger.info("Filename : {}", originalFilename);

        // Generate a unique filename
        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename + extension;

        // Full path where the file will be stored
        String fullPathWithFileName = path + fileNameWithExtension;
        logger.info("Full image path: {}", fullPathWithFileName);

        // Check if the file extension is allowed
        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg")
                || extension.equalsIgnoreCase(".jpeg")) {
            logger.info("File extension is {}", extension);

            // Ensure the directory exists, if not, create it
            File folder = new File(path);
            if (!folder.exists()) {
                logger.info("Directory does not exist, creating now: {}", path);
                boolean dirsCreated = folder.mkdirs();
                if (dirsCreated) {
                    logger.info("Directory created successfully.");
                } else {
                    logger.error("Failed to create directory: {}", path);
                    throw new IOException("Failed to create directory: " + path);
                }
            } else {
                logger.info("Directory already exists: {}", path);
            }

            // Copy the file to the target location
            logger.info("Saving file to {}", fullPathWithFileName);
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            logger.info("File saved successfully.");
            return fileNameWithExtension;

        } else {
            throw new BadApiRequestException("File with this " + extension + " not allowed!!");
        }
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }

}