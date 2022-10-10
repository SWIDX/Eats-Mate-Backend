package com.swidx.reviewservice.image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<String> upload(List<MultipartFile> mfileList, String dirName) throws IOException {
        List<String> fileUrlList = new ArrayList<String>();

        for (MultipartFile mfile : mfileList) {
            // 하단의 주석을 해제하면 FileNotFoundException이 발생한다. 왜 그런지 궁금하다면
            // https://stackoverflow.com/questions/59214385/java-io-filenotfoundexception-for-a-present-multipartfile
            // 이거 읽고 평생 잊지 마시오
            
            // File file = new File(mfile.getOriginalFilename());
            // mfile.transferTo(file);

            if (false) { // 나중에 처리
                System.out.println("\n*** S3Uploader: MultipartFile -> File Conversion Failed ***");
            }

            // String fileName = dirName + "/" + file.getName();
            ObjectMetadata objectMetadata = generateObjectMetaData(mfile);
            String imageKey = dirName + "/" + UUID.randomUUID();
            
            // aws-java-sdk-bom 구버전 쓰면 PutObjectRequest 파라미터가 3개짜리임
            amazonS3Client.putObject(new PutObjectRequest(bucket, imageKey, mfile.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            String uploadImageUrl = amazonS3Client.getUrl(bucket, imageKey).toString();
            fileUrlList.add(uploadImageUrl);

            // removeNewFile(file);
        }
        return fileUrlList;
    }

    private ObjectMetadata generateObjectMetaData(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        return objectMetadata;
    }

    private void removeNewFile(File targetFile) {
        if (!targetFile.delete()) {
            System.out.println("\n*** S3Uploader: Local File Removal Failed ***");
        }
    }
}