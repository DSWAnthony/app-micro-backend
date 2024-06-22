package com.app.exa.cloud;

import com.cloudinary.Cloudinary;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CloudConfig {

    private final String cloud_name ="doqovpova";
    private final String api_key ="879758869235742";
    private final String api_secret ="7Dn18YIOGhYp8R0YnYCGUgchNls";

    @Produces
    @Singleton
    public Cloudinary cloudinary(){
        Map<String,String> config =new HashMap<>();
        config.put("cloud_name",cloud_name);
        config.put("api_key",api_key);
        config.put("api_secret",api_secret);
        return new Cloudinary(config);
    }
}
