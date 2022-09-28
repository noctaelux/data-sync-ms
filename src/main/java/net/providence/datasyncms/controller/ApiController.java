package net.providence.datasyncms.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/data/sync")
public class ApiController {

    @PostMapping("/postgres/to/redis")
    public String postgresToRedis(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceData = "http://localhost:8081/api/v1/postgres-stress/list";
        String postData = "http://localhost:8080/api/v1/redis-stress/agregar/lista";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.getForEntity(resourceData, String.class);

        HttpEntity<String> request = new HttpEntity<>(Objects.requireNonNull(response.getBody()), headers);

        ResponseEntity<String> targetResponse = restTemplate.postForEntity(postData,request, String.class);

        return targetResponse.getBody();
    }

}
