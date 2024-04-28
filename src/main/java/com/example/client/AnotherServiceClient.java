package com.example.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AnotherServiceClient {

    private final RestTemplate restTemplate;

    @Value("${another.service.url}")
    private String anotherServiceUrl;

    public AnotherServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void postToAnotherService(String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        restTemplate.postForObject(anotherServiceUrl, requestEntity, String.class);
    }
}
