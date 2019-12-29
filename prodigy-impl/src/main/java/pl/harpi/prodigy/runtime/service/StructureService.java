package pl.harpi.prodigy.runtime.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StructureService {
    @Cacheable(value = "cacheProducts", key = "#productId")
    public Map<String, Object> getStructure(Long productId) {
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJHRY4FUV63DHK5AA",
                "3H4VZuM5A/IyQxYJfK9G253ZOZ36/RyWuKlAUqJK"
        );

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1).build();
        String str = s3.getObjectAsString("harpipl-prodigy", "structures/products/0000000000000000001.json");

        Map<String, Object> value = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            value = mapper.readValue(str, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("Arek");
        return value;
    }
}
