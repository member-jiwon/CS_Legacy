package com.kedu.file;

import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class GcpConfig {

    @Bean
    public Storage storage() throws Exception {
        InputStream keyFile = getClass().getClassLoader()
        		.getResourceAsStream("google/sublime-night-472604-p4-f18e321084b3.json");
        if (keyFile == null) {
            throw new IllegalStateException("GCP key file not found in classpath: google/gcp-key.json");
        }

        return StorageOptions.newBuilder()
            .setCredentials(ServiceAccountCredentials.fromStream(keyFile))
            .setProjectId("sublime-night-472604") //실제 GCP ID 입력
            .build()
            .getService();
    }
}