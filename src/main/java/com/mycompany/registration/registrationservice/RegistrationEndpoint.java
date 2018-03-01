/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RegistrationEndpoint {

    private static final String USER_SERVICE_ENDPOINT = "/user/{ssn}";
    private static final String USER_SERVICE_URL = "http://localhost:8080/";

    public static final String USER_SERVICE_URL_ENDPOINT = USER_SERVICE_URL + USER_SERVICE_ENDPOINT;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity register() {

        String userServiceUrlEndpoint = USER_SERVICE_URL_ENDPOINT.replace("{ssn}", "###-0000-###-0001");

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(userServiceUrlEndpoint, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().build();
    }
}
