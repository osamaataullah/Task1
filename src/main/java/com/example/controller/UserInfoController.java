package com.example.controller;

import com.example.client.AnotherServiceClient;
import com.example.config.PropertyFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserInfoController {

    private final PropertyFileReader propertyFileReader;
    private final AnotherServiceClient anotherServiceClient;

    @Autowired
    public UserInfoController(PropertyFileReader propertyFileReader, AnotherServiceClient anotherServiceClient) {
        this.propertyFileReader = propertyFileReader;
        this.anotherServiceClient = anotherServiceClient;
    }

    @GetMapping("/userDetail")
    public ResponseEntity<Object> getUserDetail(@RequestParam String user) {
        try {
            String workstation = propertyFileReader.getWorkstationForUser(user);
            if (workstation != null) {
                // User found, prepare JSON and post it to another service
                String json = "{\"user\": \"" + user + "\", \"workstation\": \"" + workstation + "\", \"status\": \"Success\", \"message\": \"user exist in database and has access to given workstation\"}";
                anotherServiceClient.postToAnotherService(json);
                return ResponseEntity.ok().body(json);
            } else {
                // User not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }
}
