package com.example.LinkedInInsights.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client = S3Client.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .build();

    public String uploadFile(File file) throws IOException {
        String key = UUID.randomUUID() + "-" + file.getName();

        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putRequest, RequestBody.fromBytes(Files.readAllBytes(file.toPath())));

        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }
}
