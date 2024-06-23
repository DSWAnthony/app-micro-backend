package com.app.exa.cloud;

import com.cloudinary.Cloudinary;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CloudService {
    @Inject
    Cloudinary cloudinary;

    public Map uploadPdf(String filePath) {
        CloudConfig cloudConfig = new CloudConfig();
        cloudinary = cloudConfig.cloudinary();
        File file = new File(filePath);
        String fileName = file.getName();
        String publicId = fileName.substring(0, fileName.lastIndexOf('.'));

        Map<String, Object> uploadParams = new HashMap<>();
        uploadParams.put("folder", "pdf");
        uploadParams.put("public_id",publicId);
        uploadParams.put("resource_type", "auto");
        try {
            return cloudinary.uploader().upload(file, uploadParams);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
