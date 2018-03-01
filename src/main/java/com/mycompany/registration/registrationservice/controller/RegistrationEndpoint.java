/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.controller;

import com.mycompany.user.resource.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RegistrationEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationEndpoint.class);

    private static final String USER_SERVICE_URL = "http://localhost:8080/";
    private static final String USER_SERVICE_ENDPOINT = "/user/{ssn}";
    private static final String USER_SERVICE_CREATE_USER_ENDPOINT = "/user/create";

    private static final String EXCLUSION_SERVICE_URL = "http://localhost:8090/exclusion-service/rest/";
    private static final String EXCLUSION_SERVICE_VALIDATE = "validate/{ssn}/{dob}";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity register() {

        String userServiceUrlEndpoint = USER_SERVICE_URL + USER_SERVICE_ENDPOINT.replace("{ssn}", "###-0000-###-0001");

        LOGGER.debug("Calling external UserService with endpoint URL: %s", userServiceUrlEndpoint);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(userServiceUrlEndpoint, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        String exclusionServiceUrlEndpoint = EXCLUSION_SERVICE_URL + EXCLUSION_SERVICE_VALIDATE.replace("{ssn}/{dob}","");
        ResponseEntity<String> responseEntity1 = restTemplate.getForEntity(exclusionServiceUrlEndpoint, String.class);
        if (responseEntity1.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        UserResource requestResource = new UserResource();

        HttpEntity<UserResource> request = new HttpEntity<>(requestResource);
        restTemplate.postForEntity(USER_SERVICE_URL + USER_SERVICE_CREATE_USER_ENDPOINT, request, UserResource.class);

        return ResponseEntity.ok().build();
    }
}
