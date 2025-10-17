package com.loupfitproductservice.product_service.business;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), - 1)
                            .contentType(file.getContentType())
                            .build()
            );

            return "http://localhost:9000/" + bucketName + "/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload no MinIO: " + e.getMessage());
        }
    }

    public String removeFile(String fileName) {

        try {

            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );

            return "Imagem removida com sucesso";

        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover arquivo do MinIO: " + e.getMessage(), e);
        }

    }
}
